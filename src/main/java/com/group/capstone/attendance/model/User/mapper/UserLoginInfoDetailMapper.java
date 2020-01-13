package com.group.capstone.attendance.model.User.mapper;

import com.group.capstone.attendance.entity.Class;
import com.group.capstone.attendance.entity.User;
import com.group.capstone.attendance.model.User.dto.UserLoginInfoDetailDto;

import java.util.List;

public class UserLoginInfoDetailMapper {
    public static UserLoginInfoDetailDto toUserLoginInfoDetailDto(User user, String aClass, List<String> roles){
        UserLoginInfoDetailDto userLoginInfoDetailDto = new UserLoginInfoDetailDto();
        userLoginInfoDetailDto.setId(user.getId());
        userLoginInfoDetailDto.setName(user.getLassName()+" "+user.getFirstName());
        userLoginInfoDetailDto.setClass_name(aClass);
        userLoginInfoDetailDto.setEmail(user.getEmail());
        userLoginInfoDetailDto.setAccount(user.getAccount());
        userLoginInfoDetailDto.setAvatar_link(user.getPicture());
        userLoginInfoDetailDto.setRoles(roles);
        return userLoginInfoDetailDto;
    }
}
