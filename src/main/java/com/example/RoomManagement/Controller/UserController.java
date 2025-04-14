package com.example.RoomManagement.Controller;
import com.example.RoomManagement.DTO.LoginRequest;
import com.example.RoomManagement.Entity.User;
import com.example.RoomManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public String adduser(@RequestBody User user)
    {
        userService.adduser(user);
        return "User Created Successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
        return "User Logged in Successfully";
    }

}
