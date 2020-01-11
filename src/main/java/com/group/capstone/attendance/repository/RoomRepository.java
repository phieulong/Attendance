package com.group.capstone.attendance.repository;

import com.group.capstone.attendance.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Query(nativeQuery = true, value = "select *\n" +
                                        "from room\n" +
                                        "where room.status = 1")
    public List<Room> getAllRoomInfo();
}
