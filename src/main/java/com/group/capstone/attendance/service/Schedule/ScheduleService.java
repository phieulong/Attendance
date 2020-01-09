package com.group.capstone.attendance.service.Schedule;

import com.group.capstone.attendance.model.Schedule.dto.StudentScheduleDetailDto;
import com.group.capstone.attendance.model.Schedule.dto.StudentScheduleDto;
import com.group.capstone.attendance.model.Schedule.dto.TeacherScheduleDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScheduleService {
    public List<StudentScheduleDetailDto> getScheduleByIdStudent (int student_id, String date);

    public List<TeacherScheduleDto> getScheduleByIdTeacher (int teacher_id, String date);
}
