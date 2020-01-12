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

    public String createSchedule(CreateScheduleRequest createScheduleRequest){
        ClassTerm classTerm = new ClassTerm();
        classTerm = classTermRepository.getClassTermIdByClassIdAndTermId
                (createScheduleRequest.getClassId(), createScheduleRequest.getTermId());
        if (classTerm == null){
            Class aClass = classRepository.findById(createScheduleRequest.getClassId());
            System.out.println(aClass.getId());
            Term term = termRepository.findById(createScheduleRequest.getTermId());
            System.out.println(term.getId());
            System.out.println(aClass.toString());
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
        scheduleRepository.saveAndFlush(schedule);
        return "Success";
    }
}
