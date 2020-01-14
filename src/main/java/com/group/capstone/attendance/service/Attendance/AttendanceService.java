package com.group.capstone.attendance.service.Attendance;

import com.group.capstone.attendance.model.Attendance.dto.TeacherAttendanceInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AttendanceService {
    public String setAttendanceByTeacher(int Student_id, int Schedule_id, boolean is_present);

    public String setAttendanceByStudent(int Student_id, int Schedule_id);

    public List<TeacherAttendanceInfo> getAttendanceListByScheduleId(int schedule_id);
}
