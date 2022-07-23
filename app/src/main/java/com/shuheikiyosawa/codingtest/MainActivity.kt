package com.shuheikiyosawa.codingtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.shuheikiyosawa.codingtest.model.remote.ApiRequest
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {
            val response = ApiRequest().getTopCategoryList()
            Log.d("progress","$response")
        }
    }
}