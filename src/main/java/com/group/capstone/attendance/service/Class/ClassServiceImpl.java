package com.group.capstone.attendance.service.Class;

import com.group.capstone.attendance.common.ErrorMessage;
import com.group.capstone.attendance.exception.ErrorServerException;
import com.group.capstone.attendance.exception.RecordNotFoundException;
import com.group.capstone.attendance.model.Class.dto.ClassDto;
import com.group.capstone.attendance.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClassServiceImpl implements ClassService{
    @Autowired
    private ClassRepository classRepository;

    //lấy ra tất cả thông tin lớp học
    public List<ClassDto> getAllClassInfo(){
        List<ClassDto> classDtoList;
        try {
            classDtoList = classRepository.getAllClassInfo();
        }catch (Exception ex){
            //trong quá trình tương tác với database nếu xảy ra lỗi trả về message
            throw new ErrorServerException(ErrorMessage.ERROR_SERVER);
        }
        if (classDtoList.isEmpty())
            //nếu k tìm thấy class nào trả về message null
            throw new RecordNotFoundException(ErrorMessage.NOT_FOUND);
        return classDtoList;
    }
}
