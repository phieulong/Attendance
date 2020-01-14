package com.group.capstone.attendance.repository;

import com.group.capstone.attendance.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query(nativeQuery = true, value = "select *\n" +
                                        "from role\n" +
                                        "where status = 1 and id = ?1")
    public Role findById(int id);
}
