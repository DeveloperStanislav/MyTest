package com.example.mytest.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CodeStationAPI(val nameStation: String, val codes: String) : Parcelable

fun getListCodeCityAPI(): List<CodeStationAPI> {
    return listOf(
        CodeStationAPI("Орехово-Зуево", "c10745"),
        CodeStationAPI("Павловский-Посад", "c10746"),
        CodeStationAPI("Москва(Курский вокзал)", "s2000001")
    )
}
