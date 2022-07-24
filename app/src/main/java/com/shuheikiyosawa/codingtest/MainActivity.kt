package com.shuheikiyosawa.codingtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.shuheikiyosawa.codingtest.databinding.ActivityMainBinding
import com.shuheikiyosawa.codingtest.model.remote.ApiRequest
import com.shuheikiyosawa.codingtest.viewModel.SearchViewModel
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: SearchViewModel by viewModels()
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.init()
        }
    }
}