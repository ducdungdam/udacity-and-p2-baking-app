package com.ducdungdam.bakingapp.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.ducdungdam.bakingapp.R;
import com.ducdungdam.bakingapp.view.DetailIngredientsFragment;
import com.ducdungdam.bakingapp.view.DetailStepsFragment;

/**
 * Adapter for ViewPager in {@link com.ducdungdam.bakingapp.view.DetailActivity} on mobile screen
 */

public class DetailFragmentPagerAdapter extends FragmentPagerAdapter {

  private final Context mContext;

  public DetailFragmentPagerAdapter(Context context, FragmentManager fm) {
    super(fm);
    mContext = context;
  }

  @Override
  public Fragment getItem(int position) {
    if (position == 0) {
      return new DetailIngredientsFragment();
    } else {
      DetailStepsFragment detailStepsFragment = new DetailStepsFragment();
      return detailStepsFragment;
    }
  }

  @Override
  public int getCount() {
    return 2;
  }

  @Override
  public CharSequence getPageTitle(int position) {
    switch (position) {
      case 0:
        return mContext.getString(R.string.title_ingredient);
      case 1:
        return mContext.getString(R.string.title_steps);
      default:
        return null;
    }
  }
}
