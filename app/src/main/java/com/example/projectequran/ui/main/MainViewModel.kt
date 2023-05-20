package com.example.projectequran.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.projectequran.data.Repository

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val repository = Repository()
    fun getSurat() = repository.getSurat()
}