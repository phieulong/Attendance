package com.group.capstone.attendance.service.Class;

import com.group.capstone.attendance.model.Class.dto.ClassDto;
import com.group.capstone.attendance.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClassServiceImpl implements ClassService{
    @Autowired
    private ClassRepository classRepository;

    public List<ClassDto> getAllClassInfo(){
        return classRepository.getAllClassInfo();
    }
}
