package com.group.capstone.attendance.repository;

import com.group.capstone.attendance.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    @Query(nativeQuery = true, value = "select *\n" +
                                        "from subject\n" +
                                        "where subject.status = 1")
    public List<Subject> getAllSubjectInfo();
}
