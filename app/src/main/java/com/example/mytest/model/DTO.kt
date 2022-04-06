package com.example.mytest.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DTO(
    val segments: List<Segment>
) : Parcelable

@Parcelize
data class Segment(
    val arrival: String,
    val from: From,
    val thread: Thread,
    val to: To,

    @SerializedName("departure_platform")
    val departurePlatform: String,

    val departure: String,
    val stops: String,

    @SerializedName("departure_terminal")
    val departureTerminal: String? = null,

    @SerializedName("has_transfers")
    val hasTransfers: Boolean,

    @SerializedName("tickets_info")
    val ticketsInfo: TicketsInfo,

    val duration: Double,

    @SerializedName("arrival_terminal")
    val arrivalTerminal: String? = null,

    @SerializedName("start_date")
    val startDate: String,

    @SerializedName("arrival_platform")
    val arrivalPlatform: String
) : Parcelable

@Parcelize
data class From(
    val code: String,
    val title: String,

    @SerializedName("station_type")
    val stationType: String,

    @SerializedName("popular_title")
    val popularTitle: String,

    @SerializedName("short_title")
    val shortTitle: String,

    @SerializedName("transport_type")
    val transportType: String,

    @SerializedName("station_type_name")
    val stationTypeName: String,

    val type: String
) : Parcelable

@Parcelize
data class To(
    val code: String,
    val title: String,

    @SerializedName("station_type")
    val stationType: String,

    @SerializedName("popular_title")
    val popularTitle: String,

    @SerializedName("short_title")
    val shortTitle: String,

    @SerializedName("transport_type")
    val transportType: String,

    @SerializedName("station_type_name")
    val stationTypeName: String,

    val type: String
) : Parcelable

@Parcelize
data class Thread(
    val uid: String,
    val title: String,
    val number: String,

    @SerializedName("short_title")
    val shortTitle: String,

    @SerializedName("thread_method_link")
    val threadMethodLink: String,

    @SerializedName("transport_type")
    val transportType: String,

    val vehicle: String? = null,

    @SerializedName("transport_subtype")
    val transportSubtype: TransportSubtype,

    @SerializedName("express_type")
    val expressType: String? = null
) : Parcelable

@Parcelize
data class TransportSubtype(
    val color: String,
    val code: String,
    val title: String
) : Parcelable

@Parcelize
data class TicketsInfo(
    @SerializedName("et_marker")
    val etMarker: Boolean,
    val places: List<Place>
) : Parcelable

@Parcelize
data class Place(
    val currency: String,
    val price: Price,
    val name: String? = null
) : Parcelable

@Parcelize
data class Price(
    val cents: Long,
    val whole: Long
) : Parcelable
