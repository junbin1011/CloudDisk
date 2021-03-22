package com.cloud.dynamicdebug

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@LargeTest
@HiltAndroidTest
@Config(application = HiltTestApplication::class, shadows = [ShadowDynamicViewModel::class])
class DynamicFragmentTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun `show show dynamic list when get success`() {
        //given
        ShadowDynamicViewModel.state = ShadowDynamicViewModel.State.SUCCESS
        //when
        val scenario: ActivityScenario<DebugActivity> = ActivityScenario.launch(DebugActivity::class.java)
        scenario.onActivity {
            //then
            onView(withText("今天天气真不错！")).check(matches(isDisplayed()))
            onView(withText("2021-03-17 14:47:55")).check(matches(isDisplayed()))
            onView(withText("这个连续剧值得追！")).check(matches(isDisplayed()))
            onView(withText("2021-03-17 14:48:08")).check(matches(isDisplayed()))
        }
    }

    @Test
    fun `show show dynamic list when net work exception but have cache`() {
        //given
        ShadowDynamicViewModel.state = ShadowDynamicViewModel.State.CACHE
        //when
        val scenario: ActivityScenario<DebugActivity> = ActivityScenario.launch(DebugActivity::class.java)
        scenario.onActivity {
            //then
            onView(withText("今天天气真不错！")).check(matches(isDisplayed()))
            onView(withText("2021-03-17 14:47:55")).check(matches(isDisplayed()))
            onView(withText("这个连续剧值得追！")).check(matches(isDisplayed()))
            onView(withText("2021-03-17 14:48:08")).check(matches(isDisplayed()))
        }
    }

    @Test
    fun `show show error tip when net work exception and not have cache`() {
        //given
        ShadowDynamicViewModel.state = ShadowDynamicViewModel.State.ERROR
        //when
        val scenario: ActivityScenario<DebugActivity> = ActivityScenario.launch(DebugActivity::class.java)
        scenario.onActivity {
            //then
            onView(withText("NetworkErrorException")).check(matches(isDisplayed()))
        }
    }

    @Test
    fun `show show empty tip when not has data`() {
        //given
        ShadowDynamicViewModel.state = ShadowDynamicViewModel.State.EMPTY
        //when
        val scenario: ActivityScenario<DebugActivity> = ActivityScenario.launch(DebugActivity::class.java)
        scenario.onActivity {
            //then
            onView(withText("empty data")).check(matches(isDisplayed()))
        }
    }
}