package com.example.mytest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytest.appState.AppStateStationCode
import com.example.mytest.model.CodeStationAPI
import com.example.mytest.repository.RepositoryImplCodeStation

class SearchViewModel(private val liveData: MutableLiveData<AppStateStationCode> = MutableLiveData()) :
    ViewModel() {

    private val repositoryImplCodeStation: RepositoryImplCodeStation by lazy {
        RepositoryImplCodeStation()
    }

    fun getLiveData() = liveData

    fun getListStation() {
        Thread {
            liveData.postValue(AppStateStationCode.Success(repositoryImplCodeStation.getCodeStationAPI()))
        }.start()
    }

    fun getCodeStationCity(flag: Boolean, codeStationAPI: CodeStationAPI) {
        Thread {
            if (flag) {
                liveData.postValue(AppStateStationCode.StationOne(codeStationAPI))
            } else {
                liveData.postValue(AppStateStationCode.StationTwo(codeStationAPI))
            }
        }.start()
    }


}