package com.bita.lost.ui.main

import androidx.core.util.Pair
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.and.base.common.Event
import com.and.base.component.SDF
import com.and.base.component.format
import com.and.base.log.Log
import com.bita.lost.base.LViewModel
import com.bita.lost.repo.data.AreaCode
import com.bita.lost.repo.data.ProductCode
import com.bita.lost.repo.data.SearchResultData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener

class SearchViewModel : LViewModel() {

    val areaCode = ObservableField<AreaCode>()          // 지역
    val productCode = ObservableField<ProductCode>()    // 물건
    val startDate = ObservableField<String>()           // 시작날짜
    val endDate = ObservableField<String>()             // 끝날짜

    val areaPickFr = AreaPickFr.newInstance { areaCode.set(it) }
    val productPickFr = ProductPickFr.newInstance { productCode.set(it) }

    // Fragment Show Event LiveData
    private val _showBottomSheet = MutableLiveData<BottomSheetDialogFragment>()
    val showBottomSheet: LiveData<BottomSheetDialogFragment> get() = _showBottomSheet


    private val _resultSearchData = MutableLiveData<SearchResultData>()
    val resultSearchData: LiveData<SearchResultData> get() = _resultSearchData

    private val _showRangeDatePicker = MutableLiveData<Event<Unit>>()
    val showRangeDatePicker: LiveData<Event<Unit>> get() = _showRangeDatePicker


    fun showFragment(fragment: BottomSheetDialogFragment) {
        _showBottomSheet.postValue(fragment)
    }

    fun showDatePicker() {
        _showRangeDatePicker.value = Event(Unit)
    }

    fun datePickerListener(): MaterialPickerOnPositiveButtonClickListener<in Pair<Long, Long>> {
        return MaterialPickerOnPositiveButtonClickListener {
            it?.let { pair ->
                val first = pair.first ?: 0
                val second = pair.second ?: 0

                val formatFirstDate = first.format(SDF.yyyymmdd_2)
                val formatSecondDate = second.format(SDF.yyyymmdd_2)
                Log.i("startDate : $formatFirstDate , endDate : $formatSecondDate")

                startDate.set(formatFirstDate)
                endDate.set(formatSecondDate)
            }
        }
    }


    fun search() {
        val getAreaCode = areaCode.get()
        val getProductCode = productCode.get()
        val getStartDate = startDate.get()
        val getEndDate = endDate.get()

        if (getAreaCode == null) {
            _alertMessage.postValue("지역을 선택해 주세요.")
            return
        }

        if (getStartDate.isNullOrEmpty()) {
            _alertMessage.postValue("날짜를 선택해 주세요.")
            return
        }

        if (getEndDate.isNullOrEmpty()) {
            _alertMessage.postValue("날짜를 선택해 주세요.")
            return
        }

        if (getProductCode == null) {
            _alertMessage.postValue("잃어버린 물건을 선택해 주세요.")
            return
        }
        val searchResultData = SearchResultData(getAreaCode, getProductCode, getStartDate, getEndDate)
        _resultSearchData.postValue(searchResultData)
    }

}
