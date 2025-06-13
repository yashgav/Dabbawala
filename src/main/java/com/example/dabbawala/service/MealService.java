package com.example.dabbawala.service;

import com.example.dabbawala.model.Meal;
import com.example.dabbawala.model.User;
import com.example.dabbawala.repository.MealRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MealService {

    private final MealRepository mealRepository;

    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public Meal addMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    public List<Meal> findAllMeals() {
        return mealRepository.findAll();
    }

    public List<Meal> findByCook(User cook) {
        return mealRepository.findByCook(cook);
    }

    public Optional<Meal> findById(Long mealId) {
        return mealRepository.findById(mealId);
    }

}
