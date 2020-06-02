package com.bita.lost.repo.data

import com.google.gson.annotations.SerializedName

data class LostList(
        @SerializedName("item") val items: List<LostItem>?
)

data class LostItem(
    val atcId: String,
    val depPlace: String,
    val fdFilePathImg: String,
    val fdPrdtNm: String,
    val fdSbjt: String,
    val fdSn: Int,
    val fdYmd: String,
    val prdtClNm: String,
    val rnum: Int
)