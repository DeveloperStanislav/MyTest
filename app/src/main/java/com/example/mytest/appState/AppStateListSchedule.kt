package com.example.mytest.appState

import com.example.mytest.model.Schedule

sealed class AppStateListSchedule {
    data class Success(val scheduleData: List<Schedule>) : AppStateListSchedule()
    data class Error(val error: Throwable) : AppStateListSchedule()
    data class ErrorCode(val messError: String) : AppStateListSchedule()
}
