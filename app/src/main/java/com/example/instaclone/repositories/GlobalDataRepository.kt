package com.example.instaclone.repositories

import android.util.Log
import android.widget.Toast
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

class GlobalDataRepository() {

    private val globalDataService = RetrofitClient.instance.create(GlobalDataService::class.java)

    private val _globalData = MutableLiveData<GlobalDataModel>()

    val globalData: LiveData<GlobalDataModel> get() = _globalData

    fun getAllData() {
        val call = globalDataService.getGetAllData()

        call.enqueue(object : Callback<GlobalModelDto>{
            override fun onResponse(
                call: Call<GlobalModelDto>,
                response: Response<GlobalModelDto>
            ) {
                 val response = response.body()
                _globalData.value = response?.let {
                    mapGlobalDataDtoToGlobalDataModel(it)
                }

            }

            override fun onFailure(call: Call<GlobalModelDto>, t: Throwable) {
                Log.d("Error network", t.message ?: "Error HTTP")
            }

        })
    }

}