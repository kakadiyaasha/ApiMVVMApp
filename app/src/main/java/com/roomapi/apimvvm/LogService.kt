package com.roomapi.apimvvm

import android.os.Message
import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogService @Inject constructor() {

    fun myLog(message: String){
        Log.e("MyProduct", "mylog: $message", )
    }

}