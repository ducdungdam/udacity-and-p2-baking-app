package com.ducdungdam.bakingapp.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.ducdungdam.bakingapp.model.Recipe;
import com.ducdungdam.bakingapp.model.Step;
import java.util.List;

public class DetailViewModel extends ViewModel{
  private MutableLiveData<Recipe> recipe;
  private MutableLiveData<List<Step>> steps;
  private MutableLiveData<Integer> currentPosition;

  public MutableLiveData<Recipe> getRecipe() {
    if (recipe == null) {
      recipe = new MutableLiveData<>();
    }
    return recipe;
  }

  public MutableLiveData<Integer> getCurrentPosition() {
    if (currentPosition == null) {
      currentPosition = new MutableLiveData<>();
    }
    return currentPosition;
  }


  public MutableLiveData<List<Step>> getSteps() {
    if (steps == null) {
      steps = new MutableLiveData<>();
    }
    return steps;
  }

  public Step getStep() {
    if (steps.getValue() == null || currentPosition.getValue() == null) {
      return null;
    }
    return steps.getValue().get(currentPosition.getValue());
  }
}
