package com.group.capstone.attendance.repository;

import com.group.capstone.attendance.entity.User;
import com.group.capstone.attendance.model.Schedule.dto.StudentScheduleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByAccount(String account);

    @Query(nativeQuery = true, value = "Select r.name\n" +
                                        "From user u\n" +
                                        "Join user_role ur on u.id = ur.user_id\n" +
                                        "Join role r on r.id = ur.role_id\n" +
                                        "Where u.account = ?1")
    public List<String> getRoleByAccount(String account);
}
