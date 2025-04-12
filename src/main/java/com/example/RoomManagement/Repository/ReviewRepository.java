package com.example.RoomManagement.Repository;

import com.example.RoomManagement.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, String> {
}
