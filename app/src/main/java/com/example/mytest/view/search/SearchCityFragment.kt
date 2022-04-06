package com.example.mytest.view.search

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.mytest.R
import com.example.mytest.appState.AppStateStationCode
import com.example.mytest.databinding.FragmentSearchCityBinding
import com.example.mytest.model.CodeStationAPI
import com.example.mytest.utils.KEY_FLAG_SEARCH
import com.example.mytest.utils.KEY_SEARCH_ONE
import com.example.mytest.utils.KEY_SEARCH_TWO
import com.example.mytest.view.main.MainFragment
import com.example.mytest.view.utilFragment.BaseFragment
import com.example.mytest.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search_city.*

class SearchCityFragment :
    BaseFragment<FragmentSearchCityBinding>(FragmentSearchCityBinding::inflate), MyOnClickListener {

    private var flag: Boolean = true

    private val adapter: SearchStationAdapter by lazy {
        SearchStationAdapter(this)
    }
    private val searchLiveData: SearchViewModel by lazy {
        ViewModelProvider(this).get(SearchViewModel::class.java)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            it.getBoolean(KEY_FLAG_SEARCH).run {
                flag = this
            }
        }
        searchLiveData.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }
        searchLiveData.getListStation()
        searchStationRecycler.adapter = adapter
    }

    private fun renderData(appStateStationCode: AppStateStationCode) {
        when (appStateStationCode) {
            is AppStateStationCode.Success -> {
                val codeStationList = appStateStationCode.codeStationList
                adapter.setListStation(codeStationList)
            }
            is AppStateStationCode.StationOne -> {
                val station = appStateStationCode.codeStationAPI
                openMainFragment(KEY_SEARCH_ONE, station)
            }
            is AppStateStationCode.StationTwo -> {
                val station = appStateStationCode.codeStationAPI
                openMainFragment(KEY_SEARCH_TWO, station)
            }
        }
    }

    private fun openMainFragment(key: String, codeStationAPI: CodeStationAPI) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MainFragment.newInstance(Bundle().apply {
                putParcelable(key, codeStationAPI)
            })).commit()
    }

    override fun myOnClickItem(codeStationAPI: CodeStationAPI) {
        searchLiveData.getCodeStationCity(flag, codeStationAPI)
    }

    companion object {
        fun newInstance(bundle: Bundle) =
            SearchCityFragment().apply {
                arguments = bundle
            }
    }
}