@file:Suppress("NonAsciiCharacters", "FunctionName")

package com.bita.lost.ui.list

import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.and.base.common.Event
import com.and.base.log.Log
import com.bita.lost.base.BaseAdapter
import com.bita.lost.base.LViewModel
import com.bita.lost.common.progress
import com.bita.lost.common.format
import com.bita.lost.repo.ListRepository
import com.bita.lost.repo.data.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.StringBuilder
import java.text.DecimalFormat

class ListViewModel(private val repository: ListRepository) : LViewModel() {
    private var page = 0
    private var hasNext: Boolean = true
    private var isSearching = false

    lateinit var area: ObservableField<AreaCode>
    lateinit var product: ObservableField<ProductCode>
    lateinit var displayPeriod: ObservableField<String>
    lateinit var start: String
    lateinit var end: String

    val list = ObservableArrayList<Any>()
    val isLoadFinish = ObservableBoolean(false)
    val resultCount = ObservableField("")

    private val _backPressed = MutableLiveData<Event<Unit>>()
    val backPressed: LiveData<Event<Unit>> get() = _backPressed

    private val handler = CoroutineExceptionHandler { _, e ->
        Log.w("exception occured")
        e.printStackTrace()
    }

    fun init(pArea: AreaCode, pProduct: ProductCode, pStart: String, pEnd: String) {
        if(!::product.isInitialized)  this.product = ObservableField(pProduct)
        if(!::start.isInitialized) this.start = parseDate(pStart)
        if(!::end.isInitialized) this.end = parseDate(pEnd)
        if(!::displayPeriod.isInitialized) this.displayPeriod = ObservableField("$pStart - $pEnd")
        if(!::area.isInitialized) this.area = ObservableField(pArea)
    }

    // "####.#.#" -> "########" 으로 포맷 변경
    private fun parseDate(date: String): String {
        if (date.isEmpty()) return date
        val parts = date.split(".")
        val result = StringBuilder()
        parts.forEach { part ->
            try {
                result.append(DecimalFormat("00").format(part.toInt()))
            } catch (e: NumberFormatException) {
                e.printStackTrace()
            }
        }
        return result.toString()
    }

    // todo 이후 삭제
    fun 습득물조회fromDummy(raw: String?) {
        raw?.let {
            val dummy: ArrayList<LostItem> = Gson().fromJson(raw, object : TypeToken<ArrayList<LostItem>>() {}.type)
            scope.launch {
                delay(3000)
                isLoadFinish.set(true)
                list.addAll(dummy)
                if (page == 1) resultCount.set("검색결과 총 20개")
                hasNext = true
            }.progress(_isProgress)
        }
    }

    // 습득물 목록 조회
    fun searchLosts() {
        if (!hasNext || isSearching) return
        else isSearching = true
        scope.launch(handler) {
            page++

            val prdtClCd01 = product.get()?.code ?: ProductCode.모든습득물.code
            val nFdLctCd = area.get()?.code ?: AreaCode.전체지역.code

            val result: Body<LostList> = repository.습득물조회(prdtClCd01, start, end, nFdLctCd, page)

            // after success
            result.items.items?.let { list.addAll(it) }
                    ?: run { list.add(BaseAdapter.BaseHolderType.결과없음) }
            if (page == 1) resultCount.set("검색결과 총 ${result.totalCount.format()}개")

            // 더보기 여부
            if (result.totalCount <= page * 20) hasNext = false
        }.progress(_isProgress).invokeOnCompletion {
            isLoadFinish.set(true)
            isSearching = false
        }
    }

    // 조회 조건 초기화
    fun searchInitialLosts(){
        page = 0
        list.clear()
        hasNext = true
        isLoadFinish.set(false)
        searchLosts()
    }

    // 재조회
    fun searchAgain(pArea: AreaCode? = null, pProduct: ProductCode? = null, pStart: String? = null, pEnd: String? = null) {
        pArea?.let { area.set(it) }
        pProduct?.let { product.set(it) }
        pStart?.let {
            start = it
            displayPeriod.set("$start - $end")
        }
        pEnd?.let {
            end = it
            displayPeriod.set("$start - $end")
        }
        searchInitialLosts()
    }

    fun onBackPressed(v: View) {
        _backPressed.value = Event(Unit)
    }

}