package com.example.mytest.view.listSchedule

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mytest.appState.AppStateListSchedule
import com.example.mytest.databinding.FragmentListScheduleBinding
import com.example.mytest.model.Schedule
import com.example.mytest.utils.KEY_FROM_CODE
import com.example.mytest.utils.KEY_TO_CODE
import com.example.mytest.utils.TRANSPORT
import com.example.mytest.view.utilFragment.BaseFragment
import com.example.mytest.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list_schedule.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.util.*

class ListFragment :
    BaseFragment<FragmentListScheduleBinding>(FragmentListScheduleBinding::inflate),
    MyOnClickListener {

    private lateinit var localFrom: String
    private lateinit var localTo: String
    private lateinit var toDayDate: String

    private val listViewModel: ListViewModel by lazy {
        ViewModelProvider(this).get(ListViewModel::class.java)
    }

    private val adapter: ListAdapter by lazy {
        ListAdapter(this)
    }

    private fun correctDate() {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val date = sdf.format(Date())
        toDayDate = date
    }

    private fun initViewBtnDate() {
        tvBtnToday.setOnClickListener {
            listViewModel.getScheduleFromServerList(localFrom, localTo, toDayDate, TRANSPORT)
            Toast.makeText(requireContext(), toDayDate, Toast.LENGTH_LONG).show()
        }
        tvBtnTomorrow.setOnClickListener {
            val modificationDate = modDatePlusDay()
            listViewModel.getScheduleFromServerList(localFrom, localTo, modificationDate, TRANSPORT)
            Toast.makeText(requireContext(), modificationDate, Toast.LENGTH_LONG).show()
        }

        tvBtnDate.setOnClickListener {
            calendarDate()
        }
    }

    private fun modDatePlusDay(): String {
        val localDate = LocalDate.parse(toDayDate)
        val period = Period.of(0, 0, 1)
        return localDate.plus(period).toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        correctDate()
        initViewBtnDate()

        listViewModel.getLiveData().observe(viewLifecycleOwner, {
            renderData(it)
        })

        listScheduleRecycler.adapter = adapter
        arguments?.let {
            it.getString(KEY_FROM_CODE)?.run {
                localFrom = this
            }
            it.getString(KEY_TO_CODE)?.run {
                localTo = this
            }
            listViewModel.getScheduleFromServerList(localFrom, localTo, toDayDate, TRANSPORT)
            Toast.makeText(context, toDayDate, Toast.LENGTH_LONG).show()
        }
    }

    private fun renderData(appStateListSchedule: AppStateListSchedule) {
        when (appStateListSchedule) {
            is AppStateListSchedule.Success -> {
                val scheduleData = appStateListSchedule.scheduleData
                adapter.setSchedule(scheduleData)
            }
            is AppStateListSchedule.Error -> {
                val errorStr = appStateListSchedule.error.message
                Toast.makeText(context, errorStr, Toast.LENGTH_SHORT).show()
            }
            is AppStateListSchedule.ErrorCode -> {
                val messError = appStateListSchedule.messError
                Toast.makeText(context, messError, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onItemViewClick(schedule: Schedule) {
        listViewModel.saveSchedule(schedule)
    }

    companion object {
        fun newInstance(bundle: Bundle) = ListFragment().apply {
            arguments = bundle
        }
    }

    private fun calendarDate() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        val dpd = DatePickerDialog(
            requireContext(),
            { _, year, monthOfYear, dayOfMonth ->
                val mon = monthOfYear + 1
                Toast.makeText(context, "$year-$mon-$dayOfMonth", Toast.LENGTH_LONG).show()
                listViewModel.getScheduleFromServerList(
                    localFrom,
                    localTo,
                    "$year-$mon-$dayOfMonth",
                    TRANSPORT
                )
            },
            year,
            month,
            day
        )
        dpd.show()

    }
}