package com.example.challengechapter7.viewmodel

import com.example.challengechapter7.model.ResponseDataProductItem
import com.example.challengechapter7.network.RestfulAPIProduct
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import retrofit2.Call

class ViewModelProductTest {

    lateinit var service : RestfulAPIProduct

    @Before
    fun setUp() {
        service = mockk()
    }

    @Test
    fun getProductTest() : Unit = runBlocking {
        val response = mockk<Call<List<ResponseDataProductItem>>>()
        every {
            runBlocking {
                service.getAllProduct()
            }
        } returns response

        val result = service.getAllProduct()

        verify {
            runBlocking { service.getAllProduct() }
        }
        assertEquals(result, response)
    }
}