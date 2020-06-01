package com.bita.lost.repo

import com.bita.lost.repo.data.ProductCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface ProductRepository {
    suspend fun getProductList(): List<ProductCode>
}

class ProductRepositoryImpl : ProductRepository {
    override suspend fun getProductList(): List<ProductCode> = withContext(Dispatchers.Default) { ProductCode.values().toList() }
}