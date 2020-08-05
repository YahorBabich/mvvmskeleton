package com.wsc.mvvmskeleton.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.wsc.mvvmskeleton.databinding.ActivitySearchBinding
import com.wsc.mvvmskeleton.extension.observe
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {
    private val viewModel: SearchViewModel by viewModel()
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.apply {
            observe(data, ::display)
        }

        binding.getWithCoroutines.setOnClickListener {
            viewModel.performWithCoroutines()
        }

        binding.getWithRx.setOnClickListener {
            viewModel.performWithRx()
        }
    }

    private fun display(value: Int?) {
        Toast.makeText(this, "$value posts were added/replaced", Toast.LENGTH_SHORT).show()
    }
}