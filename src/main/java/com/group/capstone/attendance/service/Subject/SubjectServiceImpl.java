package com.group.capstone.attendance.service.Subject;

import com.group.capstone.attendance.common.ErrorMessage;
import com.group.capstone.attendance.entity.Subject;
import com.group.capstone.attendance.exception.ErrorServerException;
import com.group.capstone.attendance.exception.RecordNotFoundException;
import com.group.capstone.attendance.model.Subject.dto.SubjectDto;
import com.group.capstone.attendance.model.Subject.mapper.SubjectMapper;
import com.group.capstone.attendance.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;
    public List<SubjectDto> getAllSubjectDto (){
        List<Subject> subjectList;
        try{
            subjectList = subjectRepository.getAllSubjectInfo();
        }catch (Exception ex){
            throw new ErrorServerException(ErrorMessage.ERROR_SERVER);
        }
        if (subjectList.isEmpty())
            throw new RecordNotFoundException(ErrorMessage.NOT_FOUND);
        List<SubjectDto> subjectDtoList = new ArrayList<>();
        for(Subject s : subjectList){
            subjectDtoList.add(SubjectMapper.toSubjectDto(s));
        }
        return subjectDtoList;
    }
}
