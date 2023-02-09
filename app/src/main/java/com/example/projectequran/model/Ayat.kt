package com.example.projectequran.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(
    tableName = "ayat",
    foreignKeys = [ForeignKey(
        entity = Surat::class,
        parentColumns = arrayOf("nomor"),
        childColumns = arrayOf("nomorSurat"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Ayat(
    @field:Json(name = "nomorAyat")
    @ColumnInfo(name = "nomorAyat")
    @PrimaryKey
    val nomorAyat: String? = "",

    @field:Json(name = "nomorSurat")
    @ColumnInfo(name = "nomorSurat")
    val nomorSurat: String? = "",

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
    val audio: String? = "",
)
