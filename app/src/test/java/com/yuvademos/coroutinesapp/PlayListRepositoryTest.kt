package com.yuvademos.coroutinesapp

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.yuvademos.coroutinesapp.model.PlayList
import com.yuvademos.coroutinesapp.network.PlayListApi
import com.yuvademos.coroutinesapp.repository.PlayListRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import utils.BaseTest

class PlayListRepositoryTest : BaseTest() {

    val api: PlayListApi = mock()
    val playLists = mock<List<PlayList>>()
    val error = Throwable("something goes wrong")

    @Test
    fun fetchPlayLists() = runTest {
        val repository = PlayListRepository(api)
        repository.getPlayLists()

        verify(api, times(1)).fetchPlayLists()
    }

    @Test
    fun fetchSuccessPlayLists() = runTest {
        val repository = mockSuccessCase()
        assertEquals(playLists, repository.getPlayLists().first().getOrNull())
    }

    @Test
    fun fetchErrorWhenReceiveError() = runTest {
        val repository = mockFailureCase()
        assertEquals(error, repository.getPlayLists().first().exceptionOrNull())
    }

    private fun mockFailureCase(): PlayListRepository {
        val expectedError = Result.failure<List<PlayList>>(error)
        runBlocking {
            whenever(api.fetchPlayLists()).thenReturn(
                flow {
                    emit(expectedError)
                }
            )
        }
        return PlayListRepository(api)
    }

    private fun mockSuccessCase(): PlayListRepository {
        runBlocking {
            whenever(api.fetchPlayLists()).thenReturn(
                flow {
                    emit(Result.success(playLists))
                }
            )
        }
        return PlayListRepository(api)
    }

}