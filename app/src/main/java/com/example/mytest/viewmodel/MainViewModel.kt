package com.example.mytest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytest.appState.AppStateHistorySchedule
import com.example.mytest.repository.RepositoryImplHistorySchedule

class MainViewModel(private val liveData: MutableLiveData<AppStateHistorySchedule> = MutableLiveData()) :
    ViewModel() {

    private val repositoryImplHistorySchedule: RepositoryImplHistorySchedule by lazy {
        RepositoryImplHistorySchedule()
    }

    fun getLiveData() = liveData

    fun getHistory() {
        Thread {
            liveData.postValue(AppStateHistorySchedule.Success(repositoryImplHistorySchedule.getAllHistorySchedule()))
        }.start()
    }

}