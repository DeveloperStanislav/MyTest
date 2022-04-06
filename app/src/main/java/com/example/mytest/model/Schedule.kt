package com.example.mytest.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Schedule(
    val fromCity: String,
    val toCity: String,
    val fromTime: String,
    val toTime: String
) : Parcelable
