package com.amlidaz.restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class WaiterDashBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter_dash_board);


    }

    public void onButtonClicker(View view) {
        startActivity(new Intent(WaiterDashBoard.this, RecipeCategories.class));
        finish();

    }
}
