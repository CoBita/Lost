package com.bita.lost.repo.data

import com.google.gson.annotations.SerializedName

data class BaseData<T>(val response: Response<T>)
data class Response<T>(
        val header: Header,
        val body: Body<T>
)

data class Header(
        @SerializedName("resultCode") val code: String,
        @SerializedName("resultMsg") val msg: String
)

data class Body<T>(
        val items: T,
        val numOfRows: Int,
        val pageNo: Int,
        val totalCount: Int
)