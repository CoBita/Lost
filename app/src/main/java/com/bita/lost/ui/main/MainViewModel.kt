package com.bita.lost.ui.main

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.and.base.log.Log
import com.bita.lost.base.LViewModel
import com.bita.lost.repo.data.AcquirePlaceCode
import com.bita.lost.repo.data.AcquisitionCode
import com.bita.lost.repo.data.MainResultData

class MainViewModel : LViewModel() {

    val infoText = ObservableField(acquireInfoText)

    val selectAcquirePlaceIcon = ObservableInt()
    val selectAcquisitionIcon = ObservableInt()

    private val _replaceFragment = MutableLiveData<String>()
    val replaceFragment: LiveData<String> get() = _replaceFragment

    private val _finish = MutableLiveData<MainResultData>()
    val finish: LiveData<MainResultData> get() = _finish

    private var selectAcquirePlaceData: AcquirePlaceCode? = null
    private var selectAcquisitionData: AcquisitionCode? = null
    private var selectSearchText: String? = null


    init {
        _replaceFragment.postValue(ACQUIRE_PLACE_TAG)
    }

    fun setAcquirePlaceData(acquireData: AcquirePlaceCode) {
        selectAcquirePlaceData = acquireData
        infoText.set(acquisitionInfoText)
        selectAcquirePlaceIcon.set(acquireData.icon)
        _replaceFragment.postValue(ACQUISITION_TAG)
    }

    fun setAcquisitionData(acquisitionCode: AcquisitionCode) {
        selectAcquisitionData = acquisitionCode
        infoText.set(searchInfoText)
        selectAcquisitionIcon.set(acquisitionCode.icon)
        _replaceFragment.postValue(SEARCH_TAG)
    }

    fun setSearchText(searchText: String?) {
        selectSearchText = searchText
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
        Log.i("searchText : $selectSearchText")


        selectAcquirePlaceData?.let { placeData ->
            selectAcquisitionData?.let { acquisitionData ->
                val mainResultData = MainResultData(placeData, acquisitionData, selectSearchText)
                _finish.postValue(mainResultData)
            }
        }
    }

    companion object {
        private const val acquireInfoText = "어디에서\n잃어버렸나요?"
        private const val acquisitionInfoText = "무엇을\n잃어버렸나요?"
        private const val searchInfoText = "검색어를\n입력하시겠어요?"

        const val ACQUIRE_PLACE_TAG = "AcquirePlace"
        const val ACQUISITION_TAG = "Acquisition"
        const val SEARCH_TAG = "Search"
    }

}