package com.example.mytest.repository

import com.example.mytest.model.DTO
import retrofit2.Callback

interface RepositoryList {
    fun getScheduleFromApi(
        fromCity: String, toCity: String, dateTime: String, transport: String, callback:
        Callback<DTO>
    )
}