package com.varkalys.argyle.ui.screen

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

@kotlin.OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseViewModelTest {

    private val dispatcher = StandardTestDispatcher()

    @Before
    fun dispatcherSetup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun dispatcherDown() {
        Dispatchers.resetMain()
    }
}