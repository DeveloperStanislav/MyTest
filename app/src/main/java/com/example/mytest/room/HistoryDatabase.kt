package com.example.mytest.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HistorySchedulesEntity::class], version = 1, exportSchema = false)
abstract class HistoryDatabase : RoomDatabase() {

    abstract fun historyScheduleDAO(): HistoryScheduleDAO
}