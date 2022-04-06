package com.example.mytest.appState

import com.example.mytest.model.CodeStationAPI

sealed class AppStateStationCode {
    data class Success(val codeStationList: List<CodeStationAPI>) : AppStateStationCode()
    data class StationOne(val codeStationAPI: CodeStationAPI) : AppStateStationCode()
    data class StationTwo(val codeStationAPI: CodeStationAPI) : AppStateStationCode()
}
