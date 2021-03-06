package com.group.capstone.attendance.repository;

import com.group.capstone.attendance.entity.Term;
import com.group.capstone.attendance.model.Term.dto.TermDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TermRepository extends JpaRepository<Term, Integer> {
    @Query(nativeQuery = true, name = "getAllTermInfo")
    public List<TermDto> getAllTermInfo();

    @Query(nativeQuery = true, value = "select *\n" +
                                        "from term\n" +
                                        "where status = 1 and id = ?1")
    public Term findById(int id);
}
