package com.group.capstone.attendance.repository;

import com.group.capstone.attendance.entity.ClassTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public interface ClassTermRepository extends JpaRepository<ClassTerm, Integer> {
    @Query(nativeQuery = true, value = "select *\n" +
                                        "from class_term ct\n" +
                                        "join class cl on ct.class_id = cl.id\n" +
                                        "join term t on ct.term_id = t.id\n" +
                                        "where cl.id = ?1 and t.id = ?2 ")
    public ClassTerm getClassTermIdByClassIdAndTermId(int class_id, int term_id );
}
