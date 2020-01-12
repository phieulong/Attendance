package com.group.capstone.attendance.service.ClassTerm;

import com.group.capstone.attendance.entity.ClassTerm;
import org.springframework.stereotype.Service;

@Service
public interface ClassTermService {
    public ClassTerm createClassTerm(ClassTerm classTerm);
}
