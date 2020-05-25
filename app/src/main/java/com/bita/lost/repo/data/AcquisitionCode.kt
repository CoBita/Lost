@file:Suppress("ClassName", "unused", "NonAsciiCharacters", "EnumEntryName")

package com.bita.lost.repo.data

import com.bita.lost.R

enum class AcquisitionCode(val icon: Int) {
    지갑(R.drawable.ic_wallet),
    가방(R.drawable.ic_bag),
    핸드폰(R.drawable.ic_phone),
    의류(R.drawable.ic_cloth),
    책(R.drawable.ic_book),

    직접입력(R.drawable.ic_etc)

    // TODO : 카드추가, 직접입력 BottomSheet
}