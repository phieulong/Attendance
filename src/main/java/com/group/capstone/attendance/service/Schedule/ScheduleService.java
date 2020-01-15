package com.group.capstone.attendance.service.Schedule;

import com.group.capstone.attendance.entity.Schedule;
import com.group.capstone.attendance.model.Schedule.dto.StudentScheduleDetailDto;
import com.group.capstone.attendance.model.Schedule.dto.TeacherScheduleDto;
import com.group.capstone.attendance.model.Schedule.request.CreateScheduleRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScheduleService {
    public List<StudentScheduleDetailDto> getScheduleByStudentId (int student_id, String date);

    public List<TeacherScheduleDto> getScheduleByTeacherId (int teacher_id, String date);

    public String createSchedule(int Teacher_id, CreateScheduleRequest createScheduleRequest);
}
