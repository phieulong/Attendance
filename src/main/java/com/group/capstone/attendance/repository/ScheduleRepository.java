package com.group.capstone.attendance.repository;

import com.group.capstone.attendance.entity.Schedule;
import com.group.capstone.attendance.model.Schedule.dto.StudentScheduleDto;
import com.group.capstone.attendance.model.Schedule.dto.TeacherScheduleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    @Query(nativeQuery = true, name = "findScheduleByStudentId")
    public List<StudentScheduleDto> getScheduleByIdStudent(int student_id, String date);

    @Query(nativeQuery = true, name = "findScheduleByTeacherId")
    public List<TeacherScheduleDto> getScheduleByIdTeacher(int teacher_id, String date);

    @Query(nativeQuery = true, value = "SELECT * \n" +
            "from schedule sc\n" +
            "where sc.class_term_id = ?1 and sc.date = ?2 and sc.category_id = ?3")
    public List<Schedule> checkDuplicate(int ct_id, String date, int cg_id);

    @Query(nativeQuery = true, value = "select *\n" +
                                        "from schedule sc\n" +
                                        "join class_term ct on ct.id = sc.class_term_id\n" +
                                        "join class cl on cl.id = ct.class_id\n" +
                                        "where cl.id = ?1")
    public List<Schedule> getAllByClassId(int class_id);

    @Query(nativeQuery = true, value = "select *\n" +
                                        "from schedule\n" +
                                        "where id = ?1 ")
    public Schedule findById(int id);
}
