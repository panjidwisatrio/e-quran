package com.example.projectequran.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.projectequran.data.remote.ApiService
import com.example.projectequran.data.remote.RetrofitService
import com.example.projectequran.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {
    private val retrofit: ApiService = RetrofitService.create()

    fun getSurat(): LiveData<Resource<List<Surat>>> {
        val surat = MutableLiveData<Resource<List<Surat>>>()

        surat.postValue(Resource.Loading())
        retrofit.getSurat().enqueue(object : Callback<SuratList> {
            override fun onResponse(
                call: Call<SuratList>,
                response: Response<SuratList>
            ) {
                val list = response.body()?.data

                if (list.isNullOrEmpty())
                    surat.postValue(Resource.Error("list is empty"))
                else
                    surat.postValue(Resource.Success(list))
            }

            override fun onFailure(call: Call<SuratList>, t: Throwable) {
                surat.postValue(Resource.Error(t.message))
            }

        })

        return surat
    }

    fun getSuratDetail(nomorSurat: Int): LiveData<Resource<Surat>> {
        val suratDetail = MutableLiveData<Resource<Surat>>()

        suratDetail.postValue(Resource.Loading())
        retrofit.getSuratDetail(nomorSurat).enqueue(object : Callback<SuratDetail> {
            override fun onResponse(
                call: Call<SuratDetail>,
                response: Response<SuratDetail>
            ) {
                val list = response.body()?.data
                suratDetail.postValue(Resource.Success(list))
            }

            override fun onFailure(call: Call<SuratDetail>, t: Throwable) {
                suratDetail.postValue(Resource.Error(t.message))
            }

        })

        return suratDetail
    }

    fun getAyat(nomorSurat: Int): LiveData<Resource<List<Ayat>>> {
        val ayat = MutableLiveData<Resource<List<Ayat>>>()

        ayat.postValue(Resource.Loading())
        retrofit.getAyat(nomorSurat).enqueue(object : Callback<AyatList> {
            override fun onResponse(
                call: Call<AyatList>,
                response: Response<AyatList>
            ) {
                val list = response.body()?.data?.ayat

                if (list.isNullOrEmpty())
                    ayat.postValue(Resource.Error("list is empty"))
                else
                    ayat.postValue(Resource.Success(list))
            }

            override fun onFailure(call: Call<AyatList>, t: Throwable) {
                ayat.postValue(Resource.Error(t.message))
            }

        })

        return ayat
    }

    fun getTafsir(nomorSurat: Int): LiveData<Resource<List<Tafsir>>> {
        val tafsir = MutableLiveData<Resource<List<Tafsir>>>()

        tafsir.postValue(Resource.Loading())
        retrofit.getTafsir(nomorSurat).enqueue(object : Callback<TafsirList> {
            override fun onResponse(
                call: Call<TafsirList>,
                response: Response<TafsirList>
            ) {
                val list = response.body()?.data?.tafsir

                if (list.isNullOrEmpty())
                    tafsir.postValue(Resource.Error("list is empty"))
                else
                    tafsir.postValue(Resource.Success(list))
            }

            override fun onFailure(call: Call<TafsirList>, t: Throwable) {
                tafsir.postValue(Resource.Error(t.message))
            }

        })

        return tafsir
    }
}