package com.varkalys.argyle.base

import androidx.annotation.StringRes
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.varkalys.argyle.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
abstract class BaseTest {

    @get:Rule(order = 0)
    val hiltRule by lazy { HiltAndroidRule(this) }

    @get:Rule(order = 1)
    val composeRule by lazy { createAndroidComposeRule<MainActivity>() }

    @Before
    fun waitToLaunch() {
        wait1Second()
    }

    fun wait1Second() {
        composeRule.apply {
            waitForIdle()
            Thread.sleep(TimeUnit.SECONDS.toMillis(1))
        }
    }

    fun AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>.onNodeWithText(@StringRes id: Int): SemanticsNodeInteraction =
        onNodeWithText(activity.getString(id))
}