package com.ducdungdam.bakingapp.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.ducdungdam.bakingapp.R;
import com.ducdungdam.bakingapp.data.RecipeRepository;
import com.ducdungdam.bakingapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity{
  ActivityMainBinding rootView;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    rootView = DataBindingUtil.setContentView(this, R.layout.activity_main);
  }
}
