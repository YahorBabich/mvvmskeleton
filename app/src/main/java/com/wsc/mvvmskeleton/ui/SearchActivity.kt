package com.wsc.mvvmskeleton.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wsc.mvvmskeleton.R
import com.wsc.mvvmskeleton.databinding.ActivitySearchBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {
    private val viewModel: SearchViewModel by viewModel()
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_search)

        binding.button.setOnClickListener {

        }
    }
}