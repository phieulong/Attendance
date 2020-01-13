package com.group.capstone.attendance.service.User;


import com.group.capstone.attendance.model.User.dto.UserInfo;
import com.group.capstone.attendance.model.User.dto.UserLoginInfoDetailDto;
import com.group.capstone.attendance.model.User.dto.UserLoginInfoDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public UserLoginInfoDto getUserLoginInfoByAccount(String account);

    public UserLoginInfoDetailDto login(String account, String password);

    public UserInfo getStudentInfo(int id);

    public UserInfo getTeacherInfo(int id);

    public List<UserInfo> getAllTeacher();
}
