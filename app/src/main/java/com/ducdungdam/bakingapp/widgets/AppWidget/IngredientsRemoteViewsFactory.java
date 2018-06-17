package com.ducdungdam.bakingapp.widgets.AppWidget;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import com.ducdungdam.bakingapp.R;
import com.ducdungdam.bakingapp.model.Ingredient;
import com.ducdungdam.bakingapp.model.Recipe;
import java.util.List;

class IngredientsRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

  final static String EXTRA_RECIPE = "extra_recipe";

  private List<Ingredient> ingredients = null;
  private Context context;

  IngredientsRemoteViewsFactory(Context applicationContext, Intent intent) {
    this.context = applicationContext;
    Log.d("DUNG", "IngredientsListProvider: bin hier");

    final Recipe recipe = intent.getParcelableExtra(EXTRA_RECIPE);
    if (recipe != null && recipe.getIngredients() != null && recipe.getIngredients().size() > 0) {
      this.ingredients = recipe.getIngredients();
    }
  }

  @Override
  public void onCreate() {

  }

  @Override
  public void onDataSetChanged() {
    Log.d("DUNG", "onDataSetChanged: bin hier");
  }

  @Override
  public void onDestroy() {

  }

  @Override
  public int getCount() {
    return 5;
//    if (ingredients == null) {
//      return 0;
//    } else {
//      return ingredients.size();
//    }
  }

  @Override
  public RemoteViews getViewAt(int position) {
    RemoteViews views = new RemoteViews(context.getPackageName(),
        R.layout.view_widget_ingredient_list_item);

//    views.setTextViewText(R.id.tv_ingredient, ingredients.get(position).getName());
    views.setTextViewText(R.id.tv_ingredient, "es geht");
    return views;
  }

  @Override
  public RemoteViews getLoadingView() {

    return null;
  }

  @Override
  public int getViewTypeCount() {
    return 1;
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public boolean hasStableIds() {
    return true;
  }
}
