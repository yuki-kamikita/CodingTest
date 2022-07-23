package com.shuheikiyosawa.codingtest.core.data

import com.google.gson.annotations.SerializedName

data class SubCategory(
    @SerializedName("id_category")
    val id: String,
    @SerializedName("name_category")
    val name: String,
    @SerializedName("book_list")
    val bookList: List<Book>
    )


