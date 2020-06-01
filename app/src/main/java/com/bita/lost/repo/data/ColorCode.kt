@file:Suppress("NonAsciiCharacters", "EnumEntryName")

package com.bita.lost.repo.data

import android.graphics.Color

enum class ColorCode(val hex: Int, val code: String, val description: String) {
    흰색(Color.parseColor("#ffffff"), "CL1001", "화이트(하양)"),
    검정색(Color.parseColor("#000000"), "CL1002", "블랙(검정)"),
    빨강색(Color.parseColor("#ff0000"), "CL1003", "레드(빨강)"),
    주황색(Color.parseColor("#ff8000"), "CL1004", "오렌지(주황)"),
    노랑색(Color.parseColor("#ffff00"), "CL1005", "옐로우(노랑)"),
    초록색(Color.parseColor("#00ff00"), "CL1006", "그린(초록)"),
    파랑색(Color.parseColor("#0000ff"), "CL1007", "블루(파랑)"),
    갈색(Color.parseColor("#a52a2a"), "CL1008", "브라운(갈)"),
    보라색(Color.parseColor("#ee82ee"), "CL1009", "바이올렛(보라)"),
    분홍색(Color.parseColor("#ffc0cb"), "CL1011", "핑크(분홍)"),
    기타(Color.parseColor("#ffffff"), "CL1010", "기타");
}