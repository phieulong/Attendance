package com.group.capstone.attendance.service.Schedule;

import com.group.capstone.attendance.entity.*;
import com.group.capstone.attendance.entity.Class;
import com.group.capstone.attendance.model.Schedule.dto.StudentScheduleDetailDto;
import com.group.capstone.attendance.model.Schedule.dto.StudentScheduleDto;
import com.group.capstone.attendance.model.Schedule.dto.TeacherScheduleDto;
import com.group.capstone.attendance.model.Schedule.mapper.ScheduleMapper;
import com.group.capstone.attendance.model.Schedule.request.CreateScheduleRequest;
import com.group.capstone.attendance.repository.*;
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
    private AttendanceRepository attendanceRepository;

    public List<StudentScheduleDetailDto> getScheduleByIdStudent (int student_id, String date){
        List<StudentScheduleDto> studentScheduleDtoList = scheduleRepository.getScheduleByIdStudent(student_id, date);
        List<StudentScheduleDetailDto> studentScheduleDetailDtoList = new ArrayList<>();
        if(!studentScheduleDtoList.isEmpty()){
            List<String> avatar_5_friends;
            for(StudentScheduleDto std : studentScheduleDtoList){
                avatar_5_friends = userRepository.getUserPictureByScheduleId(std.getSchedule_id());
                System.out.println(avatar_5_friends);
                studentScheduleDetailDtoList.add(ScheduleMapper.toStudentScheduleDetailDto(std,avatar_5_friends));
            }
        }
        return studentScheduleDetailDtoList;
    }

    public List<TeacherScheduleDto> getScheduleByIdTeacher (int teacher_id, String date){
        return scheduleRepository.getScheduleByIdTeacher(teacher_id, date);
    }
    @Transactional
    public String createSchedule(CreateScheduleRequest createScheduleRequest){
        ClassTerm classTerm = classTermRepository.getClassTermIdByClassIdAndTermId
                (createScheduleRequest.getClassId(), createScheduleRequest.getTermId());
        if (classTerm == null){
            classTerm = new ClassTerm();
            Class aClass = classRepository.findById(createScheduleRequest.getClassId());
            Term term = termRepository.findById(createScheduleRequest.getTermId());
            classTerm.setAClass(aClass);
            classTerm.setTerm(term);
            Date date = new Date();
            classTerm.setCreatedAt(date);
            classTerm.setCreatedBy(1);
            classTerm.setUpdatedAt(date);
            classTerm.setUpdatedBy(1);
            classTerm = classTermService.createClassTerm(classTerm);
        }
        Schedule schedule = new Schedule();
        schedule.setDate(createScheduleRequest.getDate());
        Category category = categoryRepository.findById(createScheduleRequest.getCategoryId());
        schedule.setCategory(category);
        schedule.setClassTerm(classTerm);
        Room room = roomRepository.findById(createScheduleRequest.getRoomId());
        schedule.setRoom(room);
        Subject subject = subjectRepository.findById(createScheduleRequest.getSubjectId());
        schedule.setSubject(subject);
        User user = userRepository.findById(createScheduleRequest.getSubTrainerId());
        schedule.setSubTrainer(user);
        Date date = new Date();
        schedule.setCreatedAt(date);
        schedule.setCreatedBy(1);
        schedule.setUpdatedAt(date);
        schedule.setUpdatedBy(1);
        schedule.setStatus(true);
        Schedule s = scheduleRepository.save(schedule);
        List<Registration> registrationList = classTerm.getAClass().getRegistrationList();
        for(int i = 0; i < registrationList.size(); i++){
            Attendance attendance = new Attendance();
            attendance.setSchedule(s);
            attendance.setRegistration(registrationList.get(i));
            attendance.setPresent(false);
            attendance.setCreatedAt(date);
            attendance.setCreatedBy(1);
            attendance.setUpdatedAt(date);
            attendance.setUpdatedBy(1);
            attendanceRepository.saveAndFlush(attendance);
        }
        scheduleRepository.flush();
        return "Success";
    }
}
