package com.ducdungdam.bakingapp.widgets.AppWidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import com.ducdungdam.bakingapp.R;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidgetProvider extends AppWidgetProvider {

  static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
      int appWidgetId) {

    // Construct the RemoteViews object
    RemoteViews views = new RemoteViews(context.getPackageName(),
        R.layout.widget_recipe);

    Intent intent = new Intent(context, RecipeWidgetService.class);
    views.setRemoteAdapter(R.id.lv_ingredients, intent);

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views);
  }

  @Override
  public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
    // There may be multiple widgets active, so update all of them
    Log.d("DUNG", "onUpdate: bin hier");
    for (int appWidgetId : appWidgetIds) {
      updateAppWidget(context, appWidgetManager, appWidgetId);
    }
  }

  @Override
  public void onEnabled(Context context) {
    // Enter relevant functionality for when the first widget is created
  }

  @Override
  public void onDisabled(Context context) {
    // Enter relevant functionality for when the last widget is disabled
  }
}

