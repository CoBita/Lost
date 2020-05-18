@file:Suppress("NonAsciiCharacters", "FunctionName")

package com.bita.lost.repo

import com.bita.lost.repo.data.LostListFrame

interface ListRepository {
    suspend fun 분실물조회(startIndex: Int, endIndex: Int, cate: String, wbCode: String, getName: String): LostListFrame
}

class ListRepositoryImpl(private val dataSource: ListDataSource) : ListRepository {
    override suspend fun 분실물조회(startIndex: Int, endIndex: Int, cate: String, wbCode: String, getName: String)
            : LostListFrame = dataSource.분실물조회(startIndex, endIndex, cate, wbCode, getName)
}