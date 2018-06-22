package com.ducdungdam.bakingapp;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

import android.content.Intent;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.ducdungdam.bakingapp.model.Recipe;
import com.ducdungdam.bakingapp.utilities.FakeDataUtils;
import com.ducdungdam.bakingapp.view.DetailActivity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * This test checks a user clicking on a RecyclerView item in MainActivity which opens up the
 * corresponding DetailActivity.
 */

@RunWith(AndroidJUnit4.class)
public class DetailActivityScreenTest {
  private final static String EXTRA_RECIPE = "extra_recipe";
  private final static Recipe EXTRA_RECIPE_VALUE = FakeDataUtils.getRecipe();

  @Rule
  public ActivityTestRule<DetailActivity> activityTestRule =
      new ActivityTestRule<>(DetailActivity.class);

  @Test
  public void clickViewPagerIngredientTab_ShowIngredientFragment() {
    onView(allOf(withText("Ingredient"),
        isDescendantOfA(withId(R.id.view_pager)))).perform(click());

    onView(withId(R.id.rv_ingredient_list))
        .check(matches(isDisplayed()));
  }

  @Test
  public void clickViewPagerStepsTab_ShowStepsFragment() {
    onView(allOf(withText("Steps"),
        isDescendantOfA(withId(R.id.view_pager)))).perform(click());

    onView(withId(R.id.rv_steps_list))
        .check(matches(isDisplayed()));
  }

  @Test
  public void clickRecyclerViewStepItem_OpensStepActivity() {
    Intent i = new Intent();
    i.putExtra(EXTRA_RECIPE, EXTRA_RECIPE_VALUE);
    activityTestRule.launchActivity(i);

    onView(allOf(withText("Steps"),
        isDescendantOfA(withId(R.id.view_pager)))).perform(click());

    onView(withId(R.id.rv_steps_list))
        .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

    onView(withId(R.id.fragment_steps))
        .check(matches(isDisplayed()));
  }
}
