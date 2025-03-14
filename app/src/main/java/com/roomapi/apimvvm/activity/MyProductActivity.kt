package com.roomapi.apimvvm.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.roomapi.apimvvm.LogService
import com.roomapi.apimvvm.activity.ui.theme.RoomApiMVVMAppTheme
import com.roomapi.apimvvm.modul.Product
import com.roomapi.apimvvm.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyProductActivity : ComponentActivity() {
    @Inject
    lateinit var logService: LogService

    private lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]

        enableEdgeToEdge()

        setContent {
            RoomApiMVVMAppTheme{

                ProductScreen(productViewModel)
                LaunchedEffect(Unit) {
                    productViewModel.getProducts()
                }
            }

        }

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(viewModel: ProductViewModel) {

    val products by viewModel.products.collectAsState()
    val isLoading by viewModel.loading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()


    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "My ProductList") },Modifier.background(Color.Blue))
        }
    ) { paddingValues ->

        // The rest of your content goes here
        Column(modifier = Modifier.padding(paddingValues)) {
            when{
                isLoading->{
                    LoadingView()
                }
                products.isNotEmpty()->{
                    ProductList(products)
                }
                errorMessage != null->{
                    ErrorView(errorMessage.toString())
                }
            }
        }
    }
}



@Composable
fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}


@Composable
fun ProductList(products: List<Product>) {
    Column(modifier = Modifier.padding(16.dp)) {
        products.forEach { product ->
            Text(
                text = "Product: ${product.productName}, Price: ${product.price}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun ErrorView(errorMessage: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Error: $errorMessage")
    }
}
@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    RoomApiMVVMAppTheme {
        Greeting2("Android")
    }
}