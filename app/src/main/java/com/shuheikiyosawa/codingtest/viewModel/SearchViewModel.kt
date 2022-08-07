package com.shuheikiyosawa.codingtest.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shuheikiyosawa.codingtest.core.data.Logger
import com.shuheikiyosawa.codingtest.core.data.TopCategoryList
import com.shuheikiyosawa.codingtest.model.remote.ApiRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {
    val response = MutableLiveData<TopCategoryList>().apply {
        value = null
    }
    val loading = MutableLiveData<Boolean>().apply {
        value = true
    }

    init {
        CoroutineScope(Dispatchers.IO).launch {

            val response = ApiRequest().getTopCategoryList()

            CoroutineScope(Dispatchers.Main).launch {
                loading.value = false
                this@SearchViewModel.response.value = response
            }

            Logger.debug("$response")
        }
    }
}