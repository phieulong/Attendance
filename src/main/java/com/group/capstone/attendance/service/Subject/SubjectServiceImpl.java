package com.group.capstone.attendance.service.Subject;

import com.group.capstone.attendance.entity.Subject;
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
        List<Subject> subjectList = subjectRepository.getAllSubjectInfo();
        List<SubjectDto> subjectDtoList = new ArrayList<>();
        for(Subject s : subjectList){
            subjectDtoList.add(SubjectMapper.toSubjectDto(s));
        }
        return subjectDtoList;
    }
}
