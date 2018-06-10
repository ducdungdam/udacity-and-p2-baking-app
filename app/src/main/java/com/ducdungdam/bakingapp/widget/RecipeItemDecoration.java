package com.ducdungdam.bakingapp.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.view.View;
import com.ducdungdam.bakingapp.R;

public class RecipeItemDecoration extends ItemDecoration {

  private final int paddingVertical;
  private final int paddingHorizontal;

  public RecipeItemDecoration(Context context) {
    this.paddingVertical = (int) context.getResources().getDimension(R.dimen.recipe_item_spacing);
    this.paddingHorizontal = (int) context.getResources().getDimension(R.dimen.recipe_item_spacing);
  }

  @Override
  public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
      RecyclerView.State state) {
    int position = parent.getChildAdapterPosition(view);
    int left = 0;
    int top = 0;
    int right = 0;
    int bottom = 0;

    if (parent.getLayoutManager() instanceof GridLayoutManager &&
        ((GridLayoutManager) parent.getLayoutManager()).getSpanCount() > 0) {
      int spanCount = ((GridLayoutManager) parent.getLayoutManager()).getSpanCount();
      top = paddingVertical;
      right = paddingHorizontal;

      if (position % spanCount == 0) {
        left = paddingHorizontal;
      }
    } else {
      top = paddingVertical;

      if (position == parent.getChildCount() - 1) {
        bottom = paddingVertical;
      }
    }

    outRect.set(left, top, right, bottom);

  }
}
