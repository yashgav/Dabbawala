package com.example.dabbawala.controller;

import com.example.dabbawala.model.Meal;
import com.example.dabbawala.model.User;
import com.example.dabbawala.model.Role;
import com.example.dabbawala.service.MealService;
import com.example.dabbawala.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/meals")
public class MealController {

    private final MealService mealService;
    private final UserService userService;

    public MealController(MealService mealService, UserService userService) {
        this.mealService = mealService;
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addMeal(@RequestParam String phone, @RequestBody Meal meal) {
        User user = userService.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getRole() != Role.COOK) {
            return ResponseEntity.status(403).body("Forbidden: Only Cooks can add meal.");
        }

        meal.setCook(user);
        Meal saved = mealService.addMeal(meal);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/all")
    public List<Meal> findAllMeals() {
        return mealService.findAllMeals();
    }
}
