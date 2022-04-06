package com.example.mytest.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_schedules_entity")
data class HistorySchedulesEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val fromCity: String,
    val toCity: String,
    val fromTime: String,
    val toTime: String
)
