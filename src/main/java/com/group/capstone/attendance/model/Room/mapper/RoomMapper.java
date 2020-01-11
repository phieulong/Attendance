package com.group.capstone.attendance.model.Room.mapper;

import com.group.capstone.attendance.entity.Room;
import com.group.capstone.attendance.model.Room.dto.RoomDto;

public class RoomMapper {
    public static RoomDto toRoomDto (Room room){
        RoomDto roomDto = new RoomDto();
        roomDto.setId(room.getId());
        roomDto.setName(room.getName());
        roomDto.setDescription(room.getDescription());
        return roomDto;
    }
}
