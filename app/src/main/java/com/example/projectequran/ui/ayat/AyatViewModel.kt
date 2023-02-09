package com.example.projectequran.ui.ayat

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.projectequran.data.Repository

class AyatViewModel(application: Application): AndroidViewModel(application) {
    private val repository = Repository(application)

    fun getAyat(nomorSurat: Int) = repository.getAyat(nomorSurat)
}