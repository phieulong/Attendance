package com.group.capstone.attendance.repository;

import com.group.capstone.attendance.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository  extends JpaRepository<Attendance, Integer> {
    @Query(nativeQuery = true,
            value = "SELECT * FROM attendance WHERE registration_id = ?1 and schedule_id = ?2")
    public Attendance findByRegistrationAndSchedule(int id, int schedule_id);

//
//    @Query(nativeQuery = true,
//            value = "UPDATE attendance SET is_present = true\n" +
//                    "Where registration_id = ?1 and schedule_id = ?2")
//    public void setAttendanceByStudent(int Student_id, int Schedule_id);
}
