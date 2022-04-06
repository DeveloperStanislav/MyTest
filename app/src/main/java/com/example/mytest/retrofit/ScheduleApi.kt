package com.example.mytest.retrofit

import com.example.mytest.model.DTO
import com.example.mytest.utils.AUTHORIZATION
import com.example.mytest.utils.URL_API
import com.example.mytest.utils.URL_API_END_POINT
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ScheduleApi {

    @GET(URL_API_END_POINT)
    fun getSchedule(
        @Header(AUTHORIZATION) apiKey: String,
        @Query("from") fromCity: String,
        @Query("to") toCity: String,
        @Query("date") date: String,
        @Query("transport_types") transportTypes: String
    ): Call<DTO>


    companion object {
        fun startRetrofit(): ScheduleApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(URL_API)
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder().setLenient().create()
                    )
                ).build()
            return retrofit.create(ScheduleApi::class.java)
        }
    }

}
