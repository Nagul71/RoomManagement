package com.example.RoomManagement.Entity;

import jakarta.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

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
