package com.example.mytest.app

import android.app.Application
import androidx.room.Room
import com.example.mytest.room.HistoryDatabase
import com.example.mytest.room.HistoryScheduleDAO
import java.util.*

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        application = this
    }

    companion object {
        private const val DB_NAME = "history.db"
        private var application: App? = null
        private var db: HistoryDatabase? = null

        fun getHistorySchedulesDAO(): HistoryScheduleDAO {
            if (db == null) {
                if (application == null) {
                    throw IllformedLocaleException("Application = null")
                } else {
                    db = Room.databaseBuilder(
                        application!!.applicationContext,
                        HistoryDatabase::class.java, DB_NAME
                    ).allowMainThreadQueries()
                        .build()
                }
            }
            return db!!.historyScheduleDAO()
        }
    }
}