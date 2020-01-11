package com.group.capstone.attendance.service.Category;

import com.group.capstone.attendance.model.Category.dto.CategoryInfoDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    public List<CategoryInfoDto> getAllCategoryInfo();
}
