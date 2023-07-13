package com.example.itdictionary.screens

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.itdictionary.DetailsActivity
import com.example.itdictionary.R
import com.example.itdictionary.SearchActivity
import com.example.itdictionary.adapters.WordsAdapter
import com.example.itdictionary.data.WordDao
import com.example.itdictionary.data.WordDatabase
import com.example.itdictionary.databinding.ScreenHomeBinding
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.ldralighieri.corbind.view.clicks

class HomeScreen : Fragment(R.layout.screen_home) {
    private lateinit var binding: ScreenHomeBinding
    private val adapter = WordsAdapter()
    private lateinit var studentDao: WordDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ScreenHomeBinding.bind(view)


        initData()
        initListeners()
    }

    private fun initListeners() {
        binding.icSearch.clicks().debounce(200).onEach {
            val intent = Intent(requireContext(), SearchActivity::class.java)
            requireActivity().startActivity(intent)
        }.launchIn(lifecycleScope)

        adapter.setOnClick {
            val intent = Intent(requireContext(), DetailsActivity::class.java)
            intent.putExtra("ID", it.id)
            intent.putExtra("WORD", it.word)
            intent.putExtra("TRANSLATION", it.translation)
            intent.putExtra("BOOKMARK", it.bookmarked)
            requireActivity().startActivity(intent)
        }
        adapter.setOnBookmarkClickListener { word, position->
            lifecycleScope.launch {
                studentDao.updateWord(word)
            }
        }
    }

    private fun initData() {
        binding.rvWords.adapter = adapter

        studentDao = WordDatabase.getInstance(requireContext()).wordDao()

        lifecycleScope.launch{
            adapter.models = studentDao.getAllWords().toMutableList()
        }
    }
}