package com.bita.lost.ui.main

import com.and.base.log.Log
import com.and.base.ui.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: MainRepository) : BaseViewModel() {
    fun 분실물조회(
            startIndex: Int,
            endIndex: Int,
            cate: String,
            wbCode: String,
            getName: String
    ) {
        repository.분실물조회(startIndex, endIndex, cate, wbCode, getName).enqueue(
                object : Callback<String> {
                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Log.w("Fail : ${t.message}")
                    }

                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        Log.w(response.body().toString())
                    }
                }
        )
    }
}