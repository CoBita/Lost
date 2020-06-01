package com.bita.lost.ui.main

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import com.bita.lost.repo.AreaRepository
import com.bita.lost.repo.data.AreaCode
import kotlinx.coroutines.launch

class AreaViewModel(private val areaRepository: AreaRepository) : HeaderBaseViewModel() {

    override val title: ObservableField<String>
        get() = ObservableField("지역 선택")

    val areaList = ObservableArrayList<AreaCode>()

    fun getAreaList() {
        scope.launch {
            val list = areaRepository.getAreaList()
            areaList.clear()
            areaList.addAll(list)
        }
    }

}