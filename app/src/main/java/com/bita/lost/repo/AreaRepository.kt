package com.bita.lost.repo

import com.bita.lost.repo.data.AreaCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface AreaRepository {
    suspend fun getAreaList(): List<AreaCode>
}

class AreaRepositoryImpl() : AreaRepository {
    override suspend fun getAreaList(): List<AreaCode> = withContext(Dispatchers.Default) { AreaCode.values().toList() }
}
