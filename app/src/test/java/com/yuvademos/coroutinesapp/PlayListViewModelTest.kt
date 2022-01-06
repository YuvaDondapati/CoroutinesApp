package com.yuvademos.coroutinesapp

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.yuvademos.coroutinesapp.model.PlayList
import com.yuvademos.coroutinesapp.repository.PlayListRepository
import com.yuvademos.coroutinesapp.viewmodel.PlayListViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import utils.BaseTest
import utils.captureValues
import utils.getValueForTest


class PlayListViewModelTest : BaseTest() {

    private val repo: PlayListRepository = mock()
    private val expectedList = mock<List<PlayList>>()
    private val expected = Result.success(expectedList)
    val error = Throwable("something goes wrong")
    val expectedError = Result.failure<List<PlayList>>(error)

    @Test
    fun getPlayListsFromRepo() = runTest {
        val viewModel = mockSuccessViewModel()
        viewModel.playLists.getValueForTest() //from livedataextension class
        verify(repo, times(1)).getPlayLists()
    }

    @Test
    fun emitsPlayListsFromRepo() = runTest {
        val viewModel = mockSuccessViewModel()
        assertEquals(expected, viewModel.playLists.getValueForTest())
    }

    @Test
    fun emitsErrorWhenReceiveErrorFromRepo() = runTest {
        val viewModel = mockFailureCase()
        assertEquals(expectedError, viewModel.playLists.getValueForTest())
    }

    @Test
    fun showSpinnerWhileLoading() {
        val viewModel = mockSuccessViewModel()
        viewModel.loader.captureValues {
            viewModel.playLists.getValueForTest()
            assertEquals(true, values[0]) //values from livedataextensiontest
        }
    }

    @Test
    fun hideSpinnerAfterPlayListsLoad() {
        val viewModel = mockSuccessViewModel()
        viewModel.loader.captureValues {
            viewModel.playLists.getValueForTest()
            assertEquals(false, values.last()) //values from livedataextensiontest
        }
    }

    @Test
    fun hideSpinnerAfterError() {
        val viewModel = mockFailureCase()
        viewModel.loader.captureValues {
            viewModel.playLists.getValueForTest()
            assertEquals(false, values.last()) //values from livedataextensiontest
        }
    }

    private fun mockFailureCase(): PlayListViewModel {
        runBlocking {
            whenever(repo.getPlayLists()).thenReturn(
                flow {
                    emit(expectedError)
                }
            )
        }
        return PlayListViewModel(repo)
    }

    private fun mockSuccessViewModel(): PlayListViewModel {
        runBlocking {
            whenever(repo.getPlayLists()).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }
        return PlayListViewModel(repo)
    }
}