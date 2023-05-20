package com.example.projectequran.util

import android.media.MediaPlayer
import androidx.annotation.StringRes
import com.example.projectequran.R

object Constanta {
    const val TIME_SPLASH = 2000L

    const val SPLASH_SCREEN = "splash_screen"

    const val EXTRA_NOMOR_SURAT = "EXTRA_NOMOR"
    const val EXTRA_NAMA_LATIN = "EXTRA_NAMA_LATIN"
    const val EXTRA_JUMLAH_AYAT = "EXTRA_JUMLAH_AYAT"
    const val EXTRA_TEMPAT_TURUN = "EXTRA_TEMPAT_TURUN"
    const val EXTRA_ARTI = "EXTRA_ARTI"
    const val EXTRA_DESKRIPSI = "EXTRA_DESKRIPSI"
    const val EXTRA_TAFSIR = "EXTRA_TAFSIR"
    const val EXTRA_NOMOR_AYAT = "EXTRA_NOMOR_AYAT"

    // TODO: 10/10/2021 - Play Audio
    var currentAudio: String? = ""
    val mediaPlayer = MediaPlayer()

    @StringRes
    val TAB_TITLES = intArrayOf(
        R.string.tab1,
        R.string.tab2
    )

    const val BASE_URL = "https://equran.id/api/v2/"
}