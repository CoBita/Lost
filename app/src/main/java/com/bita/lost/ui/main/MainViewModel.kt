package com.bita.lost.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.and.base.log.Log
import com.bita.lost.base.LViewModel
import com.bita.lost.repo.data.AcquireData

class MainViewModel : LViewModel() {


    private val _selectAcquirePlaceData = MutableLiveData<AcquireData>()
    val selectAcquirePlaceData: LiveData<AcquireData> get() = _selectAcquirePlaceData


    fun setAcquirePlaceData(acquireData: AcquireData) {
        _selectAcquirePlaceData.postValue(acquireData)
        Log.i("setAcquirePlaceData : $acquireData")
    }
}