package com.group.capstone.attendance.service.Schedule;

import com.group.capstone.attendance.model.Schedule.dto.StudentScheduleDto;
import org.springframework.stereotype.Service;

@Service
public interface ScheduleService {
    public StudentScheduleDto getScheduleByIdStudent (int student_id);
}
