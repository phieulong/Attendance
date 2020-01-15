package com.group.capstone.attendance.service.User;

import com.group.capstone.attendance.common.ErrorMessage;
import com.group.capstone.attendance.entity.*;
import com.group.capstone.attendance.entity.Class;
import com.group.capstone.attendance.exception.BadRequest;
import com.group.capstone.attendance.exception.ErrorServerException;
import com.group.capstone.attendance.exception.RecordNotFoundException;
import com.group.capstone.attendance.model.User.dto.UserInfo;
import com.group.capstone.attendance.model.User.dto.UserLoginInfoDetailDto;
import com.group.capstone.attendance.model.User.dto.UserLoginInfoDto;
import com.group.capstone.attendance.model.User.mapper.UserLoginInfoDetailMapper;
import com.group.capstone.attendance.model.User.mapper.UserLoginInfoMapper;
import com.group.capstone.attendance.model.User.request.CreateUserRequest;
import com.group.capstone.attendance.repository.*;
import com.group.capstone.attendance.service.Attendance.AttendanceService;
import com.group.capstone.attendance.service.Registration.RegistrationService;
import com.group.capstone.attendance.service.UserRole.UserRoleService;
import com.group.capstone.attendance.util.JwtUltis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private AttendanceService attendanceService;

    //service: tạo mới user
    //thông tin người tạo được lấy từ token lưu vào cùng với user
    //Sử dụng transaction nếu quá trình insert dữ liệu bị lỗi, database sẽ được roll back
    @Transactional
    public UserInfo createUser(int Teacher_id, CreateUserRequest createUserRequest){
        //kiểm tra xem account truyền vào từ client đã tồn tại trong database hay chưa
        User u = userRepository.findByAccount(createUserRequest.getAccount());
        if (u != null)
            throw new BadRequest("This account has been registered");
        //kiểm tra xem email truyền vào từ client đã tồn tại trong database hay chưa
        u = userRepository.findByEmail(createUserRequest.getEmail());
        if (u != null)
            throw new BadRequest("This email has been registered");
        UserInfo userInfo = new UserInfo();
        User user = new User();
        user.setFirstName(createUserRequest.getFirst_name());
        user.setLassName(createUserRequest.getLast_name());
        user.setEmail(createUserRequest.getEmail());
        user.setAccount(createUserRequest.getAccount());
        user.setPassword(createUserRequest.getPassword());
        user.setPicture(createUserRequest.getPicture());
        Date date = new Date();
        user.setCreatedAt(date);
        user.setCreatedBy(Teacher_id);
        user.setUpdatedAt(date);
        user.setUpdatedBy(Teacher_id);
        user.setStatus(true);
        try{
        user = userRepository.saveAndFlush(user);
        }catch (Exception ex){
            throw new ErrorServerException(ErrorMessage.ERROR_SERVER);
        }
        userInfo.setClass_name("");
        Role role;
        try {
            role = roleRepository.findById(createUserRequest.getRole_id());
        }catch (Exception ex){
            throw new ErrorServerException(ErrorMessage.ERROR_SERVER);
        }
        // nếu là tạo user là một sinh viên, thì đăng kí vào lớp đã gửi lên từ request
        if(role.getName().equals("ROLE_STUDENT")){
            Registration registration = new Registration();
            Class aClass;
            try{
                aClass = classRepository.findById(createUserRequest.getClass_id());
            }catch (Exception ex){
                throw new ErrorServerException(ErrorMessage.ERROR_SERVER);
            }
            registration = registrationService.createRegistration(Teacher_id, user, aClass);
            userInfo.setClass_name(aClass.getName());
            List<Schedule> scheduleList = scheduleRepository.getAllByClassId(aClass.getId());
            for (Schedule sc : scheduleList){
                attendanceService.createAttendance(Teacher_id, registration, sc);
            }
        }
        UserRole userRole = userRoleService.creatUserRole(Teacher_id, user, role);
        userInfo.setId(user.getId());
        userInfo.setAccount(user.getAccount());
        userInfo.setEmail(createUserRequest.getEmail());
        userInfo.setName(user.getLassName()+" "+user.getFirstName());
        userInfo.setAvatar_link(createUserRequest.getPicture());
        return userInfo;
    }

    public UserLoginInfoDto getUserLoginInfoByAccount(String account){
        User user = userRepository.findByAccount(account);
        List<String> roles = userRepository.getRoleByAccount(account);
        return UserLoginInfoMapper.toUserLoginInfoDto(user, roles);
    }

    //service login: kiểm tra tài account và password truyền vào từ client
    //trả về thông tin tài khoản nếu tồn tài user chứa account và password đã truyền lên
    public UserLoginInfoDetailDto login(String account, String password){
        User user;
        try {
            //tìm user có account truyền vào từ client
            user = userRepository.findByAccount(account);
        }catch (Exception ex) {
            throw new ErrorServerException(ErrorMessage.ERROR_SERVER);
        }
        //nếu không tồn tại user này trả về lỗi
        if(user == null)
            throw new RecordNotFoundException(ErrorMessage.NOT_FOUND);
        List<String> roles;
        try {
            //lấy ra danh sách role của user
            roles = userRepository.getRoleByAccount(account);
        }catch (Exception ex){
            throw new ErrorServerException(ErrorMessage.ERROR_SERVER);
        }
        UserLoginInfoDetailDto userLoginInfoDetailDto;
        if(!password.equals(user.getPassword()))
            throw new RecordNotFoundException(ErrorMessage.NOT_FOUND);
        String aClass = "";
        //kiểm tra xem user có phải là sinh viên hay không
        //nếu đúng thì tìm lớp mà sinh viên đã đăng kí rồi truyền vào thông tin user trả về
        if (roles.get(0).equals("ROLE_STUDENT") && roles.size() == 1) {
            aClass = classRepository.findByStudentId(user.getId());
            userLoginInfoDetailDto = UserLoginInfoDetailMapper.toUserLoginInfoDetailDto(user, aClass, roles);
        // nếu là giáo viên thì tìm xem lớp cô giáo viên này làm chủ nhiệm, không có thì để trống
        } else {
            aClass = classRepository.findByTeacherId(user.getId());
            userLoginInfoDetailDto = UserLoginInfoDetailMapper.toUserLoginInfoDetailDto(user, aClass, roles);
        }
        //tạo token cho user trong phiên đăng nhập này rồi truyền vào biến trả về client
        userLoginInfoDetailDto.setToken(JwtUltis.generateToken(UserLoginInfoMapper.toUserLoginInfoDto(user, roles)));
        return userLoginInfoDetailDto;
    }

    public UserInfo getStudentInfo(int id){
        return userRepository.getStudentInfo(id);
    }

    public UserInfo getTeacherInfo(int id){
        return userRepository.getTeacherInfo(id);
    }

    public List<UserInfo> getAllTeacher(){
        return userRepository.getAllTeacher();
    }
}
