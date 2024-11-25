package com.example.instaclone.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.instaclone.data.model.GlobalDataModel
import com.example.instaclone.repositories.GlobalDataRepository

class HomeFeedViewModel(
    private val globalDataRepo: GlobalDataRepository,
    val context: LifecycleOwner
): ViewModel() {

    private val _globalData = MutableLiveData<GlobalDataModel>()

    val globalData: LiveData<GlobalDataModel> get() = _globalData

    fun fetchGlobalData() {
        this.globalDataRepo.globalData.observe(this.context) { data ->
            this@HomeFeedViewModel._globalData.value = data
        }

        this.globalDataRepo.getAllData()
    }
}