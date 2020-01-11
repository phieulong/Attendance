package com.group.capstone.attendance.service.Class;

import com.group.capstone.attendance.model.Class.dto.ClassDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClassService {

    public List<ClassDto> getAllClassInfo();

}
