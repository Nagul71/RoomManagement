package com.example.RoomManagement.Controller;
import com.example.RoomManagement.DTO.LoginRequest;
import com.example.RoomManagement.Entity.User;
import com.example.RoomManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
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
    public ResponseEntity<User> loginUser(@RequestBody LoginRequest request) {
        User user = userService.loginUser(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(user); // This includes the userId
    }

}
