package com.roomapi.apimvvm.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roomapi.apimvvm.modul.Product
import com.roomapi.apimvvm.network.NetworkState
import com.roomapi.apimvvm.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private  val repository: ProductRepository) :ViewModel(){

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _products = MutableStateFlow<List<Product>>(emptyList())

   val products: StateFlow<List<Product>>
       get()=_products



    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true  // Show loading state
            _errorMessage.value = null // Reset error state
            val result = repository.getMyProduct()

            when (result) {
                is NetworkState.Success -> {
                    _products.value = result.data
                }
                is NetworkState.Error -> {
                    _errorMessage.value = result.exception.message ?: "Unknown error"
                }

                else -> {

                }
            }

            _loading.value = false  // Hide loading state

        }
    }




}