@file:Suppress("NonAsciiCharacters")

package com.bita.lost.repo.data

import androidx.annotation.DrawableRes
import com.bita.lost.R

enum class ProductCode(val code: String, @DrawableRes val icon: Int) {
    가방("PRA000", R.drawable.ic_backpack),
    귀금속("PRO000", R.drawable.ic_jewerly),
    기타물품("PRZ000", R.drawable.ic_etc),
    도서용품("PRB000", R.drawable.ic_book),
    산업용품("PRD000", R.drawable.ic_toolbox),
    서류("PRC000", R.drawable.ic_paper_bag),
    쇼핑백("PRQ000", R.drawable.ic_shopping_bag),
    스포츠용품("PRE000", R.drawable.ic_sport),
    악기("PRR000", R.drawable.ic_instrument),
    유가증권("PRM000", R.drawable.ic_security_file),
    의류("PRK000", R.drawable.ic_cloth),
    자동차("PRF000", R.drawable.ic_car),
    전자기기("PRG000", R.drawable.ic_pad),
    증명서("PRN000", R.drawable.ic_certificate),
    지갑("PRH000", R.drawable.ic_wallet),
    카드("PRP000", R.drawable.ic_card),
    컴퓨터("PRI000", R.drawable.ic_computer),
    현금("PRL000", R.drawable.ic_money),
    휴대폰("PRJ000", R.drawable.ic_phone),
    모든습득물("", R.drawable.ic_packages);
}