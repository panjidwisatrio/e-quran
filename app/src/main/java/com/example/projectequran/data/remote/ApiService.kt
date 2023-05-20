package com.example.projectequran.data.remote

import com.example.projectequran.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("surat")
    fun getSurat(): Call<SuratList>

    @GET("surat/{nomor}")
    fun getSuratDetail(
        @Path("nomor")
        nomor: Int
    ): Call<SuratDetail>

    @GET("surat/{nomor}")
    fun getAyat(
        @Path("nomor")
        nomor: Int
    ): Call<AyatList>

    @GET("tafsir/{nomor}")
    fun getTafsir(
        @Path("nomor")
        nomor: Int
    ): Call<TafsirList>
}