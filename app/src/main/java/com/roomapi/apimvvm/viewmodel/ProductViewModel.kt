package com.roomapi.apimvvm.viewmodel

import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roomapi.apimvvm.modul.Product
import com.roomapi.apimvvm.network.NetworkState
import com.roomapi.apimvvm.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private  val repository: ProductRepository) :ViewModel(){



    private val _products= MutableLiveData<NetworkState<List<Product>>>()

    val products: LiveData<NetworkState<List<Product>>>

        get()=_products


    fun getProducts(){

        _products.value = NetworkState.Loading

        viewModelScope.launch{

            val result = repository.getMyProduct()
            _products.value = result



        }
    }


}