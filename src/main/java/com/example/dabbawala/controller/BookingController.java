package com.example.dabbawala.controller;

import com.example.dabbawala.model.Booking;
import com.example.dabbawala.model.Meal;
import com.example.dabbawala.model.User;
import com.example.dabbawala.model.Role;
import com.example.dabbawala.service.BookingService;
import com.example.dabbawala.service.MealService;
import com.example.dabbawala.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final UserService userService;
    private final MealService mealService;

    public BookingController(BookingService bookingService, UserService userService, MealService mealService) {
        this.bookingService = bookingService;
        this.userService = userService;
        this.mealService = mealService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> bookMeal(@RequestParam String phone, @RequestBody Booking booking) {
        User customer = userService.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (customer.getRole() != Role.CUSTOMER) {
            return ResponseEntity.status(403).body("Forbidden: Only Customers can book.");
        }
        booking.setCustomer(customer);
        Meal meal = mealService.findById(booking.getMeal().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Meal not found"));
        booking.setMeal(meal);
        booking.setTotalPrice(booking.getQty() * meal.getPrice());

        Booking saved = bookingService.bookMeal(booking);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/by-customer/{phone}")
    public List<Booking> findBookingsByCustomer(@PathVariable String phone) {
        User customer = userService.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return bookingService.findBookingsByCustomer(customer);
    }
}
