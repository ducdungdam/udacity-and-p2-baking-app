package com.ducdungdam.bakingapp.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.ducdungdam.bakingapp.model.Step;
import java.util.List;

public class StepViewModel extends ViewModel {

  private MutableLiveData<Integer> currentPosition;
  private MutableLiveData<List<Step>> steps;

  public MutableLiveData<List<Step>> getSteps() {
    if (steps == null) {
      steps = new MutableLiveData<>();
    }
    return steps;
  }

  public MutableLiveData<Integer> getCurrentPosition() {
    if (currentPosition == null) {
      currentPosition = new MutableLiveData<>();
    }
    return currentPosition;
  }

  public Step getStep() {
    if (steps.getValue() == null || currentPosition.getValue() == null) {
      return null;
    }
    return steps.getValue().get(currentPosition.getValue());
  }
}
