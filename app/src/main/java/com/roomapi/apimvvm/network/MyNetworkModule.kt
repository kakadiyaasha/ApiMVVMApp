package com.roomapi.apimvvm.network

import com.roomapi.apimvvm.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MyNetworkModule {


    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder().
                baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
               build()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit):ProductServiceAPi {
        return retrofit.create(ProductServiceAPi::class.java)
    }

    companion object {
        private const val BASE_URL = "https://2873199.youcanlearnit.net/" // Replace with your API URL
    }

}