package com.bita.lost.repo

import com.bita.lost.repo.data.ColorCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface ColorRepository {
    suspend fun getColorCodeList(): List<ColorCode>
}

class ColorRepositoryImpl : ColorRepository {
    override suspend fun getColorCodeList(): List<ColorCode> = withContext(Dispatchers.Default) { ColorCode.values().toList() }
}