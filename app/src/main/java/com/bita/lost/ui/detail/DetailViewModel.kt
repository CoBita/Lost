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

    val title = ObservableField<String>()

    val imageUrl = ObservableField<String>()    // 이미지 URL
    val fdPlace = ObservableField<String>()     // 습득 장소
    val tel = ObservableField<String>()         // 전화번호
    val uniq = ObservableField<String>()        // 특이사항
    val depPlace = ObservableField<String>()    // 보관장소

    private val _goTel = MutableLiveData<String>()
    val goTel: LiveData<String> get() = _goTel

    private val _findPlace = MutableLiveData<String>()
    val findPlace: LiveData<String> get() = _findPlace

    private val _finishAlert = MutableLiveData<String>()
    val finishAlert: LiveData<String> get() = _finishAlert


    fun 습득물상세조회(id: String, seq: Int) {
        scope.launch {
            val detailResult = detailRepository.습득물상세조회(id, seq).response.body.item
            습득물결과(detailResult)
        }.progress(_isProgress)
    }


    private fun 습득물결과(data: DetailItem) {
        imageUrl.set(data.fdFilePathImg)
        fdPlace.set("습득장소 : ${data.fdPlace}")
        tel.set(data.tel)
        uniq.set(data.uniq)
        depPlace.set(data.depPlace)


        val state = data.csteSteNm
        if (state == "종결") {
            _finishAlert.postValue("이미 종결 된 물건입니다.\n다른 물건을 찾아보세요")
        }
    }

    fun clickTel() {
        val telNum = tel.get()
        _goTel.postValue(telNum)
    }

    fun clickDepPlace(){
        val place = depPlace.get()
        _findPlace.postValue(place)

    }
}