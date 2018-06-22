package com.ducdungdam.bakingapp.utilities;

import android.databinding.BindingAdapter;
import android.widget.ImageView;
import com.ducdungdam.bakingapp.R;
import com.squareup.picasso.Picasso;

public class DataBindingUtils {
  /**
   * Loads an Step Thumbnail by a given Url to an ImageView.
   *
   * @param view ImageView in which the Image is load into
   * @param thumbnailUrl Url of step thumbnail
   */
  @BindingAdapter("setStepThumbnail")
  public static void setStepThumbnail(ImageView view, String thumbnailUrl) {
    Picasso.with(view.getContext())
        .load(thumbnailUrl)
        .error(android.R.color.transparent)
        .placeholder(R.drawable.img_recipe_card)
        .into(view);
  }

}
