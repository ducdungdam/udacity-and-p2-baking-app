package com.ducdungdam.bakingapp;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;

import android.app.Activity;
import android.app.Instrumentation.ActivityResult;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.ducdungdam.bakingapp.view.MainActivity;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * This test checks if intent is send correctly when a user clicking on a RecyclerView item in MainActivity which opens up the
 * corresponding DetailActivity.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityIntentTest {
  private final static String EXTRA_RECIPE = "extra_recipe";

  @Rule
  public IntentsTestRule<MainActivity> activityIntentTestRule = new IntentsTestRule<>(
      MainActivity.class);

  private IdlingResource mIdlingResource;

  @Before
  public void registerIdlingResource() {
    mIdlingResource = activityIntentTestRule.getActivity().getIdlingResource();
    // To prove that the test fails, omit this call:
    IdlingRegistry.getInstance().register(mIdlingResource);
  }

  @Before
  public void stubAllExternalIntents() {
    intending(not(isInternal())).respondWith(new ActivityResult(Activity.RESULT_OK, null));
  }

  @Test
  public void clickRecyclerViewItem_OpensDetailActivity() {
    onView(withId(R.id.rv_recipe_list))
        .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

    intended(allOf(
        hasExtraWithKey(EXTRA_RECIPE)));
  }

  @After
  public void unregisterIdlingResource() {
    if (mIdlingResource != null) {
      IdlingRegistry.getInstance().unregister(mIdlingResource);
    }
  }
}
