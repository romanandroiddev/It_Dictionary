package com.example.itdictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.itdictionary.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initListeners()
        initObservers()
    }

    private fun initObservers() {
        TODO("Not yet implemented")
    }

    private fun initListeners() {

    }
}