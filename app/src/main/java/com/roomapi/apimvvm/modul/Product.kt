package com.roomapi.apimvvm.modul

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Product(

    @SerializedName("productName") // API field name
    val productName: String,

    @SerializedName("imageFile")
    val imageFile: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("size")
    val size: Int,

    @SerializedName("price")
    val price: Double

)
