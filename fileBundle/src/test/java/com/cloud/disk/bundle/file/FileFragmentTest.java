package com.cloud.disk.bundle.file;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import dagger.hilt.android.testing.HiltAndroidRule;
import dagger.hilt.android.testing.HiltAndroidTest;
import dagger.hilt.android.testing.HiltTestApplication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
@HiltAndroidTest
@Config(application = HiltTestApplication.class, shadows = {ShadowFileFragment.class})
public class FileFragmentTest {

    @Rule
    public HiltAndroidRule hiltRule = new HiltAndroidRule(this);

    @Test
    public void show_show_file_list_when_get_success() {
        //given
        ShadowFileFragment.state = ShadowFileFragment.State.SUCCESS;
        //when
        ActivityScenario<DebugActivity> scenario = ActivityScenario.launch(DebugActivity.class);
        scenario.onActivity(activity -> {
            //then
            onView(withText("遗留代码重构.pdf")).check(matches(isDisplayed()));
            onView(withText("100.00K")).check(matches(isDisplayed()));
            onView(withText("系统组件化.pdf")).check(matches(isDisplayed()));
            onView(withText("9.67K")).check(matches(isDisplayed()));
        });
    }

    @Test
    public void show_show_error_tip_when_net_work_exception() {
        //given
        ShadowFileFragment.state = ShadowFileFragment.State.ERROR;
        //when
        ActivityScenario<DebugActivity> scenario = ActivityScenario.launch(DebugActivity.class);
        scenario.onActivity(activity -> {
            //then
            onView(withText("NetworkErrorException")).check(matches(isDisplayed()));
        });
    }

    @Test
    public void show_show_empty_tip_when_not_has_data() {
        //given
        ShadowFileFragment.state = ShadowFileFragment.State.EMPTY;
        //when
        ActivityScenario<DebugActivity> scenario = ActivityScenario.launch(DebugActivity.class);
        scenario.onActivity(activity -> {
            //then
            onView(withText("empty data")).check(matches(isDisplayed()));
        });
    }
}