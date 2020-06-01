package com.bita.lost.ui.main

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.and.base.common.Event
import com.bita.lost.base.LViewModel
import com.bita.lost.repo.ColorRepository
import com.bita.lost.repo.data.ColorCode
import kotlinx.coroutines.launch

class ColorPickViewModel(private val colorRepository: ColorRepository) : LViewModel() {

    val colorList = ObservableArrayList<ColorCode>()

    private val _dismiss = MutableLiveData<Event<Unit>>()
    val dismiss: LiveData<Event<Unit>> = _dismiss

    fun getColorList() {
        scope.launch {
            val list = colorRepository.getColorCodeList()
            colorList.clear()
            colorList.addAll(list)
        }
    }

    fun dismiss() {
        _dismiss.value = Event(Unit)
    }
}