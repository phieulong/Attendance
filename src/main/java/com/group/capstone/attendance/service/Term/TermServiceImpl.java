package com.group.capstone.attendance.service.Term;

import com.group.capstone.attendance.common.ErrorMessage;
import com.group.capstone.attendance.exception.ErrorServerException;
import com.group.capstone.attendance.exception.RecordNotFoundException;
import com.group.capstone.attendance.model.Term.dto.TermDto;
import com.group.capstone.attendance.repository.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TermServiceImpl implements TermService{
    @Autowired
    private TermRepository termRepository;
    public List<TermDto> getAllTermInfo(){
        List<TermDto> termDtoList;
        try {
            termDtoList = termRepository.getAllTermInfo();
        }catch (Exception ex){
            throw new ErrorServerException(ErrorMessage.ERROR_SERVER);
        }
        if (termDtoList.isEmpty())
            throw new RecordNotFoundException(ErrorMessage.NOT_FOUND);
        return termDtoList;
    }
}
