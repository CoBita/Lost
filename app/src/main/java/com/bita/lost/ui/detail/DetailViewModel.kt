@file:Suppress("FunctionName", "NonAsciiCharacters", "SpellCheckingInspection")

package com.bita.lost.ui.detail

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bita.lost.base.LViewModel
import com.bita.lost.common.progress
import com.bita.lost.repo.DetailRepository
import com.bita.lost.repo.data.DetailItem
import kotlinx.coroutines.launch

class DetailViewModel(private val detailRepository: DetailRepository) : LViewModel() {

    val imageUrl = ObservableField<String>()    // 이미지 URL
    val fdPlace = ObservableField<String>()     // 습득 장소
    val tel = ObservableField<String>()         // 전화번호
    val uniq = ObservableField<String>()        // 특이사항

    private val _goTel = MutableLiveData<String>()
    val goTel: LiveData<String> get() = _goTel


    fun 습득물상세조회(id: String, seq: Int) {
        scope.launch {
            val detailResult = detailRepository.습득물상세조회(id, seq).response.body.item
            습득물결과(detailResult)
        }.progress(_isProgress)
    }


    private fun 습득물결과(data: DetailItem) {
        imageUrl.set(data.fdFilePathImg)
        fdPlace.set("습득장소 : ${data.fdPlace}")
        tel.set("전화번호 : ${data.tel}")
        uniq.set(data.uniq)
    }

    fun clickTel() {
        val telNum = tel.get()
        _goTel.postValue(telNum)
    }
}