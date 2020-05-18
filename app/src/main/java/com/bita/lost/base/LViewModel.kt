package com.bita.lost.base

import com.and.base.ui.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

abstract class LViewModel : BaseViewModel(), CoroutineScope {
    private val job = SupervisorJob()
    final override val coroutineContext: CoroutineContext = Dispatchers.Main + job
    val scope = CoroutineScope(coroutineContext)

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}