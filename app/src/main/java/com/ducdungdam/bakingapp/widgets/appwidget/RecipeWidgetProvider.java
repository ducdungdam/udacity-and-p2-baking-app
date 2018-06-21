package com.ducdungdam.bakingapp.widgets.appwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import com.ducdungdam.bakingapp.R;
import com.ducdungdam.bakingapp.model.Recipe;
import com.google.gson.Gson;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidgetProvider extends AppWidgetProvider {

  final static String EXTRA_RECIPE_JSON = "extra_recipe_json";

  static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
      int appWidgetId, Recipe recipe) {
    RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_recipe);
    views.setTextViewText(R.id.tv_recipe_name, recipe == null ? "" : recipe.getName());

    Intent intent = new Intent(context, RecipeWidgetService.class);
    if (recipe != null) {
      intent.putExtra(EXTRA_RECIPE_JSON, new Gson().toJson(recipe));
    }
    views.setRemoteAdapter(R.id.lv_ingredients, intent);
    appWidgetManager.updateAppWidget(appWidgetId, views);
  }
}

