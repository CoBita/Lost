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

// todo 데이터 참고 후 주석 삭제 필요
// "atcId":"L2020052700000011",
// "clrNm":"블랙(검정)",
// "lstFilePathImg":"https:\/\/www.lost112.go.kr\/lostnfs\/images\/uploadImg\/20200527\/20200527122712643.jpg",
// "lstHor":17,
// "lstLctNm":"경기도",
// "lstPlace":"강원고속 버스",
// "lstPlaceSeNm":"버스",
// "lstPrdtNm":"구찌지갑",
// "lstSbjt":"구찌지갑",
// "lstSteNm":"온라인 접수",
// "lstYmd":"2020-05-26",
// "orgId":"O0000828",
// "orgNm":"안산상록경찰서",
// "prdtClNm":"지갑 > 남성용 지갑",
// "tel":"031-8040-2150",
// "uniq":"개인정보보호정책에 의해 정보가 제공되지 않습니다."}}}}
data class DetailItem(
        val atcId: String,                  // 관리ID
        val clrNm: String,                  // 색상명
        val lstFilePathImg: String,         // 분실물이미지명
        val lstHor: Int,                    // 분실시간
        val lstLctNm: String,               // 분실지역명
        val lstPlace: String,               // 분실장소
        val lstPlaceSeNm: String,           // 분실장소구분명
        val lstPrdtNm: String,              // 물품명
        val lstSbjt: String,                // 게시제목
        val lstSteNm: String,               // 분실물 상태명
        val lstYmd: String,                 // 분실일자
        val orgId: String,                  // 기관ID
        val orgNm: String,                  // 기관명
        val prdtClNm: String,               // 물품분류명
        val tel: String,                    // 기관전화번호
        val uniq: String                    // 특이사항
)