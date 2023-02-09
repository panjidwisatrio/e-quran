package com.example.projectequran.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectequran.data.Resource
import com.example.projectequran.databinding.ActivityMainBinding
import com.example.projectequran.model.Surat
import com.example.projectequran.ui.adapter.SuratAdapter
import com.example.projectequran.util.ViewStateCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), ViewStateCallback<List<Surat>> {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var suratAdapter: SuratAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarLayout.toolbar)
        supportActionBar?.title = null

        suratAdapter = SuratAdapter()

        showRecyclerView()
        getSurat()
    }

    private fun showRecyclerView() {
        binding.rvSurat.apply {
            visibility = visible
            adapter = suratAdapter
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun getSurat() {
//        CoroutineScope(Dispatchers.IO).launch(Dispatchers.Main) {
//
//        }
        viewModel.getSurat().observe(this@MainActivity) {
            when (it) {
                is Resource.Error -> onFailed(it.message)
                is Resource.Loading -> onLoading()
                is Resource.Success -> it.data?.let { it1 -> onSuccess(it1) }
            }
        }
    }

    override fun onSuccess(data: List<Surat>) {
        suratAdapter.setAllData(data)
        with(binding) {
            rvSurat.visibility = visible
            progressBar.visibility = invisible
            ivError.visibility = invisible
            tvError.visibility = invisible
        }
    }

    override fun onLoading() {
        with(binding) {
            rvSurat.visibility = invisible
            progressBar.visibility = visible
            ivError.visibility = invisible
            tvError.visibility = invisible
        }
    }

    override fun onFailed(message: String?) {
        if (message == null) {
            with(binding) {
                rvSurat.visibility = invisible
                progressBar.visibility = invisible
                ivError.visibility = visible
                tvError.visibility = visible
            }
        } else {
            with(binding) {
                rvSurat.visibility = invisible
                progressBar.visibility = invisible
                ivError.visibility = visible
                tvError.visibility = visible
                tvError.text = message
            }
        }
    }
}