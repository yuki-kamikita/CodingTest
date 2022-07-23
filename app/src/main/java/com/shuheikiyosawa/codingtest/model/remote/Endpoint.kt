package com.shuheikiyosawa.codingtest.model.remote

import com.shuheikiyosawa.codingtest.core.data.TopCategoryList
import retrofit2.Response
import retrofit2.http.GET

interface Endpoint{
    @GET("dev/mock/book/all")
    suspend fun allBook(): Response<TopCategoryList>
}