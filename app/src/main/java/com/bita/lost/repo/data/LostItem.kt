package com.bita.lost.repo.data

import com.google.gson.annotations.SerializedName
import java.text.DecimalFormat

data class LostListFrame(@SerializedName("SearchLostArticleService") val service: LostList)

data class LostList(
    @SerializedName("list_total_count") val listTotalCount: Int,
    @SerializedName("RESULT") val result: LostListResult,
    @SerializedName("row") val items: ArrayList<LostItem>
)

data class LostListResult(
    val CODE: String,
    val MESSAGE: String
)

//"ID": 6.1708348E7,
//"TAKE_PLACE": "경찰서",
//"GET_NAME": "삼성애니콜폴더핸드폰(검정+회색)",
//"GET_DATE": "2018-08-21",
//"GET_POSITION": "성원여객"
data class LostItem(
    @SerializedName("ID") private val doubleId: Double,
    @SerializedName("TAKE_PLACE") val place: String,
    @SerializedName("GET_NAME") val name: String,
    @SerializedName("GET_DATE") val date: String,
    @SerializedName("GET_POSITION") val company: String
) {
    val id get() = DecimalFormat("#").format(doubleId)

    override fun toString(): String =
        "LostItem(id=$id, place='$place', name='$name', date='$date', company='$company')"

}
