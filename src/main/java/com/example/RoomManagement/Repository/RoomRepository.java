package com.example.RoomManagement.Repository;
import com.example.RoomManagement.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,String> {

    List<Room> findByUserUserId(String userId);

    // Or using @Query annotation
    @Query("SELECT r FROM Room r WHERE r.user.userId = :userId")
    List<Room> findRoomsByUserId(@Param("userId") String userId);
}
