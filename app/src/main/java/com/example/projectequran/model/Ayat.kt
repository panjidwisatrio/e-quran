package com.example.projectequran.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "ayat")
data class Ayat(
    @field:Json(name = "nomorAyat")
    @ColumnInfo(name = "nomorAyat")
    @PrimaryKey
    val nomorAyat: Int? = 0,

    @field:Json(name = "teksArab")
    @ColumnInfo(name = "teksArab")
    val teksArab: String? = "",

    @field:Json(name = "teksLatin")
    @ColumnInfo(name = "teksLatin")
    val teksLatin: String? = "",

    @field:Json(name = "teksIndonesia")
    @ColumnInfo(name = "teksIndonesia")
    val teksIndonesia: String? = "",

    @field:Json(name = "audio")
    @ColumnInfo(name = "audio")
    val audio: Map<String, String> = mapOf(),
)
