package com.amlidaz.restaurantapp.model;

import java.util.ArrayList;

/**
 * Created by admin on 28/09/17.
 */

public class FoodType {
    public String Name;

    public ArrayList<String> players = new ArrayList<String>();

    public FoodType(String Name) {
        this.Name = Name;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return Name;
    }
}
