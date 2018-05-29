package com.ducdungdam.bakingapp.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import com.ducdungdam.bakingapp.R;
import com.ducdungdam.bakingapp.adapter.RecipeAdapter;
import com.ducdungdam.bakingapp.databinding.ActivityMainBinding;
import com.ducdungdam.bakingapp.model.Recipe;
import com.ducdungdam.bakingapp.viewmodel.MainViewModel;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  ActivityMainBinding rootView;

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
            rootView.rvRecipeList.setAdapter(adapter);
          } else {
            RecipeAdapter adapter = (RecipeAdapter) rootView.rvRecipeList.getAdapter();
            adapter.setRecipeList(recipes);
          }
        }
      }
    });
  }
}
