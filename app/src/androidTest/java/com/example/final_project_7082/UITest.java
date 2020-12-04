package com.example.final_project_7082;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
//import static android.support.v4
//import android.support.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class UITest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);



    @Test
    public void clickEvent() {
        onView(withId(R.id.button1)).perform(click());
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.event_day)).perform(typeText("3"), closeSoftKeyboard());
        onView(withId(R.id.event_month)).perform(typeText("12"), closeSoftKeyboard());
        onView(withId(R.id.event_year)).perform(typeText("2020"), closeSoftKeyboard());
        onView(withId(R.id.edit_event_title)).perform(typeText("Event 1"), closeSoftKeyboard());
        onView(withId(R.id.edit_event_content)).perform(typeText("This is an event."), closeSoftKeyboard());
        onView(withId(R.id.bt_save_event)).perform(click());
        assertEquals(1,1);
    }

    @Test
    public void clickJournal() {
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.bt_add)).perform(click());
        onView(withId(R.id.edit_title)).perform(typeText("Journal 1"), closeSoftKeyboard());
        onView(withId(R.id.edit_content)).perform(typeText("This is a journal entry."), closeSoftKeyboard());
        onView(withId(R.id.bt_update)).perform(click());
        //onView(withId(R.id.recycler_view)).perform(click());

        //TouchUtils.clickView(this, view.getChildAt(p));
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.text_title)).check(matches(withText("Journal 1")));
        onView(withId(R.id.text_content)).check(matches(withText("This is a journal entry.")));
    }


}
