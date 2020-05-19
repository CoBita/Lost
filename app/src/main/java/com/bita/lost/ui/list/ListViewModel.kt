@file:Suppress("NonAsciiCharacters", "FunctionName")

package com.bita.lost.ui.list

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bita.lost.base.LViewModel
import com.bita.lost.repo.ListRepository
import com.bita.lost.repo.data.LostItem
import com.bita.lost.repo.data.LostListFrame
import kotlinx.coroutines.launch

class ListViewModel(private val repository: ListRepository) : LViewModel() {
    private val _result = MutableLiveData<LostListFrame>()
    val result: LiveData<LostListFrame> get() = _result

    val list = ObservableArrayList<LostItem>()

    fun 분실물조회(startIndex: Int, endIndex: Int, cate: String, wbCode: String, getName: String) {
        scope.launch {
            val result = repository.분실물조회(startIndex, endIndex, cate, wbCode, getName)
            // todo 깜빡거리는지 확인 필요
            list.addAll(result.service.items)
            _result.postValue(result)
        }
    }
}