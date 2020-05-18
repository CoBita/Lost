package com.bita.lost.ui.main

import com.bita.lost.Const
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MainDataSource {
    @GET("/${Const.SERVICE_KEY}/json/SearchLostArticleService/{START_INDEX}/{END_INDEX}/{CATE}/{WB_CODE}/{GET_NAME}")
    fun 분실물조회(
        @Path("START_INDEX") startIndex: Int,
        @Path("END_INDEX") endIndex: Int,
        @Path("CATE", encoded = true) cate: String,
        @Path("WB_CODE") wbCode: String,
        @Path("GET_NAME", encoded = true) getName: String
    ) : Call<String>
}