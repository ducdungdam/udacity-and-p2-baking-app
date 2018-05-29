package com.ducdungdam.bakingapp.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ducdungdam.bakingapp.R;
import com.ducdungdam.bakingapp.databinding.ViewRecipeListItemBinding;
import com.ducdungdam.bakingapp.model.Recipe;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<ViewHolder> {
  List<Recipe> recipes = null;

  public RecipeAdapter(List<Recipe> recipes) {
    super();
    this.recipes = recipes;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new RecipeViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.view_recipe_list_item, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Recipe r = recipes.get(position);
    ((RecipeViewHolder) holder).bind(r);
  }

  @Override
  public int getItemCount() {
    if(recipes == null) {
      return 0;
    } else {
      return recipes.size();
    }
  }

  public void setRecipeList(List<Recipe> recipes) {
    this.recipes = recipes;
    notifyDataSetChanged();
  }

  class RecipeViewHolder extends ViewHolder {
    ViewRecipeListItemBinding rootView;

    RecipeViewHolder(View itemView) {
      super(itemView);
      rootView = DataBindingUtil.bind(itemView);
    }

    private void bind(Recipe recipe) {
      rootView.setRecipe(recipe);
    }
  }
}
