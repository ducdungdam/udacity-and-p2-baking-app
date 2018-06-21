package com.ducdungdam.bakingapp;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;

import android.app.Activity;
import android.app.Instrumentation.ActivityResult;
import android.content.Intent;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.ducdungdam.bakingapp.model.Recipe;
import com.ducdungdam.bakingapp.utilities.FakeDataUtils;
import com.ducdungdam.bakingapp.view.DetailActivity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * This test checks if intent is send correctly when a user clicking on a RecyclerView item in MainActivity which opens up the
 * corresponding DetailActivity.
 */

@RunWith(AndroidJUnit4.class)
public class DetailActivityIntentTest {
  private final static String EXTRA_RECIPE = "extra_recipe";
  private final static Recipe EXTRA_RECIPE_VALUE = FakeDataUtils.getRecipe();
  private final static String EXTRA_CURRENT_STEP_POSITION = "extra_current_step_position";
  private final static String EXTRA_STEPS = "extra_steps";

  @Rule
  public IntentsTestRule<DetailActivity> activityIntentTestRule = new IntentsTestRule<>(
      DetailActivity.class);

  @Before
  public void stubAllExternalIntents() {
    intending(not(isInternal())).respondWith(new ActivityResult(Activity.RESULT_OK, null));
  }

  @Test
  public void clickRecyclerViewItem_OpensStepActivity() {
    Intents.release();
    Intent i = new Intent();
    i.putExtra(EXTRA_RECIPE, EXTRA_RECIPE_VALUE);
    activityIntentTestRule.launchActivity(i);

    onView(allOf(withText("Steps"),
        isDescendantOfA(withId(R.id.view_pager)))).perform(click());

    onView(withId(R.id.rv_steps_list))
        .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

    intended(allOf(
        hasExtraWithKey(EXTRA_STEPS),
        hasExtraWithKey(EXTRA_CURRENT_STEP_POSITION)));
  }
}
