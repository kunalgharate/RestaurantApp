package com.amlidaz.restaurantapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

public class RecipeCategories extends AppCompatActivity {

    private static final String TAG = RecipeCategories.class.getSimpleName();
    private RecyclerView recipeRecyclerview;
    private LinearLayoutManager linearLayoutManager;
    private RecipeAdapter mRecipeAdapter;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference childRef;

    public RecipeCategories() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_categories);
    }
}
