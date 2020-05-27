@file:Suppress("NonAsciiCharacters", "FunctionName")

package com.bita.lost.repo

import com.bita.lost.repo.data.DetailItemFrame
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

interface DetailRepository {

    suspend fun 분실물상세조회(id: String): DetailItemFrame
}

class DetailRepositoryImpl(private val detailDataSource: DetailDataSource) : DetailRepository {
    private val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    override suspend fun 분실물상세조회(id: String): DetailItemFrame = withContext(coroutineContext) { detailDataSource.분실물상세조회(id) }
}