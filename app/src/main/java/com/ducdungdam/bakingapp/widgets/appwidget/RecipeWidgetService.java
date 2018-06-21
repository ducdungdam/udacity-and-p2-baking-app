package com.ducdungdam.bakingapp.widgets.appwidget;

import static com.ducdungdam.bakingapp.widgets.appwidget.RecipeWidgetProvider.EXTRA_RECIPE_JSON;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import com.ducdungdam.bakingapp.R;
import com.ducdungdam.bakingapp.model.Ingredient;
import com.ducdungdam.bakingapp.model.Recipe;
import com.google.gson.Gson;
import java.util.List;

public class RecipeWidgetService extends RemoteViewsService {

  @Override
  public RemoteViewsFactory onGetViewFactory(final Intent intent) {
    return new IngredientsRemoteViewsFactory(getApplicationContext(), intent);
  }

  class IngredientsRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private List<Ingredient> ingredients;
    private Context context;

    IngredientsRemoteViewsFactory(Context context, Intent intent) {
      this.context = context;

      Recipe recipe = new Gson()
          .fromJson(intent.getStringExtra(EXTRA_RECIPE_JSON), Recipe.class);

      ingredients = recipe.getIngredients();
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
      if (ingredients == null) {
        return 0;
      }
      return ingredients.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
      if (ingredients == null || ingredients.size() == 0) {
        return null;
      }

      RemoteViews views = new RemoteViews(context.getPackageName(),
          R.layout.view_widget_ingredient_list_item);
      Ingredient ingredient = ingredients.get(position);
      views.setTextViewText(R.id.tv_ingredient_quantity,
          ingredient.getQuantity() + " " + ingredient.getMeasure());
      views.setTextViewText(R.id.tv_ingredient_name, ingredient.getName());

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
}
