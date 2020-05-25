@file:Suppress("ClassName", "unused", "NonAsciiCharacters", "EnumEntryName")

package com.bita.lost.repo.data

import com.bita.lost.R

enum class AcquirePlaceCode(val code: String, val icon: Int, val description: String) {
    버스("b1", R.drawable.ic_bus, "버스"),
    개인택시("t2", R.drawable.ic_taxi, "개인택시"),
    지하철1호선에서4호선("s1", R.drawable.ic_subway_1, "역")
    //TODO : 직접입력 추가
}