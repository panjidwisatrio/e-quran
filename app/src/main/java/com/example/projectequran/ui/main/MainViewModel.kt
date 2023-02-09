package com.example.projectequran.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.projectequran.data.Repository
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.Dispatchers

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val repository = Repository(application)

    fun getSurat() = repository.getSurat()
}