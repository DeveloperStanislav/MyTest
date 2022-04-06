package com.example.mytest.repository

import com.example.mytest.model.CodeStationAPI
import com.example.mytest.model.getListCodeCityAPI

class RepositoryImplCodeStation : RepositoryCodeStation {
    override fun getCodeStationAPI(): List<CodeStationAPI> = getListCodeCityAPI()

}