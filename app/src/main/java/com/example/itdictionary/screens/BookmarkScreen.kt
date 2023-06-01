package com.example.itdictionary.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.itdictionary.R
import com.example.itdictionary.databinding.ScreenBookmarkedBinding
import com.example.itdictionary.databinding.ScreenHomeBinding

class BookmarkScreen: Fragment(R.layout.screen_bookmarked) {
    private lateinit var binding: ScreenBookmarkedBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ScreenBookmarkedBinding.bind(view)




        initObservers()
    }

    private fun initObservers() {
        TODO("Not yet implemented")
    }
}