package com.example.mytest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytest.appState.AppStateListSchedule
import com.example.mytest.model.DTO
import com.example.mytest.model.Schedule
import com.example.mytest.repository.RepositoryImplHistorySchedule
import com.example.mytest.repository.RepositoryImplList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.format.DateTimeFormatter

class ListViewModel(
    private val liveData: MutableLiveData<AppStateListSchedule> = MutableLiveData()
) :
    ViewModel() {

    private val repositoryImplList: RepositoryImplList by lazy {
        RepositoryImplList()
    }

    private val repositoryImplHistorySchedule: RepositoryImplHistorySchedule by lazy {
        RepositoryImplHistorySchedule()
    }

    fun getLiveData() = liveData

    fun getScheduleFromServerList(
        fromCity: String,
        toCity: String,
        dateTime: String,
        transport: String
    ) {
        repositoryImplList.getScheduleFromApi(fromCity, toCity, dateTime, transport, callback)
    }

    //конвертируем из DTO, в массив объектов для отображения
    private fun converterFromDTOtoListSchedule(DTO: DTO): MutableList<Schedule> {
        val sizeDTO = DTO.segments.size
        val scMutableList: MutableList<Schedule> = mutableListOf()
        for (i in 0 until sizeDTO) {
            scMutableList.add(
                Schedule(
                    DTO.segments[i].from.title,
                    DTO.segments[i].to.title,
                    converterDateAPI(DTO.segments[i].departure),
                    converterDateAPI(DTO.segments[i].arrival)
                )
            )
        }
        return scMutableList
    }

    // Форматируем дату
    private fun converterDateAPI(timeDTO: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssz")
        val parsedDate = formatter.parse(timeDTO)
        val displayFormatter = DateTimeFormatter.ofPattern("HH:mm")
        return displayFormatter.format(parsedDate)
    }

    private val callback = object : Callback<DTO> {

        override fun onResponse(call: Call<DTO>, response: Response<DTO>) {
            if (response.isSuccessful) {
                if (response.body() == null) {
                    liveData.postValue(
                        AppStateListSchedule.ErrorCode(
                            formatError(
                                response.code(),
                                response.message()
                            )
                        )
                    )
                } else {
                    response.body()?.let {
                        liveData.postValue(
                            AppStateListSchedule.Success(converterFromDTOtoListSchedule(it))
                        )
                    }
                }
            } else {
                liveData.postValue(
                    AppStateListSchedule.ErrorCode(
                        formatError(
                            response.code(),
                            response.message()
                        )
                    )
                )
            }
        }

        override fun onFailure(call: Call<DTO>, t: Throwable) {
            liveData.postValue(
                AppStateListSchedule.Error(
                    Throwable(
                        t.message
                    )
                )
            )
        }
    }

    fun saveSchedule(schedule: Schedule) {
        repositoryImplHistorySchedule.saveSchedule(schedule)
    }

    private fun formatError(code: Int, mess: String): String {
        return "Ошибка: ${"$code $mess"}"
    }
}