package com.ducdungdam.bakingapp.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import com.ducdungdam.bakingapp.R;
import com.ducdungdam.bakingapp.adapter.DetailFragmentPagerAdapter;
import com.ducdungdam.bakingapp.databinding.ActivityDetailBinding;
import com.ducdungdam.bakingapp.model.Recipe;
import com.ducdungdam.bakingapp.model.Step;
import com.ducdungdam.bakingapp.view.DetailStepsFragment.OnStepClickListener;
import com.ducdungdam.bakingapp.viewmodel.DetailViewModel;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements OnStepClickListener {

  public final static String EXTRA_CURRENT_STEP_POSITION = "extra_current_step_position";
  public final static String EXTRA_STEPS = "extra_steps";

  private boolean isTwoPane = false;
  private DetailViewModel model;

  private ActivityDetailBinding rootView;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Intent intent = getIntent();
    final Recipe recipe = intent.getParcelableExtra(MainActivity.EXTRA_RECIPE);

    rootView = DataBindingUtil.setContentView(this, R.layout.activity_detail);

    setSupportActionBar(rootView.toolbar);
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
    rootView.setRecipe(recipe);

    model = ViewModelProviders.of(this).get(DetailViewModel.class);

    model.getRecipe().setValue(recipe);

    if (rootView.svMasterFlow == null) {
      DetailFragmentPagerAdapter adapter = new DetailFragmentPagerAdapter(this,
          getSupportFragmentManager());
      adapter.setOnStepClickListener(this);
      rootView.viewPager.setAdapter(adapter);
      rootView.tabLayoutRecipeDetail.setupWithViewPager(rootView.viewPager);
      if (actionBar != null) {
        actionBar.setDisplayShowTitleEnabled(false);
      }
    } else {
      isTwoPane = true;
      ((DetailStepsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_steps))
          .setOnStepClickListener(this);
      model.getSteps().setValue(recipe.getSteps());
      model.getCurrentPosition().setValue(0);
    }
  }

  private void startStepActivity(List<Step> steps, int position) {
    Intent intent = new Intent(this, StepActivity.class);
    intent.putParcelableArrayListExtra(EXTRA_STEPS, (ArrayList<Step>) steps);
    intent.putExtra(EXTRA_CURRENT_STEP_POSITION, position);
    startActivity(intent);
  }

  @Override
  public void onStepClick(List<Step> steps, int position) {
    if (isTwoPane) {
      model.getCurrentPosition().setValue(position);
    } else {
      startStepActivity(steps, position);
    }
  }
}
