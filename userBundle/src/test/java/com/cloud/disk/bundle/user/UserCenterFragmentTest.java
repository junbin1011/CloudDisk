package com.cloud.disk.bundle.user;


import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UserCenterFragmentTest {
    @Test
    public void show_show_user_center_ui_when_click_tab_dynamic() {
        //given
        FragmentScenario<UserCenterFragment> scenario = FragmentScenario.launchInContainer(UserCenterFragment.class);
        scenario.onFragment(activity -> {
            //then
            onView(withText("Hello user center fragment")).check(matches(isDisplayed()));
        });
    }
}