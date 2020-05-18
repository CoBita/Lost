package com.bita.lost.ui.main

import retrofit2.Call

interface MainRepository {
    fun 분실물조회(
            startIndex: Int,
            endIndex: Int,
            cate: String,
            wbCode: String,
            getName: String
    ): Call<String>
}

class MainRepositoryImpl(val dataSource: MainDataSource) : MainRepository {
    override fun 분실물조회(startIndex: Int, endIndex: Int, cate: String, wbCode: String, getName: String): Call<String>
            = dataSource.분실물조회(startIndex, endIndex, cate, wbCode, getName)
}