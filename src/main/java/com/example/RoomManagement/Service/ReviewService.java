package com.example.RoomManagement.Service;

import com.example.RoomManagement.DTO.ReviewDTO;
import com.example.RoomManagement.Entity.Review;
import com.example.RoomManagement.Entity.Room;
import com.example.RoomManagement.Entity.User;
import com.example.RoomManagement.Repository.ReviewRepository;
import com.example.RoomManagement.Repository.RoomRepository;
import com.example.RoomManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Transactional
    public Review addReview(ReviewDTO reviewDTO) {
        User user = userRepository.findById(reviewDTO.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Room room = roomRepository.findById(reviewDTO.getRoomId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found"));

        Review review = new Review();
        review.setReviewDesc(reviewDTO.getReviewDesc());
        review.setRevCreatedAt(LocalDateTime.now());
        review.setTimestamp(LocalDateTime.now());
        review.setUser(user);
        review.setRating(reviewDTO.getRating());
        review.setRoom(room);

        return reviewRepository.save(review);
    }

    @Transactional(readOnly = true)
    public List<Review> getReviewsByUserId(String userId) {
        return reviewRepository.findByUserUserId(userId);
    }

    @Transactional(readOnly = true)
    public List<Review> getReviewsByRoomId(String roomId) {
        return reviewRepository.findByRoomRoomId(roomId);
    }

    @Transactional
    public void deleteReview(String reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found"));
        reviewRepository.delete(review);
    }
}