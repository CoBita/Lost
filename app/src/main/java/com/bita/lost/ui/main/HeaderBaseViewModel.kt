package com.bita.lost.ui.main

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.and.base.common.Event
import com.bita.lost.base.LViewModel

abstract class HeaderBaseViewModel : LViewModel() {

    abstract val title: ObservableField<String>

    private val _dismiss = MutableLiveData<Event<Unit>>()
    val dismiss: LiveData<Event<Unit>> = _dismiss

    fun dismiss() {
        _dismiss.value = Event(Unit)
    }
}
