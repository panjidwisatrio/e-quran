package com.example.projectequran.model

import androidx.room.ColumnInfo
import com.squareup.moshi.Json

data class TafsirList(
    @field:Json(name = "data")
    @ColumnInfo(name = "data")
    val data: AyatTafsir
)
