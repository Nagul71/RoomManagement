package com.example.RoomManagement.Repository;

import com.example.RoomManagement.Entity.Images;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagesRepository extends JpaRepository<Images, String> {
    List<Images> findByRoomRoomId(String roomId);
}
