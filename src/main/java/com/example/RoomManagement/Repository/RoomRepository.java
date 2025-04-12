package com.example.RoomManagement.Repository;
import com.example.RoomManagement.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,String> {
}
