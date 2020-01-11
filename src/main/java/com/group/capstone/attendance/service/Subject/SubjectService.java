package com.group.capstone.attendance.service.Subject;

import com.group.capstone.attendance.model.Subject.dto.SubjectDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubjectService {
    public List<SubjectDto> getAllSubjectDto ();
}
