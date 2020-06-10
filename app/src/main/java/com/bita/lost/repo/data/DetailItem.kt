package com.bita.lost.repo.data

class DetailItemFrame(val response: DetailResponse)

data class DetailResponse(
        val header: DetailHeader,
        val body: DetailBody
)

data class DetailHeader(
        val resultCode: String,
        val resultMsg: String
)

data class DetailBody(val item: DetailItem)

data class DetailItem(
        val atcId: String,                       // 관리ID
        val csteSteNm: String,                   // 보관상태명
        val depPlace: String,                    // 보관장소
        val fdFilePathImg: String,               // 습득물사진파일경로
        val fdHor: Int,                          // 습득장소
        val fdPlace: String,                     // 습득시간
        val fdPrdtNm: String,                    // 물품명
        val fdSn: Int,                           // 습득순번
        val fdYmd: String,                       // 습득일자
        val fndKeepOrgnSeNm: String,             // 습득물보관기관구분명
        val orgId: String,                       // 기관아이디
        val orgNm: String,                       // 기관명
        val prdtClNm: String,                    // 물품분류명
        val tel: String,                         // 전화번호
        val uniq: String                         // 특이사항
)