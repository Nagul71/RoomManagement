package com.example.RoomManagement.Entity;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @Column(name = "room_id", nullable = false, unique = true)
    private String roomId;

    @Column(name = "sqr_ft")
    private Double squareFeet;

    @PrePersist
    public void generateId() {
        this.timestamp = LocalDateTime.now();

        SecureRandom random = new SecureRandom();
        char[] alphabet = NanoIdUtils.DEFAULT_ALPHABET;
        int size = 8;

        this.roomId = "rm_" + NanoIdUtils.randomNanoId(random, alphabet, size);
    }


    @Column(name = "location")
    private String location;

    @Column(name = "status")
    private String status;

    @Column(name = "beds")
    private Integer beds;

    @Column(name = "available")
    private Boolean available;

    @Column(name = "ac_or_non_ac")
    private String acOrNonAc;

    @Column(name = "price")
    private Double price;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    // Foreign key: user who posted the room
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    // One room can have many images
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Images> images = new ArrayList<>();

    // Constructors
    public Room() {}

    public Room(String roomId, Double squareFeet, String location, String status, Integer beds,
                Boolean available, String acOrNonAc, Double price, LocalDateTime timestamp, User user) {
        this.roomId = roomId;
        this.squareFeet = squareFeet;
        this.location = location;
        this.status = status;
        this.beds = beds;
        this.available = available;
        this.acOrNonAc = acOrNonAc;
        this.price = price;
        this.timestamp = timestamp;
        this.user = user;
    }

    // Getters and Setters
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Double getSquareFeet() {
        return squareFeet;
    }

    public void setSquareFeet(Double squareFeet) {
        this.squareFeet = squareFeet;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getBeds() {
        return beds;
    }

    public void setBeds(Integer beds) {
        this.beds = beds;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getAcOrNonAc() {
        return acOrNonAc;
    }

    public void setAcOrNonAc(String acOrNonAc) {
        this.acOrNonAc = acOrNonAc;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }
}
