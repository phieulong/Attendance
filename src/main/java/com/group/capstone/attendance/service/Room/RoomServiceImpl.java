package com.group.capstone.attendance.service.Room;

import com.group.capstone.attendance.entity.Room;
import com.group.capstone.attendance.model.Room.dto.RoomDto;
import com.group.capstone.attendance.model.Room.mapper.RoomMapper;
import com.group.capstone.attendance.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoomServiceImpl implements RoomService{
    @Autowired
    private RoomRepository roomRepository;
    public List<RoomDto> getAllRoomInfo(){
        List<Room> roomList = roomRepository.getAllRoomInfo();
        List<RoomDto> roomDtoList = new ArrayList<>();
        for(Room r : roomList){
            roomDtoList.add(RoomMapper.toRoomDto(r));
        }
        return roomDtoList;
    }
}
