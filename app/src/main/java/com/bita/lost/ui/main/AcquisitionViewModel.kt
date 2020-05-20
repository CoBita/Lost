package com.bita.lost.ui.main

import androidx.databinding.ObservableArrayList
import com.bita.lost.base.LViewModel
import com.bita.lost.repo.AcquireRepository
import com.bita.lost.repo.data.AcquireData
import kotlinx.coroutines.launch

class AcquisitionViewModel(private val acquireRepository: AcquireRepository) : LViewModel() {


    val codeList = ObservableArrayList<AcquireData>()

    fun getAcquirePlaceCode() {
        scope.launch {
            val code = acquireRepository.getAcquirePlaceCode()
            codeList.clear()
            codeList.addAll(code)
        }
    }
}