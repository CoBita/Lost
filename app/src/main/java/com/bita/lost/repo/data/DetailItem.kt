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

// todo 참고 후 주석 제거 해주세요.
// "atcId":"F2018113000002322",
// "csteSteNm":"종결",
// "depPlace":"서울강북경찰서",
// "fdFilePathImg":"https:\/\/www.lost112.go.kr\/lostnfs\/images\/sub\/img04_no_img.gif",
// "fdHor":24,
// "fdPlace":"노상",
// "fdPrdtNm":"영성용가박",
// "fdSn":1,
// "fdYmd":"2018-11-30",
// "fndKeepOrgnSeNm":"관서보관",
// "orgId":"O0000129",
// "orgNm":"서울강북경찰서",
// "prdtClNm":"가방 > 여성용가방",
// "tel":"02-944-4347",
// "uniq":"내용\r\n\r\n 번동파출소에서는 [2018.11.30]  [영성용가박(핑크(분홍)색)]을 습득\/보관 하였습니다.\r\n분실하신 분께서는 본인을 증명할 수 있는 서류를 지참하시어 보관중으로 기재되어 있는 기관에\r\n방문하시어 보관물품을 수령하시기 바랍니다.\r\n\r\n특이사항 : 없음" } } }
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