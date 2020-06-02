@file:Suppress("FunctionName", "NonAsciiCharacters")

package com.bita.lost.ui.detail

import androidx.lifecycle.MutableLiveData
import com.bita.lost.base.LViewModel
import com.bita.lost.common.progress
import com.bita.lost.repo.DetailRepository
import com.bita.lost.repo.data.DetailItem
import kotlinx.coroutines.launch

class DetailViewModel(private val detailRepository: DetailRepository) : LViewModel() {
    private val _result = MutableLiveData<DetailItem>()
    val result: MutableLiveData<DetailItem> get() = _result

    fun 습득물상세조회(id: String, seq : Int) {
        scope.launch {
            _result.postValue(detailRepository.습득물상세조회(id, seq).response.body.item)
        }.progress(_isProgress)
    }
}