package com.example.RoomManagement.Entity;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @Column(name = "review_id", nullable = false, unique = true)
    private String reviewId;

    @Column(name = "review_desc")
    private String reviewDesc;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "rev_created_at")
    private LocalDateTime revCreatedAt;

    @Column(name = "rating")
    private long rating;

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @PrePersist
    public void generateIdAndTimestamp() {
        this.timestamp = LocalDateTime.now();

        SecureRandom random = new SecureRandom();
        char[] alphabet = NanoIdUtils.DEFAULT_ALPHABET;
        int size = 8;

        this.reviewId = "rev_" + NanoIdUtils.randomNanoId(random, alphabet, size);
    }

    public Review() {}

    public Review(String reviewId, String reviewDesc, LocalDateTime timestamp, LocalDateTime revCreatedAt, User user, Room room) {
        this.reviewId = reviewId;
        this.reviewDesc = reviewDesc;
        this.timestamp = timestamp;
        this.revCreatedAt = revCreatedAt;
        this.user = user;
        this.room = room;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewDesc() {
        return reviewDesc;
    }

    public void setReviewDesc(String reviewDesc) {
        this.reviewDesc = reviewDesc;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDateTime getRevCreatedAt() {
        return revCreatedAt;
    }

    public void setRevCreatedAt(LocalDateTime revCreatedAt) {
        this.revCreatedAt = revCreatedAt;
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
