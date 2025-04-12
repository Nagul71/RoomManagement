package com.example.RoomManagement.Repository;
import com.example.RoomManagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
