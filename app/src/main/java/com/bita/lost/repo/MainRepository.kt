@file:Suppress("NonAsciiCharacters", "FunctionName", "LocalVariableName")

package com.bita.lost.repo

import com.bita.lost.repo.data.AcquirePlaceData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface MainRepository {
    suspend fun getAcquirePlaceCode(): ArrayList<AcquirePlaceData>
}

class MainRepositoryImpl : MainRepository {
    override suspend fun getAcquirePlaceCode(): ArrayList<AcquirePlaceData> = withContext(Dispatchers.Default) {
        val acquirePlaceCodeList = arrayListOf<AcquirePlaceData>()
        AcquirePlaceCode.values().forEach {
            acquirePlaceCodeList.add(AcquirePlaceData(it.name, it.code))
        }
        return@withContext acquirePlaceCodeList
    }
}