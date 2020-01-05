package com.group.capstone.attendance.service.Schedule;

import com.group.capstone.attendance.entity.Registration;
import com.group.capstone.attendance.entity.Schedule;
import com.group.capstone.attendance.model.Schedule.dto.StudentScheduleDto;
import com.group.capstone.attendance.model.Schedule.mapper.ScheduleMapper;
import com.group.capstone.attendance.repository.ClassRepository;
import com.group.capstone.attendance.repository.RegistrationRepository;
import com.group.capstone.attendance.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    public StudentScheduleDto getAllScheduleForStudent(int student_id){
        Schedule schedule = scheduleRepository.findByStudent_Id(student_id);
        StudentScheduleDto studentScheduleDto = ScheduleMapper.toStudentScheduleDto(schedule);
        return studentScheduleDto;
    }
}
