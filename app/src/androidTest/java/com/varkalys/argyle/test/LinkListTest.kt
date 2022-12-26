package com.varkalys.argyle.test

import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.varkalys.argyle.R
import com.varkalys.argyle.base.BaseTest
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

@HiltAndroidTest
class LinkListTest: BaseTest() {

    @Test
    fun displaysNotFilteredResults() {
        composeRule.apply {
            onNodeWithText("Amazon").assertExists()
            onNodeWithText("Grubhub").assertExists()
            onNodeWithText("DoorDash").assertExists()
            onNodeWithText("Gusto").assertExists()
            onNodeWithText("Home Depot").assertExists()
            onNodeWithText(R.string.no_results).assertDoesNotExist()
            onNodeWithText(R.string.enter_at_least_two_chars).assertDoesNotExist()
        }
    }

    @Test
    fun displaysMessageWhenSingleCharQueryEntered() {
        composeRule.apply {
            onNodeWithTag("query").performTextInput("a")
            onNodeWithText(R.string.enter_at_least_two_chars).assertExists()
            onNodeWithText(R.string.no_results).assertDoesNotExist()
            onNodeWithText("Amazon").assertDoesNotExist()
            onNodeWithText("Grubhub").assertDoesNotExist()
            onNodeWithText("DoorDash").assertDoesNotExist()
            onNodeWithText("Gusto").assertDoesNotExist()
            onNodeWithText("Home Depot").assertDoesNotExist()
        }
    }

    @Test
    fun displaysFilteredResults() {
        composeRule.apply {
            onNodeWithTag("query").performTextInput("am")
            wait1Second()
            onNodeWithText("Amazon").assertExists()
            onNodeWithText(R.string.enter_at_least_two_chars).assertDoesNotExist()
            onNodeWithText(R.string.no_results).assertDoesNotExist()
            onNodeWithText("Grubhub").assertDoesNotExist()
            onNodeWithText("DoorDash").assertDoesNotExist()
            onNodeWithText("Gusto").assertDoesNotExist()
            onNodeWithText("Home Depot").assertDoesNotExist()
        }
    }

    @Test
    fun clearsQueryAndDisplaysNotFilteredResults() {
        displaysFilteredResults()
        composeRule.onNodeWithTag("clear").performClick()
        wait1Second()
        displaysNotFilteredResults()
    }
}