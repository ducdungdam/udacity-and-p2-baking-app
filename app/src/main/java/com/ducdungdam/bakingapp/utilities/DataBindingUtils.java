package com.ducdungdam.bakingapp.utilities;

import android.databinding.BindingAdapter;
import android.widget.ImageView;
import com.ducdungdam.bakingapp.R;
import com.squareup.picasso.Picasso;

public class DataBindingUtils {
  /**
   * Loads an recipe image by a given url to an ImageView.
   *
   * @param view ImageView in which the Image is load into
   * @param imageUrl Url of recipe image
   */
  @BindingAdapter("setRecipeImage")
  public static void setRecipeImage(ImageView view, String imageUrl) {
    try {
      Picasso.with(view.getContext())
          .load(imageUrl)
          .error(R.drawable.img_recipe_card)
          .placeholder(R.drawable.img_recipe_card)
          .into(view);
    } catch (IllegalArgumentException e) {
      Picasso.with(view.getContext()).load(R.drawable.img_recipe_card);
    }
  }

  /**
   * Loads an step thumbnail by a given url to an ImageView.
   *
   * @param view ImageView in which the Image is load into
   * @param thumbnailUrl Url of step thumbnail
   */
  @BindingAdapter("setStepThumbnail")
  public static void setStepThumbnail(ImageView view, String thumbnailUrl) {
    try {
      Picasso.with(view.getContext())
          .load(thumbnailUrl)
          .error(R.drawable.img_recipe_card)
          .placeholder(R.drawable.img_recipe_card)
          .into(view);
    } catch (IllegalArgumentException e) {
      Picasso.with(view.getContext()).load(R.drawable.img_recipe_card);
    }
  }

}
