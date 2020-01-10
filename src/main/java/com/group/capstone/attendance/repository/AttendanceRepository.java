package com.group.capstone.attendance.repository;

import com.group.capstone.attendance.entity.Attendance;
import com.group.capstone.attendance.model.Attendance.dto.TeacherAttendanceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository  extends JpaRepository<Attendance, Integer> {

    @Query(nativeQuery = true,
            value = "select atd.*\n" +
                    "from user u\n" +
                    "Join registration rg on rg.user_id = u.id\n" +
                    "Join attendance atd on rg.id = atd.registration_id\n" +
                    "Join schedule sc on sc.id = atd.schedule_id\n" +
                    "Where u.id = ?1 and schedule_id = ?2")
    public Attendance findByRegistrationAndSchedule(int id, int schedule_id);

    @Query(nativeQuery = true, name = "getAttendanceByScheduleId")
    public List<TeacherAttendanceInfo> getAttendanceListByScheduleId(int schedule_id);

}
