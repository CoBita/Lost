@file:Suppress("NonAsciiCharacters", "unused", "FunctionName")

package com.bita.lost.repo

import com.bita.lost.Const
import com.bita.lost.repo.data.DetailItemFrame
import retrofit2.http.GET
import retrofit2.http.Query

interface DetailDataSource {
    @GET("/1320000/LosfundInfoInqireService/getLosfundDetailInfo")
    suspend fun 습득물상세조회(
            @Query("ATC_ID") id: String,
            @Query("FD_SN") seq : Int,
            @Query("serviceKey", encoded = true) serviceKey: String = Const.SERVICE_KEY,
            @Query("_type") type: String = "json"
    ) : DetailItemFrame
}