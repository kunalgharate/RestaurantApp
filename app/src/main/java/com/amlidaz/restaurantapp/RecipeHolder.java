package com.amlidaz.restaurantapp;

/**
 * Created by admin on 15/09/17.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class RecipeHolder extends RecyclerView.ViewHolder {
    private static final String TAG = RecipeHolder.class.getSimpleName();
    public TextView recipeName;
    public ImageView recipeImage;

    public RecipeHolder(View itemView) {
        super(itemView);
        recipeName = (TextView) itemView.findViewById(R.id.recipe_name);
        recipeImage = (ImageView) itemView.findViewById(R.id.recipe_image);
    }
}
