package com.example.mytest.appState

import com.example.mytest.model.Schedule

sealed class AppStateHistorySchedule {
    data class Success(val schedule: List<Schedule>) : AppStateHistorySchedule()
}
