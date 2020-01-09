package com.group.capstone.attendance.service.User;


import com.group.capstone.attendance.model.User.dto.UserInfo;
import com.group.capstone.attendance.model.User.dto.UserLoginInfoDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public UserLoginInfoDto getUserLoginInfoByAccount(String account);

    public UserInfo getStudentInfo(int id);

    public UserInfo getTeacherInfo(int id);
}
