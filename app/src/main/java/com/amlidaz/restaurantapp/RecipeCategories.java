package com.amlidaz.restaurantapp;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.amlidaz.restaurantapp.Adapter.CustomWaiterFoodListAdapter;
import com.amlidaz.restaurantapp.model.FoodType;

import java.util.ArrayList;

public class RecipeCategories extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_categories);

        Toolbar toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(RecipeCategories.this, R.color.colorWhite));
        collapsingToolbarLayout.setTitle("Google Toolbar");
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(RecipeCategories.this, R.color.colorWhite));

        Context context = RecipeCategories.this;
        collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(context, R.color.colorAccent));


        ExpandableListView elv = (ExpandableListView) findViewById(R.id.expandableListView1);
        final ArrayList<FoodType> team = getData();
        //CREATE AND BIND TO ADAPTER
        CustomWaiterFoodListAdapter adapter = new CustomWaiterFoodListAdapter(this, team);
        elv.setAdapter(adapter);
        //SET ONCLICK LISTENER
        elv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPos,
                                        int childPos, long id) {
                Toast.makeText(getApplicationContext(), team.get(groupPos).players.get(childPos), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    //ADD AND GET DATA
    private ArrayList<FoodType> getData() {
        FoodType t1 = new FoodType("Man Utd");
        t1.players.add("Wayne Rooney");
        t1.players.add("Van Persie");
        t1.players.add("Ander Herera");
        t1.players.add("Juan Mata");
        FoodType t2 = new FoodType("Arsenal");
        t2.players.add("Aaron Ramsey");
        t2.players.add("Mesut Ozil");
        t2.players.add("Jack Wilshere");
        t2.players.add("Alexis Sanchez");
        FoodType t3 = new FoodType("Chelsea");
        t3.players.add("John Terry");
        t3.players.add("Eden Hazard");
        t3.players.add("Diego Costa");
        t3.players.add("Oscar");
        ArrayList<FoodType> allTeams = new ArrayList<FoodType>();
        allTeams.add(t1);
        allTeams.add(t2);
        allTeams.add(t3);
        return allTeams;

    }
}


