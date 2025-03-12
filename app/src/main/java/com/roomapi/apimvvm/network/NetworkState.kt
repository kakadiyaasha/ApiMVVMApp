package com.roomapi.apimvvm.network

import retrofit2.Response



sealed class NetworkState<out T> {
    object Loading : NetworkState<Nothing>()
    data class Success<out T>(val data: T) : NetworkState<T>()
    data class Error(val exception: Throwable) : NetworkState<Nothing>()
}