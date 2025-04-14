package com.example.RoomManagement.Controller;


import com.example.RoomManagement.Entity.Room;
import com.example.RoomManagement.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/addroom")
    public String addroom(@RequestBody Room room)
    {
        roomService.addroom(room);
        return "Room Created Successfully";
    }
}
