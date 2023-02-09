package com.example.projectequran.model

import com.squareup.moshi.Json

data class TafsirList(
    @field:Json(name = "data.tafsir")
    val data: List<Tafsir>
)
