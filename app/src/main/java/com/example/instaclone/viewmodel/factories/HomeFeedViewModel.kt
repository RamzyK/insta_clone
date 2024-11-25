package com.example.instaclone.viewmodel.factories

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.instaclone.data.model.GlobalDataModel
import com.example.instaclone.repositories.GlobalDataRepository

class HomeFeedViewModel(
    val globalDataRepository: GlobalDataRepository,
    val context: LifecycleOwner
): ViewModel() {

    // Observables pour notifier la view
    private val _globalData = MutableLiveData<GlobalDataModel>()
    val globalData: LiveData<GlobalDataModel> get() = _globalData

    fun fetchAllData() {
        this.globalDataRepository.globalData.observe(context) { data ->
            this@HomeFeedViewModel._globalData.value = data
        }

        this.globalDataRepository.getData()
    }
}