package com.example.projectequran.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.projectequran.data.remote.ApiService
import com.example.projectequran.data.remote.RetrofitService
import com.example.projectequran.model.Surat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(application: Application) {
    private val retrofit: ApiService = RetrofitService.create()

    fun getSurat() : LiveData<Resource<List<Surat>>> {
        val surat = MutableLiveData<Resource<List<Surat>>>()

        surat.postValue(Resource.Loading())
        retrofit.getSurat().enqueue(object : Callback<List<Surat>> {
            override fun onResponse(
                call: Call<List<Surat>>,
                response: Response<List<Surat>>
            ) {
                val list = response.body()

                if (list.isNullOrEmpty())
                    surat.postValue(Resource.Error(null))
                else
                    surat.postValue(Resource.Success(list))
            }

            override fun onFailure(call: Call<List<Surat>>, t: Throwable) {
                surat.postValue(Resource.Error(t.message))
            }

        })

        return surat
    }
}