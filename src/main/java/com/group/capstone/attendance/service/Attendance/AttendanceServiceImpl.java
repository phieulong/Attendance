package com.group.capstone.attendance.service.Attendance;

import com.group.capstone.attendance.common.ErrorMessage;
import com.group.capstone.attendance.entity.Attendance;
import com.group.capstone.attendance.entity.Registration;
import com.group.capstone.attendance.entity.Schedule;
import com.group.capstone.attendance.exception.BadRequest;
import com.group.capstone.attendance.exception.ErrorServerException;
import com.group.capstone.attendance.exception.RecordNotFoundException;
import com.group.capstone.attendance.model.Attendance.dto.TeacherAttendanceInfo;
import com.group.capstone.attendance.repository.AttendanceRepository;
import com.group.capstone.attendance.repository.CategoryRepository;
import com.group.capstone.attendance.repository.RegistrationRepository;
import com.group.capstone.attendance.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private RegistrationRepository registrationRepository;

    //Sinh viên tự điểm danh, set is_present bằng true
    //student_id lấy từ token, schedule_id gửi lên từ client
    //log lại thông tin sinh viên update
    public String setAttendanceByStudent(int Student_id, int Schedule_id){
        Date date = new Date();
        Attendance attendance;
        Registration registration;
        try {
            System.out.println(Student_id);
            registration = registrationRepository.findByUserId(Student_id);
        }catch (Exception ex){
            //trong quá trình tương tác với database nếu có lỗi trả về message
            throw new ErrorServerException(ErrorMessage.ERROR_SERVER);
        }
        if (registration == null)
            throw new RecordNotFoundException(ErrorMessage.NOT_FOUND);
        try {
            attendance = attendanceRepository.findByScheduleAndRegistration(Schedule_id, registration.getId());
        }catch (Exception ex){
            //trong quá trình tương tác với database nếu có lỗi trả về message
            throw new ErrorServerException(ErrorMessage.ERROR_SERVER);
        }
        //nếu không có attendance trả về lỗi null
        if (attendance == null)
            throw new RecordNotFoundException(ErrorMessage.NOT_FOUND);
        attendance.setPresent(true);
        attendance.setUpdatedBy(Student_id);
        attendance.setUpdatedAt(date);
        try{
            attendanceRepository.saveAndFlush(attendance);
            return "success";
        }catch (Exception ex){
            //trong quá trình tương tác với database nếu có lỗi trả về message
            throw new ErrorServerException(ErrorMessage.ERROR_SERVER);
        }
    }
    //giáo viên điểm danh cho sinh viên, có thể set giá trị is_present = true và false
    //teacher_id lấy từ token, 2 thông tin còn lại gửi lên từ client
    //log lại thông tin giáo viên điểm danh
    public String setAttendanceByTeacher(int teacher_id, int Student_id, int Schedule_id, boolean is_present){
        Attendance attendance;
        try {
            attendance = attendanceRepository.findByRegistrationAndSchedule(Student_id, Schedule_id);
        }catch (Exception ex){
            //trong quá trình tương tác với database nếu có lỗi trả về message
            throw new ErrorServerException(ErrorMessage.ERROR_SERVER);
        }
        //nếu không có attendance trả về lỗi null
        if (attendance == null)
            throw new RecordNotFoundException(ErrorMessage.NOT_FOUND);
        attendance.setPresent(is_present);
        //log lại thông update
        attendance.setUpdatedBy(teacher_id);
        Date date = new Date();
        attendance.setUpdatedAt(date);
        try{
            attendanceRepository.saveAndFlush(attendance);
            return "success";
        }catch (Exception ex){
            //trong quá trình tương tác với database nếu có lỗi trả về message
            throw new ErrorServerException(ErrorMessage.ERROR_SERVER);
        }
    }
    //giáo viên lấy thông tin điểm danh của một buổi học
    //schedule_id gửi lên từ client
    public List<TeacherAttendanceInfo> getAttendanceListByScheduleId(int schedule_id){
        List<TeacherAttendanceInfo> teacherAttendanceInfoList;
        try {
            teacherAttendanceInfoList = attendanceRepository.getAttendanceListByScheduleId(schedule_id);
        }catch (Exception ex){
            //trong quá trình tương tác với database nếu có lỗi trả về message
            throw new ErrorServerException(ErrorMessage.ERROR_SERVER);
        }
        if (teacherAttendanceInfoList.isEmpty())
            //nếu không có schedule trả về lỗi null
            throw new RecordNotFoundException(ErrorMessage.NOT_FOUND);
        return teacherAttendanceInfoList;
    }

    //tạo danh sách điểm danh cho một buổi học
    //teacher_id lấy từ token 2 thông tin còn lại lấy từ client
    //log lại thông tin giáo viên tạo danh sách điểm danh
    public Attendance createAttendance(int teacher_id, Registration registration, Schedule schedule){
        Attendance attendance;
        try {
            attendance = attendanceRepository.findByScheduleAndRegistration(schedule.getId(), registration.getId());
        }catch (Exception ex){
            //trong quá trình tương tác với database nếu có lỗi trả về message
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
            //trong quá trình tương tác với database nếu có lỗi trả về message
            throw new ErrorServerException(ErrorMessage.ERROR_SERVER);
        }
        return attendance;
    }
}
