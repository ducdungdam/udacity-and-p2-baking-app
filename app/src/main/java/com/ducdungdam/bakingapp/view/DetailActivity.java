package com.ducdungdam.bakingapp.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.ducdungdam.bakingapp.R;
import com.ducdungdam.bakingapp.databinding.ActivityDetailBinding;
import com.ducdungdam.bakingapp.model.Recipe;

public class DetailActivity extends AppCompatActivity {
  ActivityDetailBinding rootView;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Intent intent = getIntent();
    Recipe recipe = intent.getParcelableExtra(MainActivity.EXTRA_RECIPE);

    Log.d("DUNG", "onCreate: intent "+recipe.getName());
    Log.d("DUNG", "onCreate: intent "+recipe.getIngredients().get(0).getName());

    rootView = DataBindingUtil.setContentView(this, R.layout.activity_detail);

  }
}
