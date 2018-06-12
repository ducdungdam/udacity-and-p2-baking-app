package com.ducdungdam.bakingapp.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.view.View;
import com.ducdungdam.bakingapp.R;

public class IngredientItemDecoration extends ItemDecoration {

  private final int paddingVertical;

  public IngredientItemDecoration(Context context) {
    this.paddingVertical = (int) context.getResources().getDimension(R.dimen.recipe_item_spacing);
  }

  @Override
  public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
      RecyclerView.State state) {
    int position = parent.getChildAdapterPosition(view);
    int top = 0;

    if (position == 0) {
      top = paddingVertical;
    }

    outRect.set(0, top, 0, paddingVertical);

  }
}
