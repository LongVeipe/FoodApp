package com.haerul.foodsapp.view.searchMeals;

import com.haerul.foodsapp.model.Meals;

import java.util.List;

public interface SearchView {
    void showLoading();
    void hideLoading();
    void setMeals(List<Meals.Meal> meals);
    void onErrorLoading(String message);
}
