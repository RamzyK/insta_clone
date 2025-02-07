package com.example.instaclone.repositories

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.instaclone.data.model.GlobalDataModel
import com.example.instaclone.db.AppDatabase
import com.example.instaclone.db.entities.PostsEntities
import com.example.instaclone.network.dto.GlobalModelDto
import com.example.instaclone.network.mapper.mapGlobalDataDtoToGlobalDataModel
import com.example.instaclone.network.services.GlobalDataService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GlobalDataRepository(context: Context,
                           private val globalDataService: GlobalDataService) {

    // Service pour récupérer le JSON


    // private val globalDataService = RetrofitClient.instance.create(GlobalDataService::class.java)


    // Observables pour notifier le repository
    private val _globalData = MutableLiveData<GlobalDataModel>()
    val globalData: LiveData<GlobalDataModel> get() = _globalData

    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "database-name"
    ).build()

    val scope = CoroutineScope(SupervisorJob())

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
                   // Map la donnée DTO -> Model applicatif
                    val mappedData = mapGlobalDataDtoToGlobalDataModel(it)
                    // Je stock les données dans ma db locale
                    scope.launch {
                        savePostsInLocalDb(mappedData)
                    }

                    // Renvoie le model applicatif
                    mappedData
                }

            }

            override fun onFailure(call: Call<GlobalModelDto>, t: Throwable) {
                    Log.d("Error from fake server", "Error: ${t.message}")
            }
        })
    }

    suspend fun savePostsInLocalDb(data: GlobalDataModel) {
        // Récupération DAO
        val postsDao = this.db.postDao()

        // Transformation du model en liste d'entités

        val postsEntities = data.posts.map {
            PostsEntities(
                caption = it.caption,
                likes = it.likes,
                profilePicture = it.profile_picture,
                username = it.username
            )
        }

        // Sauvegarde grâce au DAO
        postsDao.addPosts(postsEntities)

    }



}