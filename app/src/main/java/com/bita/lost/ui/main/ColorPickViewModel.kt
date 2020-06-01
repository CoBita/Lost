package com.bita.lost.ui.main

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import com.bita.lost.repo.ColorRepository
import com.bita.lost.repo.data.ColorCode
import kotlinx.coroutines.launch

class ColorPickViewModel(private val colorRepository: ColorRepository) : HeaderBaseViewModel() {

    override val title: ObservableField<String> = ObservableField("물건 색상")
    val colorList = ObservableArrayList<ColorCode>()


    fun getColorList() {
        scope.launch {
            val list = colorRepository.getColorCodeList()
            colorList.clear()
            colorList.addAll(list)
        }
    }

}