package com.example.projectequran.data.remote

import com.example.projectequran.model.Ayat
import com.example.projectequran.model.Surat
import com.example.projectequran.model.Tafsir
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/surat")
    fun getSurat(): Call<List<Surat>>

    @GET("/surat/{nomor}")
    fun getAyat(
        @Path("nomor")
        nomor: Int
    ): Call<List<Ayat>>

    @GET("/tafsir/{nomor}")
    fun getTafsir(
        @Path("nomor")
        nomor: Int
    ): Call<List<Tafsir>>
}