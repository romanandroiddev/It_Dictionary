package com.example.itdictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.itdictionary.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)



        initListeners()
    }

    private fun initListeners() {
        binding.icBack.setOnClickListener {
            finish()
        }
    }
}