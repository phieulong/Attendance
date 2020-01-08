package com.group.capstone.attendance.service.User;

import com.group.capstone.attendance.entity.User;
import com.group.capstone.attendance.model.User.dto.UserLoginInfoDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public UserLoginInfoDto getUserLoginInfoByAccount(String account);
}
