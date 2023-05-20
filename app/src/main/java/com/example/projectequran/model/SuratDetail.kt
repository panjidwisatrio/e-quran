package com.example.projectequran.model

import com.squareup.moshi.Json

data class SuratDetail(
    @field:Json(name = "data")
    val data: Surat
)
