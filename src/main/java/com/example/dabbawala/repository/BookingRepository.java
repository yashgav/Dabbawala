package com.example.dabbawala.repository;

import com.example.dabbawala.model.Booking;
import com.example.dabbawala.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByCustomer(User customer);
}
