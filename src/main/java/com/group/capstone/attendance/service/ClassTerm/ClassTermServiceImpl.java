package com.group.capstone.attendance.service.ClassTerm;

import com.group.capstone.attendance.entity.ClassTerm;
import com.group.capstone.attendance.repository.ClassTermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClassTermServiceImpl implements ClassTermService{
    @Autowired
    private ClassTermRepository classTermRepository;
    public ClassTerm createClassTerm(ClassTerm classTerm){
        classTermRepository.saveAndFlush(classTerm);
        return classTerm;
    }
}
