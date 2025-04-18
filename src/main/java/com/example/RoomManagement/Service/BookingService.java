package com.example.RoomManagement.Service;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.example.RoomManagement.DTO.BookingRequestDTO;
import com.example.RoomManagement.Entity.Booking;
import com.example.RoomManagement.Entity.Room;
import com.example.RoomManagement.Entity.User;
import com.example.RoomManagement.Repository.BookingRepository;
import com.example.RoomManagement.Repository.RoomRepository;
import com.example.RoomManagement.Repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.awt.print.Book;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Transactional
    public Booking createBooking(BookingRequestDTO bookingRequest) {
        // Find user and room
        User user = userRepository.findById(bookingRequest.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + bookingRequest.getUserId()));

        Room room = roomRepository.findById(bookingRequest.getRoomId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found with id: " + bookingRequest));
//
//        // Check if room is available
//        if (!room.getAvailable()) {
//            new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not available");
//        }

        List<Booking> existingBookings = bookingRepository
                .findByRoomRoomIdAndBookedDate(bookingRequest.getRoomId(), bookingRequest.getBookedDate());

        if (!existingBookings.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, // 409
                    "Booking already exists for the selected date. Please choose a different date."
            );
        }


        // Create new booking
        Booking booking = new Booking();
        booking.setBookingDate(bookingRequest.getBookingDate());
        booking.setBookedDate(bookingRequest.getBookedDate());
        booking.setRentalPeriod(bookingRequest.getRentalPeriod());
        booking.setTimestamp(LocalDateTime.now());

        // Set room availability and status
        room.setAvailable(false);
        room.setStatus("BOOKED FOR "+booking.getBookedDate()); // Set status to "BOOKED"
        roomRepository.save(room); // Save the updated room status

        // Set relationships
        booking.setUser(user);
        booking.setRoom(room);
        booking.setStatus("CONFIRMED");





        // Generate booking ID (you can add @PrePersist in Booking entity similar to others)
        SecureRandom random = new SecureRandom();
        char[] alphabet = NanoIdUtils.DEFAULT_ALPHABET;
        booking.setBookingId("bk_" + NanoIdUtils.randomNanoId(random, alphabet, 8));

        // Update room availability
        room.setAvailable(false);
        roomRepository.save(room);

        // Save booking
        return bookingRepository.save(booking);
    }

    @Transactional(readOnly = true)
    public List<Booking> getUserBookings(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + userId));

        return bookingRepository.findByUserUserId(userId);
    }

    @Transactional(readOnly = true)
    public List<Booking> getRoomBookings(String roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found with id: " + roomId));

        return bookingRepository.findByRoomRoomId(roomId);
    }

    @Transactional
    public Booking updateBookingStatus(String bookingId, String status) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found with id: " + bookingId));

        booking.setStatus(status);

        // If cancelling booking, make room available again
        if ("CANCELLED".equals(status)) {
            Room room = booking.getRoom();
            room.setAvailable(true);
            roomRepository.save(room);
        }

        return bookingRepository.save(booking);
    }
}