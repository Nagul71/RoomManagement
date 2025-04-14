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

    public User loginUser(String email, String password) {
        User user = userRepo.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user; // valid login
        } else {
            throw new RuntimeException("Invalid email or password");
        }
    }
}
