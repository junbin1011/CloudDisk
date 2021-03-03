package com.cloud.disk;

import androidx.fragment.app.Fragment;
import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.alibaba.android.arouter.launcher.ARouter;
import com.cloud.disk.app.MainActivity;
import com.cloud.disk.bundle.dynamic.DynamicFragment;
import com.cloud.disk.bundle.file.FileFragment;
import com.cloud.disk.bundle.user.UserCenterFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.util.List;

import dagger.hilt.android.testing.HiltAndroidRule;
import dagger.hilt.android.testing.HiltAndroidTest;
import dagger.hilt.android.testing.HiltTestApplication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.google.common.truth.Truth.assertThat;


@RunWith(AndroidJUnit4.class)
@LargeTest
@HiltAndroidTest
@Config(application = HiltTestApplication.class, shadows = {ShadowPostCard.class})
public class SmokeTesting {
    @Rule
    public HiltAndroidRule hiltRule = new HiltAndroidRule(this);

    @Before
    public void before() {
        ARouter.init(ApplicationProvider.getApplicationContext());
    }

    @Test
    public void should_show_fragment_list_when_activity_launch() {
        //given
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);
        scenario.onActivity(activity -> {
            //when
            onView(withText(R.string.tab_user)).perform(click());
            //then
            List<Fragment> fragments = activity.getSupportFragmentManager().getFragments();
            assertThat(fragments.size() == 3);
            assertThat(fragments.get(0) instanceof FileFragment);
            assertThat(fragments.get(1) instanceof DynamicFragment);
            assertThat(fragments.get(2) instanceof UserCenterFragment);
        });
    }

    @Test
    public void show_show_file_ui_when_click_tab_file() {
        //given
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);
        scenario.onActivity(activity -> {
            //when
            onView(withText(R.string.tab_file)).perform(click());
            //then
            onView(withText("Hello file fragment")).check(matches(isDisplayed()));
        });
    }

    @Test
    public void show_show_dynamic_ui_when_click_tab_dynamic() {
        //given
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);
        scenario.onActivity(activity -> {
            //when
            onView(withText(R.string.tab_dynamic)).perform(click());
            //then
            onView(withText("Hello dynamic fragment")).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        });
    }

    @Test
    public void show_show_user_center_ui_when_click_tab_dynamic() {
        //given
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);
        scenario.onActivity(activity -> {
            //when
            onView(withText(R.string.tab_user)).perform(click());
            //then
            onView(withText("Hello user center fragment")).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        });
    }

    @Test
    public void show_show_login_ui_when_click_login_button() {
        //given
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);
        scenario.onActivity(activity -> {
            Intents.init();
            //when
            onView(withId(R.id.fab)).perform(click());
            //then
            intended(IntentMatchers.hasComponent("com.cloud.disk.platform.login.LoginActivity"));
            Intents.release();
        });
    }
}