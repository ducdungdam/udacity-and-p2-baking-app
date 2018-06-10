package com.ducdungdam.bakingapp.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import com.ducdungdam.bakingapp.R;
import com.ducdungdam.bakingapp.databinding.ActivityStepBinding;
import com.ducdungdam.bakingapp.model.Step;
import com.ducdungdam.bakingapp.viewmodel.DetailViewModel;
import java.util.List;

public class StepActivity extends AppCompatActivity implements View.OnClickListener {

  ActivityStepBinding rootView;
  DetailViewModel viewModel;


  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Intent intent = getIntent();
    final List<Step> steps = intent.getParcelableArrayListExtra(DetailActivity.EXTRA_STEPS);
    final int currentStepPos = intent.getIntExtra(DetailActivity.EXTRA_CURRENT_STEP_POSITION, 0);

    rootView = DataBindingUtil.setContentView(this, R.layout.activity_step);
    rootView.btnNextStep.setOnClickListener(this);
    rootView.tvPreviousStep.setOnClickListener(this);

    setSupportActionBar(rootView.toolbar);
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setDisplayShowTitleEnabled(false);
    }

    viewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
    viewModel.getSteps().setValue(steps);
    viewModel.getCurrentPosition().setValue(currentStepPos);

    viewModel.getCurrentPosition().observe(this, new Observer<Integer>() {
      @Override
      public void onChanged(@Nullable Integer currentPosition) {
        if (viewModel.getSteps().getValue() == null || currentPosition == null || viewModel.getSteps().getValue().get(currentPosition) == null) {
          return;
        }

        Step step = viewModel.getSteps().getValue().get(currentPosition);
        rootView.setStep(step);
        rootView.setCurrentPosition(currentPosition);
        rootView.setTotalCount(viewModel.getSteps().getValue().size());
      }
    });

    getSupportFragmentManager().beginTransaction()
        .add(rootView.stepContainer.getId(), new StepFragment())
        .commit();

  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        break;
    }
    return true;
  }

  @Override
  public void onClick(View v) {
    if (v == rootView.btnNextStep) {
      Integer currentPosition = viewModel.getCurrentPosition().getValue();
      if (currentPosition != null &&
          viewModel.getSteps().getValue() != null &&
          currentPosition < viewModel.getSteps().getValue().size() - 1) {
        viewModel.getCurrentPosition().setValue(currentPosition + 1);
      }
    }
    else if (v == rootView.tvPreviousStep) {
      Integer currentPosition = viewModel.getCurrentPosition().getValue();
      if (currentPosition != null &&
          viewModel.getSteps().getValue() != null &&
          currentPosition > 0) {
        viewModel.getCurrentPosition().setValue(currentPosition - 1);
      }
    }
  }
}
