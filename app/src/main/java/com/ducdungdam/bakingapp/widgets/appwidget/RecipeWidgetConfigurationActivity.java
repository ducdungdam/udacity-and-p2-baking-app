package com.ducdungdam.bakingapp.widgets.appwidget;

import android.appwidget.AppWidgetManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.ducdungdam.bakingapp.R;
import com.ducdungdam.bakingapp.databinding.ActivityRecipeWidgetConfigurationBinding;
import com.ducdungdam.bakingapp.model.Recipe;
import com.ducdungdam.bakingapp.viewmodel.MainViewModel;
import com.ducdungdam.bakingapp.widgets.appwidget.RecipeWidgetConfigurationAdapter.OnItemClickListener;
import java.util.List;

public class RecipeWidgetConfigurationActivity extends AppCompatActivity {

  ActivityRecipeWidgetConfigurationBinding rootView;
  int appWidgetId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent intent = getIntent();
    Bundle extras = intent.getExtras();
    if (extras != null) {
      appWidgetId = extras.getInt(
          AppWidgetManager.EXTRA_APPWIDGET_ID,
          AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    rootView = DataBindingUtil.setContentView(this, R.layout.activity_recipe_widget_configuration);

    MainViewModel model = ViewModelProviders.of(this).get(MainViewModel.class);
    model.getRecipes().observe(this, new Observer<List<Recipe>>() {
      @Override
      public void onChanged(@Nullable List<Recipe> recipes) {
        if (recipes != null) {
          if (rootView.rvRecipeList.getAdapter() == null) {
            RecipeWidgetConfigurationAdapter adapter = new RecipeWidgetConfigurationAdapter(
                recipes);
            adapter.setOnItemClickListener(new OnItemClickListener() {
              @Override
              public void onItemClick(Recipe recipe) {
                updateWidget(recipe);
              }
            });
            rootView.rvRecipeList.setAdapter(adapter);
            rootView.rvRecipeList.addItemDecoration(
                new RecipeWidgetItemDecoration(RecipeWidgetConfigurationActivity.this));
          }
        }
      }
    });

    setResult(RESULT_CANCELED);
  }

  private void updateWidget(Recipe recipe) {
    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
    RecipeWidgetProvider.updateAppWidget(this, appWidgetManager,
        appWidgetId, recipe);

    Intent resultValue = new Intent();
    resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
    setResult(RESULT_OK, resultValue);
    finish();
  }
}
