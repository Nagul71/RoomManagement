package com.example.RoomManagement.Repository;

import com.example.RoomManagement.Controller.BookingController;
import com.example.RoomManagement.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, String> {
    List<Booking> findByUserUserId(String userId);

    List<Booking> findByRoomRoomId(String roomId);

    // Optional: Find bookings by status
    List<Booking> findByStatus(String status);

    List<Booking> findByRoomRoomIdAndBookedDateAndStatus(String roomId, LocalDate bookedDate, String status);
}
