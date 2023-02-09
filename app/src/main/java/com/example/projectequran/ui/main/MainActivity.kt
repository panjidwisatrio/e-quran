package com.example.projectequran.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.projectequran.databinding.ActivityMainBinding
import com.example.projectequran.model.Surat
import com.example.projectequran.ui.adapter.SuratAdapter
import com.example.projectequran.util.ViewStateCallback

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
    }

    private fun showRecyclerView() {
        suratAdapter = SuratAdapter()
        binding.rvSurat.apply {
            adapter = suratAdapter
            setHasFixedSize(true)
        }
    }

    override fun onSuccess(data: List<Surat>) {
        TODO("Not yet implemented")
    }

    override fun onLoading() {
        TODO("Not yet implemented")
    }

    override fun onFailed(message: String?) {
        TODO("Not yet implemented")
    }
}