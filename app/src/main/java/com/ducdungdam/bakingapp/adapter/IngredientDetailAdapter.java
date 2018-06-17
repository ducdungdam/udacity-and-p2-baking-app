package com.ducdungdam.bakingapp.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ducdungdam.bakingapp.R;
import com.ducdungdam.bakingapp.databinding.ViewDetailIngredientItemBinding;
import com.ducdungdam.bakingapp.model.Ingredient;
import java.util.List;

public class IngredientDetailAdapter extends RecyclerView.Adapter<ViewHolder> {
  private final List<Ingredient> ingredients;

  public IngredientDetailAdapter(List<Ingredient> ingredients) {
    super();
    this.ingredients = ingredients;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientViewHolder(LayoutInflater.from(parent.getContext())
            .inflate(R.layout.view_detail_ingredient_item, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    ((IngredientViewHolder) holder).bind(ingredients.get(position));
  }

  @Override
  public int getItemCount() {
    if(ingredients == null) {
      return 0;
    }
    return ingredients.size();
  }

  private class IngredientViewHolder extends ViewHolder {

    final ViewDetailIngredientItemBinding rootView;

    IngredientViewHolder(View itemView) {
      super(itemView);
      rootView = DataBindingUtil.bind(itemView);
    }

    private void bind(Ingredient ingredient) {
      rootView.setIngredient(ingredient);
    }
  }
}
