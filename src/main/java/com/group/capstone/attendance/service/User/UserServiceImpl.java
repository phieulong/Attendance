package com.group.capstone.attendance.service.User;

import com.group.capstone.attendance.entity.User;
import com.group.capstone.attendance.model.User.dto.UserInfo;
import com.group.capstone.attendance.model.User.dto.UserLoginInfoDetailDto;
import com.group.capstone.attendance.model.User.dto.UserLoginInfoDto;
import com.group.capstone.attendance.model.User.mapper.UserLoginInfoDetailMapper;
import com.group.capstone.attendance.model.User.mapper.UserLoginInfoMapper;
import com.group.capstone.attendance.repository.ClassRepository;
import com.group.capstone.attendance.repository.UserRepository;
import com.group.capstone.attendance.util.JwtUltis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClassRepository classRepository;

    public UserLoginInfoDto getUserLoginInfoByAccount(String account){
        User user = userRepository.findByAccount(account);
        List<String> roles = userRepository.getRoleByAccount(account);
        return UserLoginInfoMapper.toUserLoginInfoDto(user, roles);
    }

    public UserLoginInfoDetailDto login(String account, String password){
        try{
            User user = userRepository.findByAccount(account);
            List<String> roles = userRepository.getRoleByAccount(account);
            if(user != null){
                if(password.equals(user.getPassword())){
                    UserLoginInfoDetailDto userLoginInfoDetailDto;
                    String aClass = new String();
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
            return null;
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
