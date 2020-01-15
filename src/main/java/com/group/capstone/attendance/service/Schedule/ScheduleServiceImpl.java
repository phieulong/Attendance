package com.group.capstone.attendance.service.Schedule;

import com.group.capstone.attendance.common.ErrorMessage;
import com.group.capstone.attendance.entity.*;
import com.group.capstone.attendance.entity.Class;
import com.group.capstone.attendance.exception.BadRequest;
import com.group.capstone.attendance.exception.ErrorServerException;
import com.group.capstone.attendance.exception.RecordNotFoundException;
import com.group.capstone.attendance.model.Schedule.dto.StudentScheduleDetailDto;
import com.group.capstone.attendance.model.Schedule.dto.StudentScheduleDto;
import com.group.capstone.attendance.model.Schedule.dto.TeacherScheduleDto;
import com.group.capstone.attendance.model.Schedule.mapper.ScheduleMapper;
import com.group.capstone.attendance.model.Schedule.request.CreateScheduleRequest;
import com.group.capstone.attendance.repository.*;
import com.group.capstone.attendance.service.Attendance.AttendanceService;
import com.group.capstone.attendance.service.ClassTerm.ClassTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClassTermService classTermService;
    @Autowired
    private ClassTermRepository classTermRepository;
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private TermRepository termRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private AttendanceService attendanceService;

    //Service get tất cả thông tin buổi học trong ngày của sinh viên được gửi từ client
    //student_id được lấy từ token
    public List<StudentScheduleDetailDto> getScheduleByStudentId (int student_id, String date){
        List<StudentScheduleDto> studentScheduleDtoList;
        try {
            studentScheduleDtoList = scheduleRepository.getScheduleByIdStudent(student_id, date);
        }catch (Exception ex){
            throw new ErrorServerException(ErrorMessage.ERROR_SERVER);
        }
        if(studentScheduleDtoList.isEmpty())
            throw new RecordNotFoundException(ErrorMessage.NOT_FOUND);
        List<StudentScheduleDetailDto> studentScheduleDetailDtoList = new ArrayList<>();
        List<String> avatar_5_friends;
        for(StudentScheduleDto std : studentScheduleDtoList){
            try {
            avatar_5_friends = userRepository.getUserPictureByScheduleId(std.getSchedule_id());
            }catch (Exception ex){
                throw new ErrorServerException(ErrorMessage.ERROR_SERVER);
            }
            studentScheduleDetailDtoList.add(ScheduleMapper.toStudentScheduleDetailDto(std,avatar_5_friends));
        }
        return studentScheduleDetailDtoList;

    }

    //Service get tất cả thông tin buổi học trong ngày của giáo viên được gửi từ client
    //teacher_id được lấy từ token
    public List<TeacherScheduleDto> getScheduleByTeacherId (int teacher_id, String date){
        List<TeacherScheduleDto> teacherScheduleDtoList;
        try {
            teacherScheduleDtoList = scheduleRepository.getScheduleByIdTeacher(teacher_id, date);
        }catch (Exception ex){
            throw new ErrorServerException(ErrorMessage.ERROR_SERVER);
        }
        if (teacherScheduleDtoList.isEmpty())
            throw new RecordNotFoundException(ErrorMessage.NOT_FOUND);
        return teacherScheduleDtoList;
    }

    //Service tạo thời khóa biểu
    //Id người tạo thời khóa biểu được lấy từ token
    //Thông tin thời khóa biểu truyền lên từ client
    //Sử dụng transaction khi xảy ra lỗi trong quá trình query vào database
    @Transactional
    public String createSchedule(int Teacher_id, CreateScheduleRequest createScheduleRequest){
        ClassTerm classTerm;
        try{
            classTerm = classTermRepository.getClassTermIdByClassIdAndTermId
                (createScheduleRequest.getClassId(), createScheduleRequest.getTermId());
        }catch (Exception ex){
            throw new ErrorServerException(ErrorMessage.ERROR_SERVER);
        }
        Date date = new Date();
        //nếu thông tin lớp học chưa có trong kì học người dùng yêu cầu, thì phải đăng kí mới
        if (classTerm == null){
            Class aClass;
            Term term;
            try {
                aClass = classRepository.findById(createScheduleRequest.getClassId());
                term = termRepository.findById(createScheduleRequest.getTermId());
            }catch (Exception ex){
                throw new ErrorServerException(ErrorMessage.ERROR_SERVER);
            }
            classTerm = classTermService.createClassTerm(Teacher_id, aClass, term);
        }else{
            List<Schedule> scheduleList = scheduleRepository.checkDuplicate
                    (classTerm.getId(), createScheduleRequest.getDate().toString(), createScheduleRequest.getCategoryId());
            if(!scheduleList.isEmpty())
                throw new BadRequest(ErrorMessage.DUPLICATE_RECORD);
        }
        Schedule schedule = new Schedule();
        schedule.setDate(createScheduleRequest.getDate());
        Category category;
        Room room;
        Subject subject;
        User user;
        try {
            category = categoryRepository.findById(createScheduleRequest.getCategoryId());
            room = roomRepository.findById(createScheduleRequest.getRoomId());
            subject = subjectRepository.findById(createScheduleRequest.getSubjectId());
            user = userRepository.findById(createScheduleRequest.getSubTrainerId());
        }catch (Exception ex){
            throw new ErrorServerException(ErrorMessage.ERROR_SERVER);
        }
        schedule.setCategory(category);
        schedule.setClassTerm(classTerm);
        schedule.setRoom(room);
        schedule.setSubject(subject);
        schedule.setSubTrainer(user);
        schedule.setCreatedAt(date);
        schedule.setCreatedBy(Teacher_id);
        schedule.setUpdatedAt(date);
        schedule.setUpdatedBy(Teacher_id);
        schedule.setStatus(true);
        try {
            schedule = scheduleRepository.saveAndFlush(schedule);
        }catch (Exception ex){
            throw new ErrorServerException(ErrorMessage.ERROR_SERVER);
        }
        //Tạo danh sách điểm danh sau khi thêm thành công thời khóa biểu
        List<Registration> registrationList = classTerm.getAClass().getRegistrationList();
        for(int i = 0; i < registrationList.size(); i++){
            attendanceService.createAttendance(Teacher_id, registrationList.get(i), schedule);
        }
        return "success";
    }
}
