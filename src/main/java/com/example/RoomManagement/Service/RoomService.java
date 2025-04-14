package com.example.RoomManagement.Service;
import com.example.RoomManagement.Entity.Room;
import com.example.RoomManagement.Entity.User;
import com.example.RoomManagement.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepo;

}
