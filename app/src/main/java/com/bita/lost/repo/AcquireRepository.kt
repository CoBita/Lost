@file:Suppress("NonAsciiCharacters", "FunctionName", "LocalVariableName")

package com.bita.lost.repo

import com.bita.lost.repo.data.AcquirePlaceCode
import com.bita.lost.repo.data.AcquisitionCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface AcquireRepository {
    suspend fun getAcquirePlaceCode(): List<AcquirePlaceCode>

    suspend fun getAcquisitionCode(): List<AcquisitionCode>
}

class AcquireRepositoryImpl : AcquireRepository {
    override suspend fun getAcquirePlaceCode(): List<AcquirePlaceCode> = withContext(Dispatchers.Default) {
        return@withContext AcquirePlaceCode.values().asList()
    }

    override suspend fun getAcquisitionCode(): List<AcquisitionCode> = withContext(Dispatchers.Default) {
        return@withContext AcquisitionCode.values().asList()
    }
}