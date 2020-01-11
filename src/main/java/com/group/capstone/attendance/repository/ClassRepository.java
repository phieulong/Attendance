package com.group.capstone.attendance.repository;

import com.group.capstone.attendance.entity.Class;
import com.group.capstone.attendance.model.Class.dto.ClassDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Class, Integer> {

    @Query(nativeQuery = true, name = "getAllTeacherInfo")
    public List<ClassDto> getAllClassInfo();

}
