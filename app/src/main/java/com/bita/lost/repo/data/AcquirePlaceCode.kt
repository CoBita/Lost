@file:Suppress("ClassName", "unused", "NonAsciiCharacters", "EnumEntryName")

package com.bita.lost.repo.data

import com.bita.lost.R

enum class AcquirePlaceCode(val icon: Int, var description: String) {
    버스(R.drawable.ic_bus, "버스"),
    택시(R.drawable.ic_taxi, "택시"),
    역(R.drawable.ic_subway_1, "역"),
    직접입력(R.drawable.ic_etc, "직접입력")
}