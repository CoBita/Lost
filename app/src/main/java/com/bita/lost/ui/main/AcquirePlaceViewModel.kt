package com.bita.lost.ui.main

import com.and.base.log.Log
import com.bita.lost.base.LViewModel
import com.bita.lost.repo.AcquireRepository
import kotlinx.coroutines.launch

class AcquirePlaceViewModel(private val acquireRepository: AcquireRepository) : LViewModel() {

    fun getAcquirePlaceCode() {
        scope.launch {
            val code = acquireRepository.getAcquirePlaceCode()
            Log.i(code)
        }
    }
}