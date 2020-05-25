package com.bita.lost.repo

import com.bita.lost.Const
import com.bita.lost.repo.data.LostListFrame
import retrofit2.http.GET
import retrofit2.http.Query

interface ListDataSource {
    @GET("/1320000/LostGoodsInfoInqireService/getLostGoodsInfoAccTpNmCstdyPlace")
    suspend fun 분실물조회(
            @Query("LST_PLACE") lstPlace: String,
            @Query("LST_PRDT_NM") lstPrdtNm: String,
            @Query("pageNo") pageNo: Int,
            @Query("serviceKey", encoded = true) serviceKey: String = Const.SERVICE_KEY,
            @Query("_type") type: String = "json",
            @Query("numOfRows") numOfRows: Int = 20
    ): LostListFrame
}