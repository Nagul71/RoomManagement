package com.example.RoomManagement.Controller;
import com.example.RoomManagement.DTO.RoomCreationDTO;
import com.example.RoomManagement.Entity.Room;
import com.example.RoomManagement.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rooms")
@CrossOrigin(origins = "http://localhost:5173")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/{userId}/rooms")
    public ResponseEntity<Room> createRoomForUser(
            @PathVariable String userId,
            @RequestBody RoomCreationDTO roomRequest) {

        Room createdRoom = roomService.createRoom(userId, roomRequest);
        return ResponseEntity.ok(createdRoom);
        }

    @GetMapping("/get/{userId}/rooms")
    public ResponseEntity<List<Room>> getUserRooms(@PathVariable String userId) {
        List<Room> rooms = roomService.getRoomsCreatedByUser(userId);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/getallrooms")
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }
    @GetMapping("/{roomId}")
    public ResponseEntity <Optional<Room>> getRoomById(@PathVariable String roomId) {
        Optional<Room> rooms = roomService.getRoomById(roomId);
        return ResponseEntity.ok(rooms);
    }

}
