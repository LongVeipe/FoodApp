/*-----------------------------------------------------------------------------
 - Developed by Haerul Muttaqin                                               -
 - Last modified 4/7/19 5:49 PM                                               -
 - Subscribe : https://www.youtube.com/haerulmuttaqin                         -
 - Copyright (c) 2019. All rights reserved                                    -
 -----------------------------------------------------------------------------*/
package com.haerul.foodsapp.view.detail;

import com.haerul.foodsapp.model.Meals;

import java.util.List;

public interface DetailView {
    void hideLoading();
    void showLoading();
    void setMeals(Meals.Meal meal);
    void onErrorLoading(String message);
}
