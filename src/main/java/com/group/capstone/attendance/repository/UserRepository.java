package com.group.capstone.attendance.repository;

import com.group.capstone.attendance.entity.User;
import com.group.capstone.attendance.model.User.dto.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(nativeQuery = true, value = "select u.picture " +
                                        "from attendance atd " +
                                        "JOIN registration r on atd.registration_id = r.id " +
                                        "JOIN user u on r.user_id = u.id " +
                                        "where atd.schedule_id = ?1 and atd.is_present = 1 " +
                                        "limit 5")
    public List<String> getUserPictureByScheduleId(int id);

    public User findByAccount(String account);

    @Query(nativeQuery = true, value = "Select r.name\n" +
                                        "From user u\n" +
                                        "Join user_role ur on u.id = ur.user_id\n" +
                                        "Join role r on r.id = ur.role_id\n" +
                                        "Where u.account = ?1")
    public List<String> getRoleByAccount(String account);

    @Query(nativeQuery = true, name = "getStudentInfo")
    public UserInfo getStudentInfo(int id);

    @Query(nativeQuery = true, name = "getTeacherInfo")
    public UserInfo getTeacherInfo(int id);

    @Query(nativeQuery = true, name = "getAllTeacher")
    public List<UserInfo> getAllTeacher();
}
