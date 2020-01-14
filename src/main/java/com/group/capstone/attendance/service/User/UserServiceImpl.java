package com.group.capstone.attendance.service.User;

import com.group.capstone.attendance.entity.Class;
import com.group.capstone.attendance.entity.Registration;
import com.group.capstone.attendance.entity.User;
import com.group.capstone.attendance.model.User.dto.UserInfo;
import com.group.capstone.attendance.model.User.dto.UserLoginInfoDetailDto;
import com.group.capstone.attendance.model.User.dto.UserLoginInfoDto;
import com.group.capstone.attendance.model.User.mapper.UserLoginInfoDetailMapper;
import com.group.capstone.attendance.model.User.mapper.UserLoginInfoMapper;
import com.group.capstone.attendance.model.User.request.CreateUserRequest;
import com.group.capstone.attendance.repository.ClassRepository;
import com.group.capstone.attendance.repository.RegistrationRepository;
import com.group.capstone.attendance.repository.RoleRepository;
import com.group.capstone.attendance.repository.UserRepository;
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
    private RegistrationRepository registrationRepository;

    @Transactional
    public UserInfo createUser(CreateUserRequest createUserRequest){
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
        user.setCreatedBy(1);
        user.setUpdatedAt(date);
        user.setUpdatedBy(1);
        user.setStatus(true);
        user = userRepository.saveAndFlush(user);
        userInfo.setClass_name("");
        String role_name = roleRepository.findById(createUserRequest.getRole_id()).getName();
        if(role_name.equals("ROLE_STUDENT")){
            Registration registration = new Registration();
            Class aClass = classRepository.findById(createUserRequest.getClass_id());
            registration.setAClass(aClass);
            registration.setUser(user);
            registration.setStatus(true);
            registration.setCreatedAt(date);
            registration.setCreatedBy(1);
            registration.setUpdatedAt(date);
            registration.setUpdatedBy(1);
            registrationRepository.saveAndFlush(registration);
            userInfo.setClass_name(aClass.getName());
        }
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

    public UserLoginInfoDetailDto login(String account, String password){
        try{
            User user = userRepository.findByAccount(account);
            List<String> roles = userRepository.getRoleByAccount(account);
            UserLoginInfoDetailDto userLoginInfoDetailDto = new UserLoginInfoDetailDto();
            if(user != null){
                if(password.equals(user.getPassword())){
                    String aClass;
                    if(roles.get(0).equals("ROLE_STUDENT") && roles.size() == 1) {
                        aClass = classRepository.findByStudentId(user.getId());
                        userLoginInfoDetailDto = UserLoginInfoDetailMapper.toUserLoginInfoDetailDto(user, aClass, roles);
                    }else{
                        aClass = classRepository.findByTeacherId(user.getId());
                        userLoginInfoDetailDto = UserLoginInfoDetailMapper.toUserLoginInfoDetailDto(user, aClass, roles);
                    }
                    userLoginInfoDetailDto.setToken(JwtUltis.generateToken(UserLoginInfoMapper.toUserLoginInfoDto(user, roles)));
                    return userLoginInfoDetailDto;
                }
            }
            return userLoginInfoDetailDto;
        }catch (Exception e){
            return null;
        }
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
