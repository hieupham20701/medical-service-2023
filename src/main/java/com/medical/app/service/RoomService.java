package com.medical.app.service;

import com.medical.app.dto.request.RoomRequest;
import com.medical.app.dto.response.RoomResponse;

import java.util.List;

public interface RoomService {

    RoomResponse saveRoom(RoomRequest roomRequest);
    List<RoomResponse> getAllRoomResponse();
    RoomResponse  getRoomById(Integer id);
}
