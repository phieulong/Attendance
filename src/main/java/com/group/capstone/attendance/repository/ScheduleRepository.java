package com.group.capstone.attendance.repository;

import com.group.capstone.attendance.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    @Query(nativeQuery = true, value = "SELECT sd.* " +
                                        "FROM registrations rg " +
                                        "JOIN classes cl ON rg.classes_id = cl.id " +
                                        "JOIN schedules sd ON cl.id = sd.classes_id " +
                                        "WHERE rg.users_id = ?1")
    public Schedule findByStudent_Id(int student_id);
}
