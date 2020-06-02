package com.bita.lost.repo

import com.bita.lost.repo.data.Body
import com.bita.lost.repo.data.LostList
import retrofit2.http.GET
import retrofit2.http.Query

interface ListDataSource {
    @GET("1320000/LosfundInfoInqireService/getLosfundInfoAccToClAreaPd")
    suspend fun 분실물조회(
            @Query("PRDT_CL_CD_01") prdtClCd01: String,
            @Query("START_YMD") startYmd: String,
            @Query("END_YMD") endYmd: String,
            @Query("N_FD_LCT_CD") nFdLctCd: String,
            @Query("pageNo") pageNo: Int,
            @Query("numOfRows") numOfRows: Int = 20
    ): Body<LostList>
}