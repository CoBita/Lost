package com.bita.lost.repo

import com.bita.lost.Const
import com.bita.lost.repo.data.LostListFrame
import retrofit2.http.GET
import retrofit2.http.Path

interface ListDataSource {
    @GET("/${Const.SERVICE_KEY}/json/SearchLostArticleService/{START_INDEX}/{END_INDEX}/{CATE}/{WB_CODE}/{GET_NAME}")
    suspend fun 분실물조회(
            @Path("START_INDEX") startIndex: Int,
            @Path("END_INDEX") endIndex: Int,
            @Path("CATE") cate: String,
            @Path("WB_CODE") wbCode: String,
            @Path("GET_NAME") getName: String
    ): LostListFrame
}