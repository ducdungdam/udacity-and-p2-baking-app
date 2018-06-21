package com.ducdungdam.bakingapp.idlingResource;

import android.support.test.espresso.IdlingResource;

import android.support.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Idling resource in {@link com.ducdungdam.bakingapp.view.MainActivity} for Espresso test
 */

public class RecipeIdlingResource implements IdlingResource {

  @Nullable
  private volatile ResourceCallback mCallback;
  private AtomicBoolean mIsIdleNow = new AtomicBoolean(true);

  @Override
  public String getName() {
    return this.getClass().getName();
  }

  @Override
  public boolean isIdleNow() {
    return mIsIdleNow.get();
  }

  @Override
  public void registerIdleTransitionCallback(ResourceCallback callback) {
    mCallback = callback;
  }

  public void setIdleState(boolean isIdleNow) {
    mIsIdleNow.set(isIdleNow);
    if (isIdleNow && mCallback != null) {
      mCallback.onTransitionToIdle();
    }
  }
}
