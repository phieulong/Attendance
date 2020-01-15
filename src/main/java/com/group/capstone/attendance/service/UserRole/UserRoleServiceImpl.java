package com.group.capstone.attendance.service.UserRole;

import com.group.capstone.attendance.common.ErrorMessage;
import com.group.capstone.attendance.entity.Role;
import com.group.capstone.attendance.entity.User;
import com.group.capstone.attendance.entity.UserRole;
import com.group.capstone.attendance.exception.ErrorServerException;
import com.group.capstone.attendance.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;

    public UserRole creatUserRole(int Teacher_id, User user, Role role){
        UserRole userRole = new UserRole();
        Date date = new Date();
        userRole.setUser(user);
        userRole.setRole(role);
        userRole.setCreatedAt(date);
        userRole.setCreatedBy(Teacher_id);
        userRole.setUpdatedAt(date);
        userRole.setUpdatedBy(Teacher_id);
        try {
            userRoleRepository.saveAndFlush(userRole);
        }catch (Exception ex){
            throw new ErrorServerException(ErrorMessage.ERROR_SERVER);
        }
        return userRole;
    }
}
