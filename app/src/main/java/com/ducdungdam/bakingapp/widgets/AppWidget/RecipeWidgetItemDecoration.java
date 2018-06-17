package com.ducdungdam.bakingapp.widgets.AppWidget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.view.View;
import com.ducdungdam.bakingapp.R;

public class RecipeWidgetItemDecoration extends ItemDecoration {

  private final int paddingVertical;
  private final int paddingHorizontal;

  public RecipeWidgetItemDecoration(Context context) {
    this.paddingVertical = (int) context.getResources().getDimension(R.dimen.recipe_item_spacing);
    this.paddingHorizontal = (int) context.getResources().getDimension(R.dimen.recipe_item_spacing);
  }

  @Override
  public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
      RecyclerView.State state) {
    int position = parent.getChildAdapterPosition(view);

    int top = 0;

    if (position == 0) {
      top = paddingVertical;
    }

    outRect.set(paddingHorizontal, top, paddingHorizontal, paddingVertical);

  }
}
