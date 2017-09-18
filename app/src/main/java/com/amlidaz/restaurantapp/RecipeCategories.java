package com.amlidaz.restaurantapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class RecipeCategories extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_categories);
        // 1. get a reference to recyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recipe_categories);

        // this is data fro recycler view
        ItemData itemsData[] = {new ItemData("Help", R.mipmap.ic_launcher),
                new ItemData("Delete", R.mipmap.ic_launcher),
                new ItemData("Cloud", R.mipmap.ic_launcher),
                new ItemData("Favorite", R.mipmap.ic_launcher),
                new ItemData("Like", R.mipmap.ic_launcher),
                new ItemData("Rating", R.mipmap.ic_launcher)};

        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 3. create an adapter
        MyAdapter mAdapter = new MyAdapter(itemsData);
        // 4. set adapter
        recyclerView.setAdapter(mAdapter);
        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }
}




