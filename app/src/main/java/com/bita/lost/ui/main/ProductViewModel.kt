package com.bita.lost.ui.main

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import com.bita.lost.repo.ProductRepository
import com.bita.lost.repo.data.ProductCode
import kotlinx.coroutines.launch

class ProductViewModel(private val productRepository: ProductRepository) : HeaderBaseViewModel() {

    override val title: ObservableField<String> = ObservableField("물건 종류 선택")
    val productList = ObservableArrayList<ProductCode>()

    fun getProductList() {
        scope.launch {
            val list = productRepository.getProductList()
            productList.clear()
            productList.addAll(list)
        }
    }
}