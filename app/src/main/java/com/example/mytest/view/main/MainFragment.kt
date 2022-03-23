package com.example.mytest.view.main

import com.example.mytest.databinding.FragmentMainBinding
import com.example.mytest.view.utilFragment.BaseFragment

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    companion object {
        fun newInstance() = MainFragment()
    }
}