package com.example.mytest.utils

import com.example.mytest.model.Schedule
import com.example.mytest.room.HistorySchedulesEntity

fun convertorHistoryToSchedule(entityList: List<HistorySchedulesEntity>): List<Schedule> {
    return entityList.map {
        Schedule(it.fromCity, it.toCity, it.fromTime, it.toTime)
    }
}

fun convertorScheduleToHistoryScheduleEntity(schedule: Schedule): HistorySchedulesEntity {
    return HistorySchedulesEntity(
        0,
        schedule.fromCity,
        schedule.toCity,
        schedule.fromTime,
        schedule.toTime
    )
}