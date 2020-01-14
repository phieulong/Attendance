package com.group.capstone.attendance.repository;

import com.group.capstone.attendance.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    @Query(nativeQuery = true, value = "select *\n" +
                                        "from subject\n" +
                                        "where subject.status = 1")
    public List<Subject> getAllSubjectInfo();

    @Query(nativeQuery = true, value = "select *\n" +
                                        "from subject\n" +
                                        "where subject.status = 1 and subject.id = ?1")
    public Subject findById(int id);
}
