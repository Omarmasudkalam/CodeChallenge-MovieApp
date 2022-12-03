package com.omk.presentation.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.omk.presentation.util.rules.CoroutineTestRule
import org.junit.Rule
import org.mockito.Mockito


open class BaseViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = CoroutineTestRule()


    fun <T> any(): T = Mockito.any()

    inline fun <reified T> mock(): T = Mockito.mock(T::class.java)
}