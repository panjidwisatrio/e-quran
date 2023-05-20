package com.example.projectequran.model

import com.squareup.moshi.Json

data class AyatTafsir(
    @field:Json(name = "tafsir")
    val tafsir: List<Tafsir>
)
