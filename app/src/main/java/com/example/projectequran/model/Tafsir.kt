package com.example.projectequran.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "tafsir")
data class Tafsir(
    @PrimaryKey(autoGenerate = true)

    @field:Json(name = "ayat")
    @ColumnInfo(name = "ayat")
    val nomorAyat: Int? = 0,

    @field:Json(name = "teks")
    @ColumnInfo(name = "teks")
    val teksTafsir: String? = "",
)
