package com.example.RoomManagement.Controller;


import com.example.RoomManagement.DTO.RoomCreationDTO;
import com.example.RoomManagement.Entity.Room;
import com.example.RoomManagement.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/{userId}/rooms")
    public String createRoomForUser(
            @PathVariable String userId,
            @RequestBody RoomCreationDTO roomRequest) {

        Room createdRoom = roomService.createRoom(userId, roomRequest);
        return "Room Created Successfully";
        }

    @GetMapping("/get/{userId}/rooms")
    public ResponseEntity<List<Room>> getUserRooms(@PathVariable String userId) {
        List<Room> rooms = roomService.getRoomsCreatedByUser(userId);
        return ResponseEntity.ok(rooms);
    }

}
