package com.group.capstone.attendance.repository;

import com.group.capstone.attendance.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Query(nativeQuery = true, value = "select *\n" +
                                        "from room\n" +
                                        "where room.status = 1")
    public List<Room> getAllRoomInfo();

    @Query(nativeQuery = true, value = "select *\n" +
                                        "from room\n" +
                                        "where status = 1 and id = ?1")
    public Room findById(int id);
}
