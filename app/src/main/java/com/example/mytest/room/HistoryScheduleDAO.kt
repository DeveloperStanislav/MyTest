package com.example.mytest.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HistoryScheduleDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: HistorySchedulesEntity)

    @Query("DELETE FROM history_schedules_entity")
    fun allDelete()

    @Query("SELECT * FROM history_schedules_entity")
    fun getAllHistorySchedule(): List<HistorySchedulesEntity>
}