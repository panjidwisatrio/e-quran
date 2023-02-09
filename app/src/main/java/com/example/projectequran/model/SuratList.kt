package com.example.projectequran.model

import com.squareup.moshi.Json

data class SuratList(
    @field:Json(name = "data")
    val data: List<Surat>
)
