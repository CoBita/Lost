@file:Suppress("ClassName", "unused", "NonAsciiCharacters", "EnumEntryName")

package com.bita.lost.repo

import com.bita.lost.R

enum class AcquirePlaceCode(val code: String, val icon: Int, val description: String) {
    버스("b1", R.drawable.ic_bus, "버스"),
    마을버스("b2", R.drawable.ic_town_bus, "마을버스"),
    법인택시("t1", R.drawable.ic_corporation_taxi, "법인택시"),
    개인택시("t2", R.drawable.ic_taxi, "개인택시"),
    지하철1호선에서4호선("s1", R.drawable.ic_subway_1, "지하철(1~4)"),
    지하철5호선에서8호선("s2", R.drawable.ic_subway_2, "지하철(5~8)"),
    코레일("s3", R.drawable.ic_subway_3, "코레일"),
    지하철9호선("s4", R.drawable.ic_subway_4, "지하철(9)")
}