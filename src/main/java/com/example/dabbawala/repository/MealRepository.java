package com.example.dabbawala.repository;

import com.example.dabbawala.model.Meal;
import com.example.dabbawala.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findByCook(User cook);
}
