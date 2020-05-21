package com.bita.lost.ui.main

import androidx.databinding.ObservableArrayList
import com.bita.lost.base.LViewModel
import com.bita.lost.repo.data.AcquirePlaceCode
import com.bita.lost.repo.AcquireRepository
import kotlinx.coroutines.launch

class AcquirePlaceViewModel(private val acquireRepository: AcquireRepository) : LViewModel() {


    val codeList = ObservableArrayList<AcquirePlaceCode>()

    fun getAcquirePlaceCode() {
        scope.launch {
            val code = acquireRepository.getAcquirePlaceCode()
            codeList.clear()
            codeList.addAll(code)
        }
    }
}