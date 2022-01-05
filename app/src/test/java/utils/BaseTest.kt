package utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

open class BaseTest {
    @get:Rule
    var coroutineScopeRule = MainCoroutineScopeRule()

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()
}