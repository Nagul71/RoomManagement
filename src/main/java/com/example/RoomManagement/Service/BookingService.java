package com.example.RoomManagement.Service;

import com.example.RoomManagement.Repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    @Autowired
    public BookingRepository bookingRepo;

}
