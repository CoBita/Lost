@file:Suppress("NonAsciiCharacters", "FunctionName")

package com.bita.lost.repo

import com.bita.lost.repo.data.Body
import com.bita.lost.repo.data.LostList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

interface ListRepository {
    suspend fun  분실물조회(prdtClCd01 : String, startYmd: String, endYmd: String, nFdLctCd: String, pageNo: Int): Body<LostList>
}

class ListRepositoryImpl(private val dataSource: ListDataSource) : ListRepository {
    private val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    override suspend fun 분실물조회(prdtClCd01 : String, startYmd: String, endYmd: String, nFdLctCd: String, pageNo: Int)
            : Body<LostList> = withContext(coroutineContext) { dataSource.분실물조회(prdtClCd01, startYmd, endYmd, nFdLctCd, pageNo) }
}