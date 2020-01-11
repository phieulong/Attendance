package com.group.capstone.attendance.service.Term;

import com.group.capstone.attendance.model.Term.dto.TermDto;
import com.group.capstone.attendance.repository.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TermServiceImpl implements TermService{
    @Autowired
    private TermRepository termRepository;
    public List<TermDto> getAllTermInfo(){
        return termRepository.getAllTermInfo();
    }
}
