package com.medical.app.controller;

import com.medical.app.dto.request.RoomRequest;
import com.medical.app.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;
    @GetMapping
    public ResponseEntity<?> getAllRoom(){
        return ResponseEntity.ok().body(roomService.getAllRoomResponse());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoomById(@PathVariable Integer id){
        return ResponseEntity.ok().body(roomService.getRoomById(id));
    }

    @PostMapping
    public ResponseEntity<?> saveRoom(@RequestBody RoomRequest roomRequest){
        return ResponseEntity.ok().body(roomService.saveRoom(roomRequest));
    }
}
