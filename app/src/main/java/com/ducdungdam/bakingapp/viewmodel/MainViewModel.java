package com.ducdungdam.bakingapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import com.ducdungdam.bakingapp.data.RecipeRepository;
import com.ducdungdam.bakingapp.model.Recipe;
import java.util.List;

public class MainViewModel extends ViewModel {
  private LiveData<List<Recipe>> recipes;

  public LiveData<List<Recipe>> getRecipes() {
    if (recipes == null) {
      recipes = RecipeRepository.getRecipes();
    }
    return recipes;
  }
}
