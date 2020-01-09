package com.group.capstone.attendance.repository;

import com.group.capstone.attendance.entity.Schedule;
import com.group.capstone.attendance.model.Schedule.dto.StudentScheduleDto;
import com.group.capstone.attendance.model.Schedule.dto.TeacherScheduleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    @Query(nativeQuery = true, name = "findScheduleByStudentId")
    public List<StudentScheduleDto> getScheduleByIdStudent(int student_id, String date);

    @Query(nativeQuery = true, name = "findScheduleByTeacherId")
    public List<TeacherScheduleDto> getScheduleByIdTeacher(int teacher_id, String date);
}
