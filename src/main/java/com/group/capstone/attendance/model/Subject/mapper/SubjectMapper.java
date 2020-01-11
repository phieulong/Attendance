package com.group.capstone.attendance.model.Subject.mapper;

import com.group.capstone.attendance.entity.Subject;
import com.group.capstone.attendance.model.Subject.dto.SubjectDto;

public class SubjectMapper {
    public static SubjectDto toSubjectDto(Subject subject){
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(subject.getId());
        subjectDto.setName(subject.getName());
        subjectDto.setDescription(subject.getDescription());
        return subjectDto;
    }
}
