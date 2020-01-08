package com.group.capstone.attendance.service.Schedule;

import com.group.capstone.attendance.entity.Registration;
import com.group.capstone.attendance.entity.Schedule;
import com.group.capstone.attendance.model.Schedule.dto.StudentScheduleDto;
import com.group.capstone.attendance.model.Schedule.mapper.ScheduleMapper;
import com.group.capstone.attendance.repository.ClassRepository;
import com.group.capstone.attendance.repository.RegistrationRepository;
import com.group.capstone.attendance.repository.ScheduleRepository;
import com.group.capstone.attendance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    public List<StudentScheduleDto> getScheduleByIdStudent (int student_id, String date){
        List<StudentScheduleDto> studentScheduleDtoList = scheduleRepository.getScheduleByIdStudent(student_id, date);
        return studentScheduleDtoList;
    }
}
