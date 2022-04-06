package com.example.mytest.view.main

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.mytest.R
import com.example.mytest.appState.AppStateHistorySchedule
import com.example.mytest.databinding.FragmentMainBinding
import com.example.mytest.model.CodeStationAPI
import com.example.mytest.repository.RepositoryImplHistorySchedule
import com.example.mytest.utils.*
import com.example.mytest.view.listSchedule.ListFragment
import com.example.mytest.view.search.SearchCityFragment
import com.example.mytest.view.utilFragment.BaseFragment
import com.example.mytest.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private var stationFrom: String? = null
    private var stationTo: String? = null
    private var codeStationFrom: String? = null
    private var codeStationTo: String? = null

    private val adapter: MainAdapter by lazy {
        MainAdapter()
    }
    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private val repositoryImplHistorySchedule: RepositoryImplHistorySchedule by lazy {
        RepositoryImplHistorySchedule()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.getLiveData().observe(viewLifecycleOwner, {
            renderData(it)
        })

        recyclerHistory.adapter = adapter

        showSP()
        initViewBtn()

        arguments?.let {
            it.getParcelable<CodeStationAPI>(KEY_SEARCH_ONE)?.run {
                saveSP(true, this.nameStation, this.codes)
                initStationTextView(true)
            }
            it.getParcelable<CodeStationAPI>(KEY_SEARCH_TWO)?.run {
                saveSP(false, this.nameStation, this.codes)
                initStationTextView(false)
            }
        }
        loadingHistory()
    }

    private fun loadingHistory() {
        mainViewModel.getHistory()
    }


    private fun initViewBtn() {
        btnOpenSchedule.setOnClickListener {
            toFragment(codeStationFrom!!, codeStationTo!!)
        }

        with(cityDepartures) {
            text = stationFrom
            setOnClickListener {
                onSearchFragment(true)
            }
        }

        with(cityArrival) {
            text = stationTo
            setOnClickListener {
                onSearchFragment(false)
            }
        }

        tvBtnDeleteHistory.setOnClickListener {
            repositoryImplHistorySchedule.deleteAllHistory()
            loadingHistory()
        }
    }

    private fun onSearchFragment(flag: Boolean) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .addToBackStack("")
            .add(R.id.fragment_container, SearchCityFragment.newInstance(Bundle().apply {
                putBoolean(KEY_FLAG_SEARCH, flag)
            }))
            .commit()
    }

    private fun initStationTextView(flag: Boolean) {
        showSP()
        if (flag) {
            cityDepartures.text = stationFrom!!
        } else {
            cityArrival.text = stationTo
        }
    }

    private fun toFragment(fromCode: String, toCode: String) {
        requireActivity()
            .supportFragmentManager
            .beginTransaction()
            .addToBackStack("")
            .add(R.id.fragment_container, ListFragment.newInstance(
                Bundle().apply {
                    putString(KEY_FROM_CODE, fromCode)
                    putString(KEY_TO_CODE, toCode)
                }
            ))
            .commit()
    }

    private fun renderData(appStateHistorySchedule: AppStateHistorySchedule) {
        when (appStateHistorySchedule) {
            is AppStateHistorySchedule.Success -> {
                val listHistory = appStateHistorySchedule.schedule
                adapter.setListHistory(listHistory)
            }
        }
    }

    companion object {
        fun newInstance(bundle: Bundle) = MainFragment().apply {
            arguments = bundle
        }
    }

    // TODO оставил хранение последнего запроса в SP, не стал делать в Room
    private fun showSP() {
        activity?.let {
            stationFrom =
                it.getPreferences(Context.MODE_PRIVATE).getString("STATION_ONE", "Введите город")
            stationTo =
                it.getPreferences(Context.MODE_PRIVATE).getString("STATION_TWO", "Введите город")
            codeStationFrom = it.getPreferences(Context.MODE_PRIVATE).getString("CODES_ONE", "0")
            codeStationTo = it.getPreferences(Context.MODE_PRIVATE).getString("CODES_TWO", "0")
        }
    }

    private fun saveSP(flag: Boolean, station: String, codes: String) {
        if (flag) {
            activity?.let {
                it.getPreferences(Context.MODE_PRIVATE).edit().putString("STATION_ONE", station)
                    .apply()
                it.getPreferences(Context.MODE_PRIVATE).edit().putString("CODES_ONE", codes).apply()
            }
        } else {
            activity?.let {
                it.getPreferences(Context.MODE_PRIVATE).edit().putString("STATION_TWO", station)
                    .apply()
                it.getPreferences(Context.MODE_PRIVATE).edit().putString("CODES_TWO", codes).apply()
            }
        }
    }
}