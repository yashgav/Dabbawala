package com.example.dabbawala.service;

import com.example.dabbawala.model.Booking;
import com.example.dabbawala.model.User;
import com.example.dabbawala.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking bookMeal(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> findBookingsByCustomer(User customer) {
        return bookingRepository.findByCustomer(customer);
    }
}
