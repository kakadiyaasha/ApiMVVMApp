package com.roomapi.apimvvm.network

import com.roomapi.apimvvm.modul.Product
import retrofit2.Response
import retrofit2.http.GET

interface ProductServiceAPi {

    @GET("olive_oils_data.json")
    suspend fun getProducts(): Response<List<Product>>


}