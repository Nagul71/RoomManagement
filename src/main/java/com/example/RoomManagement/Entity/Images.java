package com.example.RoomManagement.Entity;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Entity
@Table(name = "images")
public class Images {

    @Id
    @Column(name = "img_id", nullable = false, unique = true)
    private String imgId;

    @Column(name = "img_url", nullable = false)
    private String imgUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    @JsonIgnore
    private Room room;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    // Constructors
    public Images() {}

    public Images(String imgId, String imgUrl, Room room, LocalDateTime timestamp) {
        this.imgId = imgId;
        this.imgUrl = imgUrl;
        this.room = room;
        this.timestamp = timestamp;
    }

    @PrePersist
    public void generateIdAndTimestamp() {
        this.timestamp = LocalDateTime.now();

        SecureRandom random = new SecureRandom();
        char[] alphabet = NanoIdUtils.DEFAULT_ALPHABET;
        int size = 8;

        this.imgId = "img_" + NanoIdUtils.randomNanoId(random, alphabet, size);
    }

    // Getters and Setters
    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
