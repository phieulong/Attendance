package com.group.capstone.attendance.service.UserRole;

import com.group.capstone.attendance.entity.Role;
import com.group.capstone.attendance.entity.User;
import com.group.capstone.attendance.entity.UserRole;
import org.springframework.stereotype.Service;

@Service
public interface UserRoleService {
    public UserRole creatUserRole(int Teacher_id, User user, Role role);
}
