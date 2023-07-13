package com.example.itdictionary.screens

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.itdictionary.DetailsActivity
import com.example.itdictionary.R
import com.example.itdictionary.adapters.WordsAdapter
import com.example.itdictionary.data.WordDao
import com.example.itdictionary.data.WordDatabase
import com.example.itdictionary.databinding.ScreenBookmarkedBinding
import kotlinx.coroutines.launch

class BookmarkScreen : Fragment(R.layout.screen_bookmarked) {
    private lateinit var binding: ScreenBookmarkedBinding
    private val adapter = WordsAdapter()
    private lateinit var studentDao: WordDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ScreenBookmarkedBinding.bind(view)

        initListeners()
        initData()


    }

    private fun initData() {
        binding.rcBookmarkedWords.adapter = adapter

        studentDao = WordDatabase.getInstance(requireContext()).wordDao()

        lifecycleScope.launch{
            adapter.models = studentDao.getBookmarkeds().toMutableList()
        }
    }

    private fun initListeners() {
        adapter.setOnBookmarkClickListener { word, position->
            lifecycleScope.launch {
                studentDao.updateWord(word)
                adapter.models.removeAt(position)
                adapter.notifyItemRemoved(position)
            }
        }

        adapter.setOnClick {
            val intent = Intent(requireContext(), DetailsActivity::class.java)
            intent.putExtra("ID", it.id)
            intent.putExtra("WORD", it.word)
            intent.putExtra("TRANSLATION", it.translation)
            intent.putExtra("BOOKMARK", it.bookmarked)
            requireActivity().startActivity(intent)
        }
    }


}