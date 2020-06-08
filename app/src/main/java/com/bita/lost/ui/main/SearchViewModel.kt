package com.bita.lost.ui.main

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bita.lost.base.LViewModel
import com.bita.lost.repo.data.AreaCode
import com.bita.lost.repo.data.ProductCode
import com.bita.lost.repo.data.SearchResultData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SearchViewModel : LViewModel() {

    val areaCode = ObservableField<AreaCode>()          // 지역
    val productCode = ObservableField<ProductCode>()    // 물건
    val startDate = ObservableField<String>()           // 시작날짜
    val endDate = ObservableField<String>()             // 끝날짜

    val areaPickFr = AreaPickFr.newInstance { areaCode.set(it) }
    val productPickFr = ProductPickFr.newInstance { productCode.set(it) }

    val startDateResult: (result: String) -> Unit = { startDate.set(it) }
    val endDateResult: (result: String) -> Unit = { endDate.set(it) }

    // Fragment Show Event LiveData
    private val _showBottomSheet = MutableLiveData<BottomSheetDialogFragment>()
    val showBottomSheet: LiveData<BottomSheetDialogFragment> get() = _showBottomSheet

    // Calendar LiveEvent
    private val _showDatePicker = MutableLiveData<(result: String) -> Unit>()
    val showDatePicker: LiveData<(result: String) -> Unit> get() = _showDatePicker

    private val _resultSearchData = MutableLiveData<SearchResultData>()
    val resultSearchData: LiveData<SearchResultData> get() = _resultSearchData


    fun showFragment(fragment: BottomSheetDialogFragment) {
        _showBottomSheet.postValue(fragment)
    }

    fun showDatePicker(result: (result: String) -> Unit) {
        _showDatePicker.postValue(result)
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
