package com.ducdungdam.bakingapp;


import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.ducdungdam.bakingapp.view.MainActivity;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * This test checks a user clicking on a RecyclerView item in MainActivity which opens up the
 * corresponding DetailActivity.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityScreenTest {

  @Rule
  public ActivityTestRule<MainActivity> activityTestRule =
      new ActivityTestRule<>(MainActivity.class);

  private IdlingResource mIdlingResource;


  // Registers any resource that needs to be synchronized with Espresso before the test is run.
  @Before
  public void registerIdlingResource() {
    mIdlingResource = activityTestRule.getActivity().getIdlingResource();
    // To prove that the test fails, omit this call:
    IdlingRegistry.getInstance().register(mIdlingResource);
  }

  @Test
  public void clickRecyclerViewItem_OpensDetailActivity() {
    onView(withId(R.id.rv_recipe_list))
        .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

    onView(withId(R.id.rv_ingredient_list))
        .check(matches(isDisplayed()));
  }

  // Remember to unregister resources when not needed to avoid malfunction.
  @After
  public void unregisterIdlingResource() {
    if (mIdlingResource != null) {
      IdlingRegistry.getInstance().unregister(mIdlingResource);
    }
  }
}
