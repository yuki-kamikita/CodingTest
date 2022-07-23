package com.shuheikiyosawa.codingtest.model.remote

import com.shuheikiyosawa.codingtest.core.data.TopCategoryList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRequest {
    private val domain = "https://2zw3cqudp7.execute-api.ap-northeast-1.amazonaws.com/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(domain)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val service = retrofit.create(Endpoint::class.java)

    /**
     * @return 取得失敗したらnullを返す
     */
    suspend fun getTopCategoryList(): TopCategoryList? {
        val response = service.allBook()
        return if (response.isSuccessful) response.body() else null
    }
}