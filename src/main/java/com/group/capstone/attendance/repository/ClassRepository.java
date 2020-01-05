package com.group.capstone.attendance.repository;

import com.group.capstone.attendance.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClassRepository extends JpaRepository<Class, Integer> {
}
