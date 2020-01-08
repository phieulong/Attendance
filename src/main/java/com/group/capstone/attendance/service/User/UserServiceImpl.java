package com.group.capstone.attendance.service.User;

import com.group.capstone.attendance.entity.User;
import com.group.capstone.attendance.model.User.dto.UserLoginInfoDto;
import com.group.capstone.attendance.model.User.mapper.UserLoginInfoMapper;
import com.group.capstone.attendance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public UserLoginInfoDto getUserLoginInfoByAccount(String account){
        User user = userRepository.findByAccount(account);
        List<String> roles = userRepository.getRoleByAccount(account);
        return UserLoginInfoMapper.toUserLoginInfoDto(user, roles);
    }
}
