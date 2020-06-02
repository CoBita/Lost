@file:Suppress("NonAsciiCharacters", "FunctionName")

package com.bita.lost.ui.list

import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.and.base.log.Log
import com.bita.lost.base.LViewModel
import com.bita.lost.common.progress
import com.bita.lost.common.format
import com.bita.lost.repo.ListRepository
import com.bita.lost.repo.data.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.lang.StringBuilder
import java.text.DecimalFormat

class ListViewModel(private val repository: ListRepository) : LViewModel() {
    private var page = 0
    lateinit var area: AreaCode
    lateinit var product: ProductCode
    lateinit var displayPeriod: String
    lateinit var startYmd: String
    lateinit var endYmd: String

    val list = ObservableArrayList<LostItem>()
    val hasNext = ObservableBoolean(true)
    val isLoadFinish = ObservableBoolean(false)
    val resultCount = ObservableField<String>("")

    private val _backPressed = MutableLiveData<Boolean>()
    val backPressed: LiveData<Boolean> get() = _backPressed

    private val handler = CoroutineExceptionHandler { _, e ->
        Log.w("exception occured")
        e.printStackTrace()
    }

    fun init(nFdLctCd: AreaCode, prdtClCd01: ProductCode, startYmd: String, endYmd: String) {
        this.product = prdtClCd01
        this.displayPeriod = "$startYmd - $endYmd"
        this.area = nFdLctCd
        this.startYmd = parseDate(startYmd)
        this.endYmd = parseDate(endYmd)
    }

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

    fun getFirstLostList() {
        if (list.isEmpty()) getLostList()
    }

    fun getLostList() {
        scope.launch(handler) {
            page++
            val result: Body<LostList> = repository.습득물조회(product.code, startYmd, endYmd, area.code, page)
            isLoadFinish.set(true)
            result.items.items?.let { list.addAll(it) }
            if (page == 1) resultCount.set("검색결과 총 ${result.totalCount.format()}개")
            if (result.totalCount <= page * 20) hasNext.set(false)
        }.progress(_isProgress)
    }

    fun onBackPressed(v: View) {
        _backPressed.postValue(true)
    }
}