package com.bita.lost.common

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Job

/**
 * 네트워크 로딩 시 progress 보여주는 함수
 */
fun Job.progress(_isProgress: MutableLiveData<Boolean>) : Job {
    _isProgress.postValue(true)
    this.invokeOnCompletion { _isProgress.postValue(false) }
    return this
}

fun String.isPhoneNum(): Boolean {
    val regularExpressionPhoneNo = "^(0(?:505|70|10|11|16|17|18|19))(\\d{3}|\\d{4})(\\d{4})$"
    return Regex(pattern = regularExpressionPhoneNo).matchEntire(this) != null
}