package com.bita.lost.ui.main

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.and.base.log.Log
import com.bita.lost.base.LViewModel
import com.bita.lost.repo.data.AcquirePlaceCode
import com.bita.lost.repo.data.AcquisitionCode
import com.bita.lost.repo.data.MainResultData

class MainViewModel : LViewModel() {

    val infoText = ObservableField(acquireInfoText)

    val selectAcquirePlaceIcon = ObservableField<Any>()
    val selectAcquisitionIcon = ObservableField<Any>()

    private val _replaceFragment = MutableLiveData<String>()
    val replaceFragment: LiveData<String> get() = _replaceFragment

    private val _finish = MutableLiveData<MainResultData>()
    val finish: LiveData<MainResultData> get() = _finish

    private var selectAcquirePlaceData: AcquirePlaceCode? = null
    private var selectAcquisitionData: AcquisitionCode? = null


    init {
        _replaceFragment.postValue(ACQUIRE_PLACE_TAG)
    }

    fun setAcquirePlaceData(acquireData: AcquirePlaceCode) {
        selectAcquirePlaceData = acquireData
        infoText.set(acquisitionInfoText)

        if (acquireData == AcquirePlaceCode.직접입력) {
            selectAcquirePlaceIcon.set(acquireData.description)
        } else {
            selectAcquirePlaceIcon.set(acquireData.icon)
        }
        _replaceFragment.postValue(ACQUISITION_TAG)
    }

    fun setAcquisitionData(acquisitionCode: AcquisitionCode) {
        selectAcquisitionData = acquisitionCode
        if (acquisitionCode == AcquisitionCode.직접입력) {
            selectAcquisitionIcon.set(acquisitionCode.description)
        } else {
            selectAcquisitionIcon.set(acquisitionCode.icon)
        }
        result()
    }


    fun result() {
        if (selectAcquirePlaceData == null) {
            _alertMessage.postValue("잃어버린 장소를 선택 해 주세요.")
            return
        }

        if (selectAcquisitionData == null) {
            _alertMessage.postValue("잃어버린 물건을 선택 해 주세요.")
            return
        }

        Log.i("acquirePlaceData : $selectAcquirePlaceData")
        Log.i("acquisitionData : $selectAcquisitionData")

        selectAcquirePlaceData?.let { placeData ->
            selectAcquisitionData?.let { acquisitionData ->
                val mainResultData = MainResultData(placeData, acquisitionData)
                _finish.postValue(mainResultData)
            }
        }
    }

    fun clickAcquirePlaceIcon() {
        _replaceFragment.postValue(ACQUIRE_PLACE_TAG)
    }

    fun clickAcquisitionIcon() {
        _replaceFragment.postValue(ACQUISITION_TAG)
    }

    companion object {
        private const val acquireInfoText = "어디에서\n잃어버렸나요?"
        private const val acquisitionInfoText = "무엇을\n잃어버렸나요?"

        const val ACQUIRE_PLACE_TAG = "AcquirePlace"
        const val ACQUISITION_TAG = "Acquisition"
    }

}