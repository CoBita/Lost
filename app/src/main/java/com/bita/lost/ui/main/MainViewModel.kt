package com.bita.lost.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bita.lost.base.LViewModel
import com.bita.lost.repo.data.AcquirePlaceData

class MainViewModel : LViewModel() {


    private val _selectAcquirePlaceData = MutableLiveData<AcquirePlaceData>()
    val selectAcquirePlaceData: LiveData<AcquirePlaceData> get() = _selectAcquirePlaceData


    fun setAcquirePlaceData(acquirePlaceData: AcquirePlaceData) {
        _selectAcquirePlaceData.postValue(acquirePlaceData)
    }
}