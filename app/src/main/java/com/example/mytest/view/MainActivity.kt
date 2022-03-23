package com.example.mytest.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mytest.R
import com.example.mytest.databinding.ActivityMainBinding
import com.example.mytest.view.main.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_conteiner, MainFragment.newInstance())
                .commit()
        }
    }

}