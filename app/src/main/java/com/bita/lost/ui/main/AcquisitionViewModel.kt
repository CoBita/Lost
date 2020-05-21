package com.bita.lost.ui.main

import androidx.databinding.ObservableArrayList
import com.bita.lost.base.LViewModel
import com.bita.lost.repo.AcquireRepository
import com.bita.lost.repo.data.AcquisitionCode
import kotlinx.coroutines.launch

class AcquisitionViewModel(private val acquireRepository: AcquireRepository) : LViewModel() {

    val codeList = ObservableArrayList<AcquisitionCode>()

    fun getAcquisitionCode() {
        scope.launch {
            val code = acquireRepository.getAcquisitionCode()
            codeList.clear()
            codeList.addAll(code)
        }
    }
}