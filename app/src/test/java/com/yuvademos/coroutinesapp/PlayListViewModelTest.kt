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
import utils.getValueForTest


class PlayListViewModelTest : BaseTest() {

    private val repo: PlayListRepository = mock()
    private val expectedList = mock<List<PlayList>>()
    private val expected = Result.success(expectedList)
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
        val error = Throwable("something goes wrong")
        val expectedError = Result.failure<List<PlayList>>(error)
        whenever(repo.getPlayLists()).thenReturn(
        flow {
            emit(expectedError)
        }
       )
        val viewModel = PlayListViewModel(repo)
        assertEquals(expectedError, viewModel.playLists.getValueForTest())
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