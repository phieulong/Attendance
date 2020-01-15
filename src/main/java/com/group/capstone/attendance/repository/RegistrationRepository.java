package com.group.capstone.attendance.repository;

import com.group.capstone.attendance.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Integer> {
    @Query(nativeQuery = true, value = "SELECT * \n" +
                                        "from registration \n" +
                                        "where user_id = ?1")
    public Registration findByUserId(int id);
}
