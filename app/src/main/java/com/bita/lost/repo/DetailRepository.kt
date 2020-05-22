@file:Suppress("NonAsciiCharacters", "FunctionName")

package com.bita.lost.repo

interface DetailRepository {

    suspend fun 분실물상세조회(id: String): Any
}

class DetailRepositoryImpl(private val detailDataSource: DetailDataSource) : DetailRepository {
    override suspend fun 분실물상세조회(id: String): Any = detailDataSource.분실물상세조회(id)
}