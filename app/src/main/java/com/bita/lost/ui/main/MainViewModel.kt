package com.bita.lost.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.and.base.log.Log
import com.bita.lost.base.LViewModel
import com.bita.lost.repo.AcquirePlaceCode
import com.bita.lost.repo.AcquisitionCode

class MainViewModel : LViewModel() {


    private val _selectAcquirePlaceData = MutableLiveData<AcquirePlaceCode>()
    val selectAcquirePlaceData: LiveData<AcquirePlaceCode> get() = _selectAcquirePlaceData

    private val _selectAcquisitionData = MutableLiveData<AcquisitionCode>()
    val selectAcquisitionData: LiveData<AcquisitionCode> get() = _selectAcquisitionData

    private val _selectSearch = MutableLiveData<String>()
    val selectSearch: LiveData<String> get() = _selectSearch

    private val _finish = MutableLiveData<Boolean>()
    val finish: LiveData<Boolean> get() = _finish


    fun setAcquirePlaceData(acquireData: AcquirePlaceCode) {
        _selectAcquirePlaceData.postValue(acquireData)
    }

    fun setAcquisitionData(acquisitionCode: AcquisitionCode) {
        _selectAcquisitionData.postValue(acquisitionCode)
    }

    fun setSearchText(searchText: String?) {
        _selectSearch.postValue(searchText)
        result()
    }


    fun result() {
        val acquirePlaceData = _selectAcquirePlaceData.value
        val acquisitionData = _selectAcquisitionData.value

        if (acquirePlaceData == null) {
            _alertMessage.postValue("잃어버린 장소를 선택 해 주세요.")
            return
        }

        if (acquisitionData == null) {
            _alertMessage.postValue("잃어버린 물건을 선택 해 주세요.")
            return
        }


        Log.i("acquirePlaceData : $acquirePlaceData")
        Log.i("acquisitionData : $acquisitionData")

        // TODO : Data 넘기는 부분 추가해야함
        _finish.postValue(true)
    }
}