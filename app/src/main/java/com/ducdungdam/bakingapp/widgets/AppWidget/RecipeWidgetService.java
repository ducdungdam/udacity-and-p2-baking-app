package com.ducdungdam.bakingapp.widgets.AppWidget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class RecipeWidgetService extends RemoteViewsService {
  @Override
  public RemoteViewsFactory onGetViewFactory(Intent intent) {
    return new IngredientsRemoteViewsFactory(this.getApplicationContext(), intent);
  }
}
