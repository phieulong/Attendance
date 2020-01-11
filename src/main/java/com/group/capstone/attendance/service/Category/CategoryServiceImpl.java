package com.group.capstone.attendance.service.Category;

import com.group.capstone.attendance.model.Category.dto.CategoryInfoDto;
import com.group.capstone.attendance.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public List<CategoryInfoDto> getAllCategoryInfo(){
        return categoryRepository.getAllCategoryInfo();
    }
}
