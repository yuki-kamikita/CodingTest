package com.shuheikiyosawa.codingtest.core.data

import com.google.gson.annotations.SerializedName

data class TopCategory(
    @SerializedName("id_top_category")
    val id: String,
    @SerializedName("name_category")
    val name: String,
    @SerializedName("sub_category_list")
    val subCategoryList: List<SubCategory>
    )

data class TopCategoryList(
    @SerializedName("top_category_list")
    val topCategoryList: List<TopCategory>)
