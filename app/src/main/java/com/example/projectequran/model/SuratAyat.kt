package com.example.projectequran.model

import com.squareup.moshi.Json

data class SuratAyat(
    @field:Json(name = "ayat")
    val ayat: List<Ayat>
)
