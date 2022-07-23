package com.shuheikiyosawa.codingtest.core.data

import com.google.gson.annotations.SerializedName

data class Book(
    @SerializedName("id_book")
    val id: String,
    @SerializedName("name_book")
    val name: String,
    @SerializedName("publisher")
    val publisher: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("img_url")
    val img_url: String
    )


