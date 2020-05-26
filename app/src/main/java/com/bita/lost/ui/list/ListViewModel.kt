@file:Suppress("NonAsciiCharacters", "FunctionName")

package com.bita.lost.ui.list

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import com.and.base.log.Log
import com.bita.lost.base.LViewModel
import com.bita.lost.common.progress
import com.bita.lost.repo.ListRepository
import com.bita.lost.repo.data.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class ListViewModel(private val repository: ListRepository) : LViewModel() {
    private var page = 0
    lateinit var lstPrdtNm: AcquisitionCode
    lateinit var lstPlace: AcquirePlaceCode

    val list = ObservableArrayList<LostItem>()
    val hasNext = ObservableBoolean(true)
    val isLoadFinish = ObservableBoolean(false)
    private val handler = CoroutineExceptionHandler { _, e ->
        Log.w("exception occured")
        e.printStackTrace()
    }

    fun init(lstPlace: AcquirePlaceCode, lstPrdtNm: AcquisitionCode) {
        this.lstPlace = lstPlace
        this.lstPrdtNm = lstPrdtNm
    }

    fun getFirstLostList() {
        if (list.isEmpty()) getLostList()
    }

    fun getLostList() {
        scope.launch(handler) {
            page++
            val result: Body<LostList> = repository.분실물조회(lstPlace.description, lstPrdtNm.description, page)
            isLoadFinish.set(true)
            result.items.items?.let { list.addAll(it) }
            if (result.totalCount <= page * 20) hasNext.set(false)
        }.progress(_isProgress)
    }
}