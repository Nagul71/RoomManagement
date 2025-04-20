package com.example.RoomManagement.Service;
import com.example.RoomManagement.DTO.RoomCreationDTO;
import com.example.RoomManagement.DTO.RoomUpdateDTO;
import com.example.RoomManagement.Entity.Room;
import com.example.RoomManagement.Entity.User;
import com.example.RoomManagement.Repository.RoomRepository;
import com.example.RoomManagement.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Room createRoom(String userId, RoomCreationDTO roomRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + userId));

        // Create new room
        Room room = new Room();
        room.setSquareFeet(roomRequest.getSquareFeet());
        room.setLocation(roomRequest.getLocation());
        room.setStatus(roomRequest.getStatus());
        room.setBeds(roomRequest.getBeds());
        room.setAvailable(roomRequest.getAvailable());
        room.setAcOrNonAc(roomRequest.getAcOrNonAc());
        room.setPrice(roomRequest.getPrice());

        // Set the user relationship
        room.setUser(user);

        // The room ID and timestamp will be generated automatically by @PrePersist

        // Save the room
        Room savedRoom = roomRepository.save(room);

        // Add the room to the user's list of rooms (maintain bidirectional relationship)
        user.getRooms().add(savedRoom);
        userRepository.save(user);

        return savedRoom;
    }

    public List<Room> getRoomsCreatedByUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + userId));


        return user.getRooms();
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Optional<Room> getRoomById(String roomId) {
        return roomRepository.findById(roomId);
    }

    public Optional<Room> deleteroombyId(String roomId) {
        Optional<Room> roomOptional = roomRepository.findById(roomId);
        roomOptional.ifPresent(room -> roomRepository.deleteById(roomId));
        return roomOptional;
    }

    @Transactional
    public Room updateRoom(String roomId, RoomUpdateDTO roomUpdateDTO) {
        Room existingRoom = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found with id: " + roomId));

        // Update only the fields that are provided in the DTO
        if (roomUpdateDTO.getSquareFeet() != null) {
            existingRoom.setSquareFeet(roomUpdateDTO.getSquareFeet());
        }
        if (roomUpdateDTO.getLocation() != null) {
            existingRoom.setLocation(roomUpdateDTO.getLocation());
        }
        if (roomUpdateDTO.getStatus() != null) {
            existingRoom.setStatus(roomUpdateDTO.getStatus());
        }
        if (roomUpdateDTO.getBeds() != null) {
            existingRoom.setBeds(roomUpdateDTO.getBeds());
        }
        if (roomUpdateDTO.getAvailable() != null) {
            existingRoom.setAvailable(roomUpdateDTO.getAvailable());
        }
        if (roomUpdateDTO.getAcOrNonAc() != null) {
            existingRoom.setAcOrNonAc(roomUpdateDTO.getAcOrNonAc());
        }
        if (roomUpdateDTO.getPrice() != null) {
            existingRoom.setPrice(roomUpdateDTO.getPrice());
        }

        return roomRepository.save(existingRoom);
    }
}
