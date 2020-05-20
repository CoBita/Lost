package com.bita.lost.ui.main

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bita.lost.base.LViewModel

class SearchViewModel : LViewModel() {

    val searchText = ObservableField<String>()

    private val _searchFinish = MutableLiveData<String>()
    val searchFinish: LiveData<String> get() = _searchFinish


    fun result() {
        val inputSearch = searchText.get()
        _searchFinish.postValue(inputSearch)
    }

}
