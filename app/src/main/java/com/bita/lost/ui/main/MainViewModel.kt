package com.bita.lost.ui.main

import com.and.base.log.Log
import com.bita.lost.base.LViewModel
import com.bita.lost.repo.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository) : LViewModel() {


    fun getAcquirePlaceCode() {
        scope.launch {
            val code = repository.getAcquirePlaceCode()
            Log.i(code)
        }
    }
}