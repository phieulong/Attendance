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

    @Query(nativeQuery = true, value = "select *\n" +
                                        "from class\n" +
                                        "where status = 1 and id = ?1")
    public Class findById(int id);

    @Query(nativeQuery = true, value = "select cl.name\n" +
                                        "from user u\n" +
                                        "join registration rg on rg.user_id = u.id\n" +
                                        "join class cl on rg.class_id = cl.id\n" +
                                        "where u.id = ?1")
    public String findByStudentId(int id);

    @Query(nativeQuery = true, value = "select cl.name\n" +
                                        "from user u\n" +
                                        "join class cl on cl.user_id = u.id\n" +
                                        "where u.id = ?1")
    public String findByTeacherId(int id);
}
