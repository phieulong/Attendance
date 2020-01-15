package com.group.capstone.attendance.service.Attendance;

import com.group.capstone.attendance.common.ErrorMessage;
import com.group.capstone.attendance.entity.Attendance;
import com.group.capstone.attendance.entity.Registration;
import com.group.capstone.attendance.entity.Schedule;
import com.group.capstone.attendance.exception.ErrorServerException;
import com.group.capstone.attendance.exception.RecordNotFoundException;
import com.group.capstone.attendance.model.Attendance.dto.TeacherAttendanceInfo;
import com.group.capstone.attendance.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;

    public String setAttendanceByStudent(int Student_id, int Schedule_id){
        Attendance attendance;
        try {
            attendance = attendanceRepository.findByRegistrationAndSchedule(Student_id, Schedule_id);
        }catch (Exception ex){
            throw new ErrorServerException(ErrorMessage.ERROR_SERVER);
        }
        if (attendance == null)
            throw new RecordNotFoundException(ErrorMessage.NOT_FOUND);
        attendance.setPresent(true);
        attendance.setUpdatedBy(Student_id);
        Date date = new Date();
        attendance.setUpdatedAt(date);
        try{
            attendanceRepository.saveAndFlush(attendance);
            return "success";
        }catch (Exception ex){
            throw new ErrorServerException(ErrorMessage.ERROR_SERVER);
        }
    }

    public String setAttendanceByTeacher(int teacher_id, int Student_id, int Schedule_id, boolean is_present){
        Attendance attendance;
        try {
            attendance = attendanceRepository.findByRegistrationAndSchedule(Student_id, Schedule_id);
        }catch (Exception ex){
            throw new ErrorServerException(ErrorMessage.ERROR_SERVER);
        }
        if (attendance == null)
            throw new RecordNotFoundException(ErrorMessage.NOT_FOUND);
        attendance.setPresent(is_present);
        attendance.setUpdatedBy(teacher_id);
        Date date = new Date();
        attendance.setUpdatedAt(date);
        try{
            attendanceRepository.saveAndFlush(attendance);
            return "success";
        }catch (Exception ex){
            throw new ErrorServerException(ErrorMessage.ERROR_SERVER);
        }
    }

    public List<TeacherAttendanceInfo> getAttendanceListByScheduleId(int schedule_id){
        List<TeacherAttendanceInfo> teacherAttendanceInfoList;
        try {
            teacherAttendanceInfoList = attendanceRepository.getAttendanceListByScheduleId(schedule_id);
        }catch (Exception ex){
            throw new ErrorServerException(ErrorMessage.ERROR_SERVER);
        }
        if (teacherAttendanceInfoList.isEmpty())
            throw new RecordNotFoundException(ErrorMessage.NOT_FOUND);
        return teacherAttendanceInfoList;
    }

    public Attendance createAttendance(int teacher_id, Registration registration, Schedule schedule){
        Attendance attendance;
        try {
            attendance = attendanceRepository.findByScheduleAndRegistration(schedule.getId(), registration.getId());
        }catch (Exception ex){
            throw new ErrorServerException(ErrorMessage.ERROR_SERVER);
        }
        if(attendance != null)
            return attendance;
        attendance = new Attendance();
        Date date = new Date();
        attendance.setSchedule(schedule);
        attendance.setRegistration(registration);
        attendance.setPresent(false);
        attendance.setCreatedAt(date);
        attendance.setCreatedBy(teacher_id);
        attendance.setUpdatedAt(date);
        attendance.setUpdatedBy(teacher_id);
        try {
            attendance = attendanceRepository.saveAndFlush(attendance);
        }catch (Exception ex){
            throw new ErrorServerException(ErrorMessage.ERROR_SERVER);
        }
        return attendance;
    }
}
