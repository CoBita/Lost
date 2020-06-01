package com.bita.lost.ui.main

import androidx.databinding.ObservableArrayList
import com.bita.lost.base.LViewModel
import com.bita.lost.repo.ColorRepository
import com.bita.lost.repo.data.ColorCode
import kotlinx.coroutines.launch

class ColorPickViewModel(private val colorRepository: ColorRepository) : LViewModel() {

    val colorList = ObservableArrayList<ColorCode>()

    fun getColorList() {
        scope.launch {
            val list = colorRepository.getColorCodeList()
            colorList.clear()
            colorList.addAll(list)
        }
    }
}