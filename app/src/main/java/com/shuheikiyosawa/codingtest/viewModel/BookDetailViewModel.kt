package com.shuheikiyosawa.codingtest.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shuheikiyosawa.codingtest.core.data.Book
import com.shuheikiyosawa.codingtest.core.data.Logger
import com.shuheikiyosawa.codingtest.core.data.TopCategoryList
import com.shuheikiyosawa.codingtest.model.remote.ApiRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookDetailViewModel: ViewModel() {
    val myBooksAdded = MutableLiveData<Boolean>().apply {
        value = false
    }


    init {
        CoroutineScope(Dispatchers.IO).launch {

            val response = ApiRequest().getTopCategoryList()
//
//            CoroutineScope(Dispatchers.Main).launch {
//                loading.value = false
//                this@BookDetailViewModel.response.value = response
//            }

            Logger.debug("$response")
        }
    }

    fun addMyBooks() {
        myBooksAdded.value = true
    }

    fun removeMyBooks() {
        myBooksAdded.value = false
    }
}