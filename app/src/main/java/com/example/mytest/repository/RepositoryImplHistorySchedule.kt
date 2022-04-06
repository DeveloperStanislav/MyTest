package com.example.mytest.repository

import com.example.mytest.app.App
import com.example.mytest.model.Schedule
import com.example.mytest.utils.convertorHistoryToSchedule
import com.example.mytest.utils.convertorScheduleToHistoryScheduleEntity

class RepositoryImplHistorySchedule : RepositoryHistorySchedules {


    override fun getAllHistorySchedule(): List<Schedule> {
        return convertorHistoryToSchedule(App.getHistorySchedulesDAO().getAllHistorySchedule())
    }

    override fun saveSchedule(schedule: Schedule) {
        App.getHistorySchedulesDAO().insert(convertorScheduleToHistoryScheduleEntity(schedule))
    }

    override fun deleteAllHistory() {
        App.getHistorySchedulesDAO().allDelete()
    }
}