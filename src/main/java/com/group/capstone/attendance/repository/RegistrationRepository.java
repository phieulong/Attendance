package com.group.capstone.attendance.repository;

import com.group.capstone.attendance.entity.Class;
import com.group.capstone.attendance.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {
}
