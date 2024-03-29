package com.example.projectequran.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.projectequran.data.Repository

class DetailViewModel(application: Application): AndroidViewModel(application) {
    private val repository = Repository()

    fun getSuratDetail(nomorSurat: Int) = repository.getSuratDetail(nomorSurat)
}