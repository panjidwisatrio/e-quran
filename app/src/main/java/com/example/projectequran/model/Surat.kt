package com.example.projectequran.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "surat")
data class Surat(
    @field:Json(name = "nomor")
    @ColumnInfo(name = "nomor")
    @PrimaryKey
    val nomor: Int? = 0,

    @field:Json(name = "nama")
    @ColumnInfo(name = "nama")
    val nama: String? = "",

    @field:Json(name = "namaLatin")
    @ColumnInfo(name = "namaLatin")
    val namaLatin: String? = "",

    @field:Json(name = "jumlahAyat")
    @ColumnInfo(name = "jumlahAyat")
    val jumlahAyat: Int? = 0,

    @field:Json(name = "tempatTurun")
    @ColumnInfo(name = "tempatTurun")
    val tempatTurun: String? = "",

    @field:Json(name = "arti")
    @ColumnInfo(name = "arti")
    val arti: String? = "",

    @field:Json(name = "deskripsi")
    @ColumnInfo(name = "deskripsi")
    val deskripsi: String? = "",

    @field:Json(name = "audioFull")
    @ColumnInfo(name = "audioFull")
    val audio: Map<String, String> = mapOf(),
)
