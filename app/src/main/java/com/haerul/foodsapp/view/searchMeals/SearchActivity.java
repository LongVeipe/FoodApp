package com.haerul.foodsapp.view.searchMeals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.haerul.foodsapp.R;
import com.haerul.foodsapp.adapter.RecyclerViewMealByCategory;
import com.haerul.foodsapp.adapter.RecyclerViewMealByName;
import com.haerul.foodsapp.adapter.ViewPagerCategoryAdapter;
import com.haerul.foodsapp.model.Categories;
import com.haerul.foodsapp.model.Meals;
import com.haerul.foodsapp.view.detail.DetailActivity;
import com.haerul.foodsapp.view.home.HomeActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnFocusChange;
import retrofit2.http.GET;

import static com.haerul.foodsapp.view.home.HomeActivity.EXTRA_DETAIL;
import static com.haerul.foodsapp.view.home.HomeActivity.EXTRA_SEARCH;

public class SearchActivity extends AppCompatActivity implements SearchView{

    @BindView(R.id.recyclerViewMeals)
    RecyclerView recyclerViewMeals;
    @BindView(R.id.searchMeal)
    EditText editTextSearchMeal;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    SearchPresenter presenter;
    RecyclerViewMealByName adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        presenter = new SearchPresenter(this);

        setupEditText();
        setupRecyclerView();
        presenter.GetMealByName(editTextSearchMeal.getText().toString());
    }
    private void setupEditText() {
        Intent intent = getIntent();
        editTextSearchMeal.setText(intent.getStringExtra(HomeActivity.EXTRA_SEARCH));
        editTextSearchMeal.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                presenter.GetMealByName(editTextSearchMeal.getText().toString());
                return false;
            }
        });
    }

    private void setupRecyclerView()
    {

        recyclerViewMeals.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerViewMeals.setClipToPadding(false);
    }


    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setMeals(List<Meals.Meal> meals) {
        adapter = new RecyclerViewMealByName(this, meals);
        recyclerViewMeals.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener((view, position) -> {
            TextView mealName = view.findViewById(R.id.mealName);
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(EXTRA_DETAIL, mealName.getText().toString());
            startActivity(intent);
        });
    }

    @Override
    public void onErrorLoading(String message) {

    }
}