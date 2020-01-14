package com.group.capstone.attendance.service.Registration;

import com.group.capstone.attendance.entity.Class;
import com.group.capstone.attendance.entity.Registration;
import com.group.capstone.attendance.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface RegistrationService {
    public Registration createRegistration(int Teacher_id, User user, Class aClass);
}
