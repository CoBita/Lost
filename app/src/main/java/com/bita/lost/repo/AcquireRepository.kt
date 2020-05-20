@file:Suppress("NonAsciiCharacters", "FunctionName", "LocalVariableName")

package com.bita.lost.repo

import com.bita.lost.repo.data.AcquireData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface AcquireRepository {
    suspend fun getAcquirePlaceCode(): ArrayList<AcquireData>

    suspend fun getAcquisitionCode(): ArrayList<AcquireData>
}

class AcquireRepositoryImpl : AcquireRepository {
    override suspend fun getAcquirePlaceCode(): ArrayList<AcquireData> = withContext(Dispatchers.Default) {
        val acquirePlaceCodeList = arrayListOf<AcquireData>()
        AcquirePlaceCode.values().forEach {
            acquirePlaceCodeList.add(AcquireData(it.description, it.code, it.icon))
        }
        return@withContext acquirePlaceCodeList
    }

    override suspend fun getAcquisitionCode(): ArrayList<AcquireData> {
        TODO("Not yet implemented")
    }
}