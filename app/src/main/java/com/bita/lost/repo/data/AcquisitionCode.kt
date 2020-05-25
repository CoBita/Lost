@file:Suppress("ClassName", "unused", "NonAsciiCharacters", "EnumEntryName")

package com.bita.lost.repo.data

import com.bita.lost.R

enum class AcquisitionCode(val icon: Int, var description: String) {
    지갑(R.drawable.ic_wallet, "지갑"),
    가방(R.drawable.ic_bag, "가방"),
    핸드폰(R.drawable.ic_phone, "핸드폰"),
    의류(R.drawable.ic_cloth, "의류("),
    책(R.drawable.ic_book, "책"),
    직접입력(R.drawable.ic_etc, "직접입력")
}