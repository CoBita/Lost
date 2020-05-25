@file:Suppress("NonAsciiCharacters", "FunctionName")

package com.bita.lost.repo

import com.bita.lost.repo.data.LostListFrame

interface ListRepository {
    suspend fun 분실물조회(lstPlace: String, lstPrdtNm: String, pageNo: Int): LostListFrame
}

class ListRepositoryImpl(private val dataSource: ListDataSource) : ListRepository {
    override suspend fun 분실물조회( lstPlace: String, lstPrdtNm: String, pageNo: Int)
            : LostListFrame = dataSource.분실물조회(lstPlace, lstPrdtNm, pageNo)
}