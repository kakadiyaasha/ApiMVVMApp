package com.roomapi.apimvvm.repository

import android.util.Log
import com.roomapi.apimvvm.modul.Product
import com.roomapi.apimvvm.network.NetworkState
import com.roomapi.apimvvm.network.ProductServiceAPi
import javax.inject.Inject

class ProductRepository @Inject constructor(val aPi: ProductServiceAPi) {


    suspend fun getMyProduct(): NetworkState<List<Product>> {
        val response=aPi.getProducts()
         return if (response.isSuccessful){
            val responseBody=response.body()
             if (responseBody!=null){
                 Log.e("getMyProduct", "getMyProduct: "+responseBody)
                 return NetworkState.Success(responseBody)
             }else{
                 NetworkState.Error(Exception("Empty Response"))

             }
         } else {
             NetworkState.Error(Exception("Error: ${response.code()}"))

         }
    }


}