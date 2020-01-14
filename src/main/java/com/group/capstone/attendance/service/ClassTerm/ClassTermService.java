package com.group.capstone.attendance.service.ClassTerm;

import com.group.capstone.attendance.entity.Class;
import com.group.capstone.attendance.entity.ClassTerm;
import com.group.capstone.attendance.entity.Term;
import org.springframework.stereotype.Service;

@Service
public interface ClassTermService {
    public ClassTerm createClassTerm(int Teacher_id, Class aClass, Term term);
}
