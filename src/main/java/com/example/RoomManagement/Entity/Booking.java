package com.example.RoomManagement.Entity;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import jakarta.persistence.*;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @Column(name = "booking_id", nullable = false, unique = true)
    private String bookingId;

    @Column(name = "booking_date")
    private LocalDate bookingDate;

    @Column(name = "booked_date")
    private LocalDate bookedDate;

    @Column(name = "status")
    private String status;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "rental_period")
    private String rentalPeriod;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    public Booking() {}

    public Booking(String bookingId, LocalDate bookingDate, LocalDate bookedDate, String status, LocalDateTime timestamp, Double rating, String rentalPeriod, User user, Room room) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.bookedDate = bookedDate;
        this.status = status;
        this.timestamp = timestamp;
        this.rating = rating;
        this.rentalPeriod = rentalPeriod;
        this.user = user;
        this.room = room;
    }

    @PrePersist
    public void generateId() {
        this.timestamp = LocalDateTime.now();

        SecureRandom random = new SecureRandom();
        char[] alphabet = NanoIdUtils.DEFAULT_ALPHABET;
        int size = 8;

        this.bookingId = "bk_" + NanoIdUtils.randomNanoId(random, alphabet, size);
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
