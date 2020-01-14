package com.group.capstone.attendance.service.Attendance;

import com.group.capstone.attendance.entity.Attendance;
import com.group.capstone.attendance.entity.Registration;
import com.group.capstone.attendance.entity.Schedule;
import com.group.capstone.attendance.model.Attendance.dto.TeacherAttendanceInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AttendanceService {
    public String setAttendanceByTeacher(int teacher_id, int Student_id, int Schedule_id, boolean is_present);

    public String setAttendanceByStudent(int Student_id, int Schedule_id);

    public List<TeacherAttendanceInfo> getAttendanceListByScheduleId(int schedule_id);

    public Attendance createAttendance(int teacher_id, Registration registration, Schedule schedule);
}
