package com.group.capstone.attendance.service.Registration;

import com.group.capstone.attendance.common.ErrorMessage;
import com.group.capstone.attendance.entity.Class;
import com.group.capstone.attendance.entity.Registration;
import com.group.capstone.attendance.entity.User;
import com.group.capstone.attendance.exception.ErrorServerException;
import com.group.capstone.attendance.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    private RegistrationRepository registrationRepository;

    public Registration createRegistration(int Teacher_id, User user, Class aClass){
        Registration registration = new Registration();
        Date date = new Date();
        registration.setAClass(aClass);
        registration.setUser(user);
        registration.setStatus(true);
        registration.setCreatedAt(date);
        registration.setCreatedBy(Teacher_id);
        registration.setUpdatedAt(date);
        registration.setUpdatedBy(Teacher_id);
        try{
            registration = registrationRepository.saveAndFlush(registration);
        }catch (Exception ex){
            throw new ErrorServerException(ErrorMessage.ERROR_SERVER);
        }
        return registration;
    }
}
