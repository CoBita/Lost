package com.bita.lost.ui.list

import androidx.databinding.ObservableField
import com.bita.lost.ui.main.HeaderBaseViewModel

class ListHeaderViewModel : HeaderBaseViewModel() {
    override val title: ObservableField<String> = ObservableField("검색 조건")
}