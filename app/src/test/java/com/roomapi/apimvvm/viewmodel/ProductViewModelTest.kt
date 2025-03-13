package com.roomapi.apimvvm.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.roomapi.apimvvm.modul.Product
import com.roomapi.apimvvm.network.NetworkState
import com.roomapi.apimvvm.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
@ExperimentalCoroutinesApi
class ProductViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()//LiveData  updating instantly in test


    private val testDispatcher= StandardTestDispatcher()

    @Mock
    lateinit var repository:ProductRepository


    lateinit var viewModel: ProductViewModel


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this) //initialize all mock object
        viewModel = ProductViewModel(repository)
        Dispatchers.setMain(testDispatcher)

    }


    @Test
    fun getProduct_List()= runTest{

        val items= listOf(Product("abc","abc","abc",5,5.0),
            Product("xyz","abc","abc",5,5.0))

        val response = NetworkState.Success(items)

        // Arrange
         Mockito.`when`(repository.getMyProduct()).thenReturn(response)


        // Act
        viewModel.getProducts()

        // Advance the coroutine to ensure it finishes
        testDispatcher.scheduler.advanceUntilIdle()


      val result = viewModel.products.value
//        assertEquals(2, result.size) // Check if the LiveData size is correct

        assertEquals(2,result.size)
    }
    @Test
    fun getProduct_Error()= runTest{



        // Arrange
        Mockito.`when`(repository.getMyProduct()).thenReturn(NetworkState.Error(Throwable("error")))


        // Act
        viewModel.getProducts()

        // Advance the coroutine to ensure it finishes
        testDispatcher.scheduler.advanceUntilIdle()

        val result = viewModel.products.value
        assertEquals(true,result)
        //assertEquals("Something went to wrong", result) // Check if the LiveData size is correct


    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}