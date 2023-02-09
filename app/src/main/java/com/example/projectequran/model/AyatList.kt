package com.example.projectequran.model

import com.squareup.moshi.Json

data class AyatList(
    @field:Json(name = "data.ayat")
    val data: List<Ayat>
)
