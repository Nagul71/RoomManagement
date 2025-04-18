package com.example.RoomManagement.DTO;

import com.example.RoomManagement.Entity.Booking;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingRequestDTO {
    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    private String bookingId;
    private LocalDate bookingDate;
    private LocalDate bookedDate;
    private String rentalPeriod;
    private String userId;
    private String roomId;

    // Constructor from Booking entity
    public BookingRequestDTO(Booking booking) {
        this.bookingDate = booking.getBookingDate();
        this.bookedDate = booking.getBookedDate();
        this.rentalPeriod = booking.getRentalPeriod();
        this.userId = booking.getUser().getUserId();
        this.roomId = booking.getRoom().getRoomId();
    }

    public BookingRequestDTO()
    {

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

    public String getRentalPeriod() {
        return rentalPeriod;
    }

    public void setRentalPeriod(String rentalPeriod) {
        this.rentalPeriod = rentalPeriod;
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