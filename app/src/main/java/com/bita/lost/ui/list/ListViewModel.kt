@file:Suppress("NonAsciiCharacters", "FunctionName")

package com.bita.lost.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bita.lost.base.LViewModel
import com.bita.lost.repo.ListRepository
import com.bita.lost.repo.data.LostListFrame
import kotlinx.coroutines.launch

class ListViewModel(private val repository: ListRepository) : LViewModel() {
    private val _result = MutableLiveData<LostListFrame>()
    val result: LiveData<LostListFrame> get() = _result

    fun 분실물조회(startIndex: Int, endIndex: Int, cate: String, wbCode: String, getName: String) {
        scope.launch {
            val result = repository.분실물조회(startIndex, endIndex, cate, wbCode, getName)
            _result.postValue(result)
        }
    }
}