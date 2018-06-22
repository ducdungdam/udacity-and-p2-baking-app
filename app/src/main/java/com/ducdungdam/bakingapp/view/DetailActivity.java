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

  private boolean isTablet;
  private DetailViewModel model;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    isTablet = getResources().getBoolean(R.bool.isTablet);

    Intent intent = getIntent();
    final Recipe recipe = intent.getParcelableExtra(MainActivity.EXTRA_RECIPE);

    ActivityDetailBinding rootView = DataBindingUtil.setContentView(this, R.layout.activity_detail);

    setSupportActionBar(rootView.toolbar);
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
    rootView.setRecipe(recipe);

    model = ViewModelProviders.of(this).get(DetailViewModel.class);

    model.getRecipe().setValue(recipe);

    if (!isTablet) {
      DetailFragmentPagerAdapter adapter = new DetailFragmentPagerAdapter(this,
          getSupportFragmentManager());
      rootView.viewPager.setAdapter(adapter);
      rootView.tabLayoutRecipeDetail.setupWithViewPager(rootView.viewPager);
      if (actionBar != null) {
        actionBar.setDisplayShowTitleEnabled(false);
      }
    } else {
      ((DetailStepsFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_steps))
          .setOnStepClickListener(this);
      model.getSteps().setValue(recipe.getSteps());
      model.getCurrentPosition().setValue(0);
    }
  }

  @Override
  public void onStepClick(List<Step> steps, int position) {
    if (isTablet) {
      model.getCurrentPosition().setValue(position);
    } else {
      Intent intent = new Intent(this, StepActivity.class);
      intent.putParcelableArrayListExtra(EXTRA_STEPS, (ArrayList<Step>) steps);
      intent.putExtra(EXTRA_CURRENT_STEP_POSITION, position);
      startActivity(intent);
    }
  }
}
