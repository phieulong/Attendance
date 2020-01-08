package com.group.capstone.attendance.model.User.mapper;

import com.group.capstone.attendance.entity.User;
import com.group.capstone.attendance.model.User.dto.UserLoginInfoDto;

import java.util.List;

public class UserLoginInfoMapper {
    public static UserLoginInfoDto toUserLoginInfoDto(User user, List<String> roles){
        UserLoginInfoDto userLoginInfoDto = new UserLoginInfoDto();
        userLoginInfoDto.setId(user.getId());
        userLoginInfoDto.setAccount(user.getAccount());
        userLoginInfoDto.setPassword(user.getPassword());
        userLoginInfoDto.setRoles(roles);
        return userLoginInfoDto;
    }
}
