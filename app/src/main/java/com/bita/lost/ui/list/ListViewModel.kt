@file:Suppress("NonAsciiCharacters", "FunctionName")

package com.bita.lost.ui.list

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import com.and.base.log.Log
import com.bita.lost.base.LViewModel
import com.bita.lost.common.progress
import com.bita.lost.repo.ListRepository
import com.bita.lost.repo.data.LostItem
import com.bita.lost.repo.data.LostListFrame
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ListViewModel(private val repository: ListRepository) : LViewModel() {
    private var index = 0
    private val viewsPerPage = 20
    private lateinit var cate: String
    private lateinit var wbCode: String
    private lateinit var name: String

    val list = ObservableArrayList<LostItem>()
    val hasNext = ObservableBoolean(true)

    fun init(cate: String, wbCode: String, name: String) {
        this.cate = cate
        this.wbCode = wbCode
        this.name = name
    }

    fun getLostList() {
        scope.launch {
            // todo 성공 실패 여부 try catch 로 처리하는게 맞나..?
            try {
                val start = index + 1
                val end = start + viewsPerPage - 1
                Log.w("$start ~ $end 조회")
                val result: LostListFrame = repository.분실물조회(start, end, cate, wbCode, name)
                delay(2000)
                index = end
                // todo 깜빡거리는지 확인 필요
//                list.addAll(result.service.items)
                list.clear()
                list.addAll(result.service.items)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.progress(_isProgress)
    }
}