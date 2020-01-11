package com.group.capstone.attendance.service.Term;

import com.group.capstone.attendance.model.Term.dto.TermDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TermService {
    public List<TermDto> getAllTermInfo();
}
