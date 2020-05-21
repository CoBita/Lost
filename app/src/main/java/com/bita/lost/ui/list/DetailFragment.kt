package com.bita.lost.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bita.lost.R
import com.bita.lost.base.LFragment
import com.bita.lost.databinding.DetailFrBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DetailFragment : LFragment() {
    override val vm: ListViewModel by sharedViewModel()
    private lateinit var binding: DetailFrBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_fr, container, false)
        return binding.root
    }

    override fun onParseExtra() {
        super.onParseExtra()
        arguments?.getString(ID)?.let { id ->
            Snackbar.make(binding.root, "id = $id", Snackbar.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val ID = "ID"
    }
}