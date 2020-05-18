package com.bita.lost.repo.data

import com.google.gson.annotations.SerializedName
import java.text.DecimalFormat

data class LostListFrame(
        @SerializedName("SearchLostArticleService") val service: LostList
)

data class LostList(
        @SerializedName("list_total_count") val listTotalCount: Int,
        @SerializedName("RESULT") val result: LostListResult,
        @SerializedName("row") val items: ArrayList<LostItem>
)

data class LostListResult(
        val CODE: String,
        val MESSAGE: String
)

data class LostItem(
        @SerializedName("ID") private val doubleId: Double,
        @SerializedName("TAKE_PLACE") val takePlace: String,
        @SerializedName("GET_NAME") val getName: String,
        @SerializedName("GET_DATE") val getDate: String,
        @SerializedName("GET_POSITION") val getPosition: String
) {
    val id get() = DecimalFormat("#").format(doubleId)
}
