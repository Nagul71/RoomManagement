package com.example.RoomManagement.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "mobile_no", nullable = false, unique = true)
    private String mobileNo;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "profile_info")
    private String profileInfo;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @PrePersist
    public void generateId() {
        this.timestamp = LocalDateTime.now();
        this.userId = UUID.randomUUID().toString();
    }
    // One user can post many rooms
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> rooms = new ArrayList<>();

    // Constructors
    public User() {}

    public User(String userId, String name, String email, String mobileNo, String password, String profileInfo, LocalDateTime timestamp) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.mobileNo = mobileNo;
        this.password = password;
        this.profileInfo = profileInfo;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileInfo() {
        return profileInfo;
    }

    public void setProfileInfo(String profileInfo) {
        this.profileInfo = profileInfo;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
