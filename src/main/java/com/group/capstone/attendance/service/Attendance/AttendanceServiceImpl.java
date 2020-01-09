package com.group.capstone.attendance.service.Attendance;

import com.group.capstone.attendance.entity.Attendance;
import com.group.capstone.attendance.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;
    public String setAttendanceByStudent(int Student_id, int Schedule_id){
        Attendance attendance = attendanceRepository.findByRegistrationAndSchedule(Student_id, Schedule_id);
        attendance.setPresent(true);
        try{
            attendanceRepository.saveAndFlush(attendance);
            return "success";
        }catch (Exception ex){
            return "failed";
        }
    }
}
