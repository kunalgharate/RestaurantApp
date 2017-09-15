package com.amlidaz.restaurantapp;

/**
 * Created by admin on 15/09/17.
 */

import android.content.Context;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

public class RecipeAdapter extends FirebaseRecyclerAdapter<RecipeObject, RecipeHolder> {
    private static final String TAG = RecipeAdapter.class.getSimpleName();
    private Context context;

    public RecipeAdapter(Class<RecipeObject> modelClass, int modelLayout, Class<RecipeHolder> viewHolderClass, DatabaseReference ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.context = context;
    }

    @Override
    protected void populateViewHolder(RecipeHolder viewHolder, RecipeObject model, int position) {
        viewHolder.recipeName.setText(model.getRecipename());
        Glide.with(context).load(model.getRecipeImageUrl()).into(viewHolder.recipeImage);
    }
}
