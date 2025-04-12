package com.example.RoomManagement.Controller;
import com.example.RoomManagement.Entity.User;
import com.example.RoomManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public User adduser(@RequestBody User user)
    {
        return userService.adduser(user);
    }

}
