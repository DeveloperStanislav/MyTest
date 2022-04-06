package com.example.mytest.repository

import com.example.mytest.model.Schedule

interface RepositoryHistorySchedules {

    fun getAllHistorySchedule(): List<Schedule>

    fun saveSchedule(schedule: Schedule)

    fun deleteAllHistory()
}