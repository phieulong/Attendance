package com.group.capstone.attendance.repository;

import com.group.capstone.attendance.entity.Category;
import com.group.capstone.attendance.model.Category.dto.CategoryInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(nativeQuery = true, name = "getAllCategoryInfo")
    public List<CategoryInfoDto> getAllCategoryInfo();

    @Query(nativeQuery = true, value = "select *\n" +
                                        "from category\n" +
                                        "where status = 1 and id = ?1")
    public Category findById(int id);
}
