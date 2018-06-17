package com.ducdungdam.bakingapp.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.ducdungdam.bakingapp.R;
import com.ducdungdam.bakingapp.adapter.RecipeAdapter;
import com.ducdungdam.bakingapp.adapter.RecipeAdapter.OnItemClickListener;
import com.ducdungdam.bakingapp.databinding.ActivityMainBinding;
import com.ducdungdam.bakingapp.model.Recipe;
import com.ducdungdam.bakingapp.viewmodel.MainViewModel;
import com.ducdungdam.bakingapp.widgets.RecipeItemDecoration;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  public final static String EXTRA_RECIPE = "extra_recipe";

  private ActivityMainBinding rootView;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    rootView = DataBindingUtil.setContentView(this, R.layout.activity_main);

    MainViewModel model = ViewModelProviders.of(this).get(MainViewModel.class);
    model.getRecipes().observe(this, new Observer<List<Recipe>>() {
      @Override
      public void onChanged(@Nullable List<Recipe> recipes) {
        if (recipes != null) {
          if (rootView.rvRecipeList.getAdapter() == null) {
            RecipeAdapter adapter = new RecipeAdapter(recipes);
            adapter.setOnItemClickListener(new OnItemClickListener() {
              @Override
              public void onItemClick(Recipe recipe) {
                startDetailActivity(recipe);
              }
            });
            rootView.rvRecipeList.setAdapter(adapter);
            rootView.rvRecipeList.addItemDecoration(new RecipeItemDecoration(MainActivity.this));
          } else {
            RecipeAdapter adapter = (RecipeAdapter) rootView.rvRecipeList.getAdapter();
            adapter.setRecipeList(recipes);
          }
        }
      }
    });
  }

  private void startDetailActivity(Recipe recipe) {
    Intent intent = new Intent(this, DetailActivity.class);
    intent.putExtra(EXTRA_RECIPE, recipe);
    startActivity(intent);
  }
}
