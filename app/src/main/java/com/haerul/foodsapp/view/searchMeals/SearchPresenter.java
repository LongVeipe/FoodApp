package com.haerul.foodsapp.view.searchMeals;

import android.util.Log;

import androidx.annotation.NonNull;

import com.haerul.foodsapp.Utils;
import com.haerul.foodsapp.model.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenter {
    private SearchView view;
    public SearchPresenter(SearchView _view){view = _view;}

    void GetMealByName(String Name)
    {
        view.showLoading();
        Call<Meals> mealsCall = Utils.getApi().getMealByName(Name);
        mealsCall.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(@NonNull Call<Meals> call, @NonNull Response<Meals> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {

                    view.setMeals(response.body().getMeals());
                }
                else {
                    view.onErrorLoading(response.message());

                }
            }

            @Override
            public void onFailure(@NonNull Call<Meals> call, @NonNull Throwable t) {

            }
        });
    }
}
