package com.group.capstone.attendance.service.Attendance;

import org.springframework.stereotype.Service;

@Service
public interface AttendanceService {
    public String setAttendanceByStudent(int Student_id, int Schedule_id);
}
