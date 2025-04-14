package com.example.RoomManagement.Controller;
import com.example.RoomManagement.DTO.BookingRequestDTO;
import com.example.RoomManagement.Entity.Booking;
import com.example.RoomManagement.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/createbooking")
    public String createBooking(
            @RequestParam String userId,
            @RequestParam String roomId,
            @RequestBody BookingRequestDTO bookingRequest) {

        Booking booking = bookingService.createBooking(userId, roomId, bookingRequest);
        return "Room Booked";
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingRequestDTO>> getUserBookings(@PathVariable String userId) {
        List<Booking> bookings = bookingService.getUserBookings(userId);
        List<BookingRequestDTO> response = bookings.stream()
                .map(BookingRequestDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<Booking>> getRoomBookings(@PathVariable String roomId) {
        List<Booking> bookings = bookingService.getRoomBookings(roomId);
        return ResponseEntity.ok(bookings);
    }

    @PatchMapping("/{bookingId}/status")
    public ResponseEntity<Booking> updateBookingStatus(
            @PathVariable String bookingId,
            @RequestParam String status) {

        Booking booking = bookingService.updateBookingStatus(bookingId, status);
        return ResponseEntity.ok(booking);
    }
}