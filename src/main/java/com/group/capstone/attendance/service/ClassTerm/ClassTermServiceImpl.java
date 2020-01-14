package com.group.capstone.attendance.service.ClassTerm;

import com.group.capstone.attendance.Common.ErrorMessage;
import com.group.capstone.attendance.entity.Class;
import com.group.capstone.attendance.entity.ClassTerm;
import com.group.capstone.attendance.entity.Term;
import com.group.capstone.attendance.exception.ErrorServerException;
import com.group.capstone.attendance.repository.ClassTermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ClassTermServiceImpl implements ClassTermService{
    @Autowired
    private ClassTermRepository classTermRepository;
    public ClassTerm createClassTerm(int Teacher_id, Class aClass, Term term){
        Date date = new Date();
        ClassTerm classTerm = new ClassTerm();
        classTerm.setAClass(aClass);
        classTerm.setTerm(term);
        classTerm.setCreatedAt(date);
        classTerm.setCreatedBy(Teacher_id);
        classTerm.setUpdatedAt(date);
        classTerm.setUpdatedBy(Teacher_id);
        try {
            classTerm = classTermRepository.saveAndFlush(classTerm);
        }catch (Exception ex){
            throw new ErrorServerException(ErrorMessage.ERROR_SERVER);
        }
        return classTerm;
    }
}
