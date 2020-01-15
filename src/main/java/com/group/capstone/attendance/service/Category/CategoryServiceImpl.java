package com.group.capstone.attendance.service.Category;

import com.group.capstone.attendance.common.ErrorMessage;
import com.group.capstone.attendance.exception.ErrorServerException;
import com.group.capstone.attendance.exception.RecordNotFoundException;
import com.group.capstone.attendance.model.Category.dto.CategoryInfoDto;
import com.group.capstone.attendance.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public List<CategoryInfoDto> getAllCategoryInfo(){
        List<CategoryInfoDto> categoryInfoDtoList;
        try {
            categoryInfoDtoList = categoryRepository.getAllCategoryInfo();
        }catch (Exception ex){
            throw new ErrorServerException(ErrorMessage.ERROR_SERVER);
        }
        if (categoryInfoDtoList.isEmpty())
            throw new RecordNotFoundException(ErrorMessage.NOT_FOUND);
        return categoryInfoDtoList;
    }
}
