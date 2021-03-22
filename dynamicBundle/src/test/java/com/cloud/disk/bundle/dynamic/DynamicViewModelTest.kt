package com.cloud.disk.bundle.dynamic

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.cloud.disk.api.file.TransferFile
import com.cloud.disk.bundle.dynamic.data.DynamicRepository
import com.cloud.disk.bundle.dynamic.exception.NetWorkErrorException
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.util.*

@ExperimentalCoroutinesApi
@SmallTest
class DynamicViewModelTest {
    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `show show dynamic list when get success`() = runBlocking {
        //given
        val mockTransferFile = mock(TransferFile::class.java)
        val mockDynamicRepository = mock(DynamicRepository::class.java)
        `when`(mockDynamicRepository.getDynamicList()).thenReturn(getMockData())
        val dynamicViewModel = DynamicViewModel(mockTransferFile, mockDynamicRepository)
        //when
        dynamicViewModel.getDynamicList()
        //then
        val dynamicOne = LiveDataTestUtil.getValue(dynamicViewModel.dynamicListLiveData)[0]
        assertThat(dynamicOne.id).isEqualTo(1)
        assertThat(dynamicOne.content).isEqualTo("今天天气真不错！")
        assertThat(dynamicOne.date).isEqualTo(1615963675000L)
        val dynamicTwo = LiveDataTestUtil.getValue(dynamicViewModel.dynamicListLiveData)[1]
        assertThat(dynamicTwo.id).isEqualTo(2)
        assertThat(dynamicTwo.content).isEqualTo("这个连续剧值得追！")
        assertThat(dynamicTwo.date).isEqualTo(1615963688000L)

    }

    @Test
    fun `show show dynamic list when net work exception but have cache`() = runBlocking {
        //given
        val mockTransferFile = mock(TransferFile::class.java)
        val mockDynamicRepository = mock(DynamicRepository::class.java)
        `when`(mockDynamicRepository.getDynamicList()).thenThrow(NetWorkErrorException::class.java)
        `when`(mockDynamicRepository.getDynamicListFromCache()).thenReturn(getMockData())
        val dynamicViewModel = DynamicViewModel(mockTransferFile, mockDynamicRepository)

        //when
        dynamicViewModel.getDynamicList()
        //then
        val dynamicOne = LiveDataTestUtil.getValue(dynamicViewModel.dynamicListLiveData)[0]
        assertThat(dynamicOne.id).isEqualTo(1)
        assertThat(dynamicOne.content).isEqualTo("今天天气真不错！")
        assertThat(dynamicOne.date).isEqualTo(1615963675000L)
        val dynamicTwo = LiveDataTestUtil.getValue(dynamicViewModel.dynamicListLiveData)[1]
        assertThat(dynamicTwo.id).isEqualTo(2)
        assertThat(dynamicTwo.content).isEqualTo("这个连续剧值得追！")
        assertThat(dynamicTwo.date).isEqualTo(1615963688000L)

    }

    @Test
    fun `show show error tip when net work exception and not have cache`() = runBlocking {
        //given
        val mockTransferFile = mock(TransferFile::class.java)
        val mockDynamicRepository = mock(DynamicRepository::class.java)
        `when`(mockDynamicRepository.getDynamicList()).thenThrow(NetWorkErrorException::class.java)
        val dynamicViewModel = DynamicViewModel(mockTransferFile, mockDynamicRepository)

        //when
        dynamicViewModel.getDynamicList()
        //then
        val errorMessage = LiveDataTestUtil.getValue(dynamicViewModel.errorMessageLiveData)
        assertThat(errorMessage).isEqualTo("NetWorkErrorException")
        val dynamicList = LiveDataTestUtil.getValue(dynamicViewModel.dynamicListLiveData)
        assertThat(dynamicList).isNull()
    }

    @Test
    fun `show show empty tip when not has data`() = runBlocking {
        //given
        val mockTransferFile = mock(TransferFile::class.java)
        val mockDynamicRepository = mock(DynamicRepository::class.java)
        `when`(mockDynamicRepository.getDynamicList()).thenReturn(null)
        val dynamicViewModel = DynamicViewModel(mockTransferFile, mockDynamicRepository)

        //when
        dynamicViewModel.getDynamicList()
        //then
        val dynamicList = LiveDataTestUtil.getValue(dynamicViewModel.dynamicListLiveData)
        assertThat(dynamicList).isNull()
    }

    private fun getMockData(): ArrayList<Dynamic> {
        val dynamicList = ArrayList<Dynamic>()
        dynamicList.add(Dynamic(1, "今天天气真不错！", 1615963675000L))
        dynamicList.add(Dynamic(2, "这个连续剧值得追！", 1615963688000L))
        return dynamicList
    }
}
