package com.group.capstone.attendance.service.Room;

import com.group.capstone.attendance.model.Room.dto.RoomDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomService {
    public List<RoomDto> getAllRoomInfo();
}
