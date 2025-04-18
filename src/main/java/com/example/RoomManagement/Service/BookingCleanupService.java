package com.example.RoomManagement.Service;

import com.example.RoomManagement.Entity.Booking;
import com.example.RoomManagement.Entity.Room;
import com.example.RoomManagement.Repository.BookingRepository;
import com.example.RoomManagement.Repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingCleanupService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    // Runs every 10 minutes (cron format: sec min hour day mon weekday)
    @Scheduled(fixedRate = 10 * 60 * 1000) // Every 10 minutes
    @Transactional
    public void cleanupExpiredBookings() {
        List<Booking> allBookings = bookingRepository.findAll();

        for (Booking booking : allBookings) {
            if ("CONFIRMED".equals(booking.getStatus())) {
                LocalDateTime bookedTime = booking.getBookedDate().atStartOfDay();
                String period = booking.getRentalPeriod().split(" ")[0];
                System.out.println(period);// Expected "12" or "24"

                try {
                    long rentalHours = Long.parseLong(period);

                    if (bookedTime != null && bookedTime.plusHours(rentalHours).isBefore(LocalDateTime.now())) {
                        // Time expired, mark booking and room as completed/available
                        booking.setStatus("COMPLETED");

                        Room room = booking.getRoom();
                        room.setAvailable(true);
                        room.setStatus("AVAILABLE");

                        roomRepository.save(room);
                        bookingRepository.save(booking);
                    }
                } catch (NumberFormatException e) {
                    // Log or handle invalid rentalPeriod values if needed
                    System.err.println("Invalid rental period: " + period);
                }
            }
        }
    }
}

