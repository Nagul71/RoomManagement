package com.example.RoomManagement.DTO;

import com.example.RoomManagement.Entity.Booking;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingRequestDTO {
    private String bookingId;
    private LocalDate bookingDate;
    private LocalDate bookedDate;
    private String status;
    private Double rating;
    private String rentalPeriod;
    private LocalDateTime timestamp;
    private String userId;
    private String roomId;

    // Constructor from Booking entity
    public BookingRequestDTO(Booking booking) {
        this.bookingId = booking.getBookingId();
        this.bookingDate = booking.getBookingDate();
        this.bookedDate = booking.getBookedDate();
        this.status = booking.getStatus();
        this.rating = booking.getRating();
        this.rentalPeriod = booking.getRentalPeriod();
        this.timestamp = booking.getTimestamp();
        this.userId = booking.getUser().getUserId();
        this.roomId = booking.getRoom().getRoomId();
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalDate getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(LocalDate bookedDate) {
        this.bookedDate = bookedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getRentalPeriod() {
        return rentalPeriod;
    }

    public void setRentalPeriod(String rentalPeriod) {
        this.rentalPeriod = rentalPeriod;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}