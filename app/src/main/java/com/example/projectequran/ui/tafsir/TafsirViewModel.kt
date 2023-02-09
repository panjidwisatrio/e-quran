package com.example.projectequran.ui.tafsir

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.projectequran.data.Repository

class TafsirViewModel(application: Application): AndroidViewModel(application) {
    private val repository = Repository(application)

    fun getTafsir(id: Int) = repository.getTafsir(id)
}