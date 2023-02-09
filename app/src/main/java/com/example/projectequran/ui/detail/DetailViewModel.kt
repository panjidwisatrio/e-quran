package com.example.projectequran.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.projectequran.data.Repository

class DetailViewModel(application: Application): AndroidViewModel(application) {
    private val repository = Repository(application)

    fun getAyat(id: Int) = repository.getAyat(id)
}