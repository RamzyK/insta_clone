package com.example.instaclone.repositories

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.instaclone.data.model.GlobalDataModel
import com.example.instaclone.db.AppDatabase
import com.example.instaclone.db.entities.PostEntity
import com.example.instaclone.network.RetrofitClient
import com.example.instaclone.network.dto.GlobalModelDto
import com.example.instaclone.network.mapper.mapGlobalDataDtoToGlobalDataModel
import com.example.instaclone.network.services.GlobalDataService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GlobalDataRepository(context: Context) {

    private val globalDataService = RetrofitClient.instance.create(GlobalDataService::class.java)

    private val _globalData = MutableLiveData<GlobalDataModel>()

    val globalData: LiveData<GlobalDataModel> get() = _globalData

    private val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "my_app_db"
    ).build()

    val scope = CoroutineScope(SupervisorJob())

    fun getAllData() {
        val call = globalDataService.getGetAllData()

        call.enqueue(object : Callback<GlobalModelDto>{
            override fun onResponse(
                call: Call<GlobalModelDto>,
                response: Response<GlobalModelDto>
            ) {
                 val responseBody = response.body()
                _globalData.value = responseBody?.let {
                    val mappedData = mapGlobalDataDtoToGlobalDataModel(it)
                    scope.launch {
                        insertDataFromApi(mappedData)
                    }
                    mappedData
                }

            }

            override fun onFailure(call: Call<GlobalModelDto>, t: Throwable) {
                Log.d("Error network", t.message ?: "Error HTTP")
            }

        })
    }

    suspend fun insertDataFromApi(data: GlobalDataModel) {
        val postsDao = db.postDao()

        val postDataEntityMapped = data.posts.map {
            PostEntity(
                caption = it.caption,
                likes = it.likes,
                profilePicture = it.profile_picture,
                username = it.username
            )
        }

        postsDao.addPosts(postDataEntityMapped)
    }

}