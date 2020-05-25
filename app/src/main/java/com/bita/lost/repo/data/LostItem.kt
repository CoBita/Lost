package com.bita.lost.repo.data

import com.google.gson.annotations.SerializedName

data class LostList(
        @SerializedName("item") val items: List<LostItem>?
)

data class LostItem(
        val atcId: String,
        val lstPlace: String,
        val lstPrdtNm: String,
        val lstSbjt: String,
        val lstYmd: String,
        val prdtClNm: String,
        val rnum: Int
)