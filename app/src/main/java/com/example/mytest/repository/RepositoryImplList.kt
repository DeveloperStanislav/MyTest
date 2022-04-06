package com.example.mytest.repository

import com.example.mytest.BuildConfig
import com.example.mytest.model.DTO
import com.example.mytest.retrofit.ScheduleApi
import retrofit2.Callback

class RepositoryImplList : RepositoryList {

    private val scheduleApi by lazy { ScheduleApi.startRetrofit() }


    override fun getScheduleFromApi(
        fromCity: String,
        toCity: String,
        dateTime: String,
        transport: String,
        callback: Callback<DTO>
    ) {
        scheduleApi.getSchedule(BuildConfig.API_KEY, fromCity, toCity, dateTime, transport)
            .enqueue(callback)
    }
}