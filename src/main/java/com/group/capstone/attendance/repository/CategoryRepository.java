package com.group.capstone.attendance.repository;

import com.group.capstone.attendance.entity.Category;
import com.group.capstone.attendance.model.Category.dto.CategoryInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(nativeQuery = true, name = "getAllCategoryInfo")
    public List<CategoryInfoDto> getAllCategoryInfo();
}
