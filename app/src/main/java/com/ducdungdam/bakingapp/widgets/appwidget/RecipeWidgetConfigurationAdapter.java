package com.ducdungdam.bakingapp.widgets.appwidget;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.ducdungdam.bakingapp.R;
import com.ducdungdam.bakingapp.databinding.ViewWidgetConfigurationRecipeListItemBinding;
import com.ducdungdam.bakingapp.model.Recipe;
import java.util.List;

public class RecipeWidgetConfigurationAdapter extends RecyclerView.Adapter<ViewHolder> {

  private List<Recipe> recipes;
  private OnItemClickListener onItemClickListener = null;

  public RecipeWidgetConfigurationAdapter(List<Recipe> recipes) {
    super();
    this.recipes = recipes;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new RecipeViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.view_widget_configuration_recipe_list_item, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Recipe r = recipes.get(position);
    ((RecipeViewHolder) holder).bind(r);
  }

  @Override
  public int getItemCount() {
    if (recipes == null) {
      return 0;
    } else {
      return recipes.size();
    }
  }

  public void setOnItemClickListener(OnItemClickListener l) {
    this.onItemClickListener = l;
  }

  public interface OnItemClickListener {

    void onItemClick(Recipe recipe);
  }

  class RecipeViewHolder extends ViewHolder implements OnClickListener {

    final ViewWidgetConfigurationRecipeListItemBinding rootView;

    RecipeViewHolder(View itemView) {
      super(itemView);
      rootView = DataBindingUtil.bind(itemView);
    }

    private void bind(Recipe recipe) {
      rootView.setRecipe(recipe);
      rootView.getRoot().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      if (onItemClickListener != null) {
        onItemClickListener.onItemClick(rootView.getRecipe());
      }
    }
  }
}
