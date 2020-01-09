package com.group.capstone.attendance.service.Schedule;

import com.group.capstone.attendance.entity.Registration;
import com.group.capstone.attendance.entity.Schedule;
import com.group.capstone.attendance.model.Schedule.dto.StudentScheduleDetailDto;
import com.group.capstone.attendance.model.Schedule.dto.StudentScheduleDto;
import com.group.capstone.attendance.model.Schedule.dto.TeacherScheduleDto;
import com.group.capstone.attendance.model.Schedule.mapper.ScheduleMapper;
import com.group.capstone.attendance.repository.ClassRepository;
import com.group.capstone.attendance.repository.RegistrationRepository;
import com.group.capstone.attendance.repository.ScheduleRepository;
import com.group.capstone.attendance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private UserRepository userRepository;

    public List<StudentScheduleDetailDto> getScheduleByIdStudent (int student_id, String date){
        List<StudentScheduleDto> studentScheduleDtoList = scheduleRepository.getScheduleByIdStudent(student_id, date);
        if(!studentScheduleDtoList.isEmpty()){
            List<StudentScheduleDetailDto> studentScheduleDetailDtoList = new ArrayList<>();
            List<String> avatar_5_friends;
            for(StudentScheduleDto std : studentScheduleDtoList){
                System.out.println(std.getSchedule_id());
                avatar_5_friends = userRepository.getUserPictureByScheduleId(std.getSchedule_id());
                System.out.println(avatar_5_friends);
                studentScheduleDetailDtoList.add(ScheduleMapper.toStudentScheduleDetailDto(std,avatar_5_friends));
            }
            return studentScheduleDetailDtoList;
        }
        return null;
    }

    public List<TeacherScheduleDto> getScheduleByIdTeacher (int teacher_id, String date){
        return scheduleRepository.getScheduleByIdTeacher(teacher_id, date);
    }
}
