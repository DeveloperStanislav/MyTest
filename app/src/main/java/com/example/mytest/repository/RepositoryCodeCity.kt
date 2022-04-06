package com.example.mytest.repository

import com.example.mytest.model.CodeStationAPI

interface RepositoryCodeStation {
    fun getCodeStationAPI(): List<CodeStationAPI>
}