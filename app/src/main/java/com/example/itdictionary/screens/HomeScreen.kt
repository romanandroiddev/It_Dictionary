package com.example.itdictionary.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.itdictionary.R
import com.example.itdictionary.databinding.ScreenHomeBinding

class HomeScreen: Fragment(R.layout.screen_home) {
    private lateinit var binding: ScreenHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ScreenHomeBinding.bind(view)




        initObservers()
    }

    private fun initObservers() {
        TODO("Not yet implemented")
    }
}