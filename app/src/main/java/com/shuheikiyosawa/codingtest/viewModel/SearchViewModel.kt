package com.shuheikiyosawa.codingtest.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shuheikiyosawa.codingtest.core.data.Logger
import com.shuheikiyosawa.codingtest.model.remote.ApiRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {
    val text = MutableLiveData<String>().apply {
        value = "now loading"
    }
    val loading = MutableLiveData<Boolean>().apply {
        value = true
    }

    suspend fun init() {
        val response = ApiRequest().getTopCategoryList()

        CoroutineScope(Dispatchers.Main).launch {
            loading.value = false
            text.value = response.toString()
        }

        Logger.debug("$response")
    }
}