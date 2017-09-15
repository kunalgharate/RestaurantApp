package com.amlidaz.restaurantapp;

/**
 * Created by admin on 15/09/17.
 */

public class RecipeObject {
    String Recipename, RecipeImageUrl;

    public RecipeObject() {

    }

    public String getRecipename() {
        return Recipename;
    }

    public void setRecipename(String recipename) {
        Recipename = recipename;
    }

    public String getRecipeImageUrl() {
        return RecipeImageUrl;
    }

    public void setRecipeImageUrl(String recipeImageUrl) {
        RecipeImageUrl = recipeImageUrl;
    }
}
