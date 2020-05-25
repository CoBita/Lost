package com.bita.lost.repo.data

import com.google.gson.annotations.SerializedName

data class LostListFrame(
        val response: Result
)

data class Result(
        val header: Header,
        val body: LostListBody
)

data class Header(
        val resultCode: String,
        val resultMsg: String
)

data class LostListBody(
        @SerializedName("items") val list: LostList,
        val numOfRows: Int,
        val pageNo: Int,
        val totalCount: Int
)

data class LostList(
        @SerializedName("item") val items: List<LostItem>
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