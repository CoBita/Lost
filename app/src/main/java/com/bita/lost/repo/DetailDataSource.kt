@file:Suppress("NonAsciiCharacters", "unused", "FunctionName")

package com.bita.lost.repo

import com.bita.lost.Const
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailDataSource {
    @GET("/${Const.SERVICE_KEY}/json/SearchLostArticleInfoService/1/5/{ID}")
    suspend fun 분실물상세조회(@Path("ID") cate: String): Any
}