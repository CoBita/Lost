package com.bita.lost.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.and.base.ui.BaseViewModel
import com.bita.lost.R
import com.bita.lost.base.LActivity

class MainActivity : AppCompatActivity() {
//    override lateinit var vm: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
