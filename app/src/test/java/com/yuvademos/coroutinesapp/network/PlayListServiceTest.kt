package com.yuvademos.coroutinesapp.network

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.yuvademos.coroutinesapp.model.PlayList
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import utils.BaseTest
import java.lang.RuntimeException

class PlayListServiceTest : BaseTest() {

    private val api: PlayListApi = mock()
    val playLists = mock<List<PlayList>>()

    @Test
    fun fetchPlayListsFromApi() = runTest {
        val service = PlayListService(api)
        service.fetchPlayLists().first()
        verify(api, times(1)).fetchAllPlayLists()
    }

    @Test
    fun convertValuesToFlowResultAndEmitThem() = runTest{
        whenever(api.fetchAllPlayLists()).thenReturn(playLists)
        val service = PlayListService(api)
        assertEquals(Result.success(playLists), service.fetchPlayLists().first())
    }

    @Test
    fun assertErrorResultsWhenNetWorkFails() = runTest {
        val error = RuntimeException("not working")
        whenever(api.fetchAllPlayLists()).thenThrow(error)
        val service = PlayListService(api)
        assertEquals("something went wrong", service.fetchPlayLists().first().exceptionOrNull()?.message)
    }

}
