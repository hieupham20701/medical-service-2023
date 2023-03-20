package com.medical.app.service.impl;

import com.medical.app.dto.request.RoomRequest;
import com.medical.app.dto.response.RoomResponse;
import com.medical.app.mapper.MapData;
import com.medical.app.model.entity.Room;
import com.medical.app.repository.RoomRepository;
import com.medical.app.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    @Override
    public RoomResponse saveRoom(RoomRequest roomRequest) {
        Room room = MapData.mapOne(roomRequest,Room.class);
        room.setCreatedDate(new Date());
        return MapData.mapOne(roomRepository.save(room),RoomResponse.class);
    }

    @Override
    public List<RoomResponse> getAllRoomResponse() {

        return MapData.mapList(roomRepository.findAll(),RoomResponse.class);
    }

    @Override
    public RoomResponse getRoomById(Integer id) {
        return MapData.mapOne(roomRepository.findById(id).orElse(null), RoomResponse.class);
    }
}
