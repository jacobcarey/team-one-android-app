package com.project.one.team.musictheoryapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.filters.LargeTest;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Espresso Test Class for testing UI elements on {@link com.project.one.team.musictheoryapp.SettingsActivity SettingsActivity}.
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class SettingsActivityTest {

    @Rule
    public ActivityTestRule<SettingsActivity> mActivityTestRule = new ActivityTestRule<>(SettingsActivity.class);

    @Test
    public void nightModeButtonShouldToggleNightMode() {
        // Store the night mode state before the toggle.
        boolean currentValue = ((Theoryously) mActivityTestRule.getActivity().getApplication()).getNightMode();

        onView(allOf(withId(R.id.nightModeToggle), isDisplayed()))
                .perform(click());

        // Check that new night mode value is different from the original.
        Assert.assertNotEquals("Night Mode should be didn't toggle!", currentValue,
                ((Theoryously) mActivityTestRule.getActivity().getApplication()).getNightMode());
    }

    @Test
    public void canSaveUsername() {
        // Could use parameterized tests here.
        String testUsername = "TestUser";
        onView(allOf(withId(R.id.userName), isDisplayed()))
                .perform(typeText(testUsername), closeSoftKeyboard());
        onView(allOf(withId(R.id.userNameButton), isDisplayed()))
                .perform(click(), pressBack());

        onView(allOf(withId(R.id.userName), isDisplayed()))
                .check(matches(withText("Logged in as: " + testUsername)));
    }

}