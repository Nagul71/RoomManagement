package com.example.RoomManagement.Service;
import com.example.RoomManagement.Entity.User;
import com.example.RoomManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public User adduser(User user) {
        return userRepo.save(user);
    }
}
