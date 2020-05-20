package com.bita.lost.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.and.base.log.Log
import com.bita.lost.base.LViewModel
import com.bita.lost.repo.AcquirePlaceCode

class MainViewModel : LViewModel() {


    private val _selectAcquirePlaceData = MutableLiveData<AcquirePlaceCode>()
    val selectAcquirePlaceData: LiveData<AcquirePlaceCode> get() = _selectAcquirePlaceData


    fun setAcquirePlaceData(acquireData: AcquirePlaceCode) {
        _selectAcquirePlaceData.postValue(acquireData)
        Log.i("setAcquirePlaceData : $acquireData")
    }
}