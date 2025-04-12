package com.example.RoomManagement.Repository;

import com.example.RoomManagement.Controller.BookingController;
import com.example.RoomManagement.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, String> {
}
