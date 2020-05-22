@file:Suppress("FunctionName", "NonAsciiCharacters")

package com.bita.lost.ui.detail

import com.and.base.log.Log
import com.bita.lost.base.LViewModel
import com.bita.lost.repo.DetailRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val detailRepository: DetailRepository) : LViewModel() {
    fun 분실물상세조회(id: String) {
        scope.launch {
            val detail = detailRepository.분실물상세조회(id)
            Log.i(detail)
        }
    }
}