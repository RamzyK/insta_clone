package com.example.instaclone.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.instaclone.data.model.GlobalDataModel
import com.example.instaclone.network.RetrofitClient
import com.example.instaclone.network.dto.GlobalModelDto
import com.example.instaclone.network.mapper.mapGlobalDataDtoToGlobalDataModel
import com.example.instaclone.network.services.GlobalDataService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GlobalDataRepository {

    // Service pour récupérer le JSON
    private val globalDataService = RetrofitClient.instance.create(GlobalDataService::class.java)


    // Observables pour notifier le repository
    private val _globalData = MutableLiveData<GlobalDataModel>()
    val globalData: LiveData<GlobalDataModel> get() = _globalData


    fun getData() {
        // Call

        val call = globalDataService.getAllData()

        // Callback --> Extraire le body --> Extraire le DTO --> Mapper le dto --> Notifier le VM

        call.enqueue(object: Callback<GlobalModelDto>{
            override fun onResponse(
                call: Call<GlobalModelDto>,
                response: Response<GlobalModelDto>
            ) {

                val body = response.body()
                _globalData.value = body?.let {
                    mapGlobalDataDtoToGlobalDataModel(it)
                }

            }

            override fun onFailure(call: Call<GlobalModelDto>, t: Throwable) {
                    Log.d("Error from fake server", "Error: ${t.message}")
            }
        })
    }



}