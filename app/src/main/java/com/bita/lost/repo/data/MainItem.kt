package com.bita.lost.repo.data

import com.google.gson.annotations.SerializedName

data class MainItem(
        val row: ArrayList<Row>
)

data class Row(
        @SerializedName("ID") val id: Double,
        @SerializedName("TAKE_PLACE") val takePlace: String,
        @SerializedName("GET_NAME") val getName: String,
        @SerializedName("GET_DATE") val getDate: String,
        @SerializedName("GET_POSITION") val getPosition: String
)

