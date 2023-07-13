package com.example.itdictionary

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itdictionary.adapters.SearchWordsAdapter
import com.example.itdictionary.data.WordDao
import com.example.itdictionary.data.WordDatabase
import com.example.itdictionary.databinding.ActivitySearchBinding
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.ldralighieri.corbind.view.clicks
import ru.ldralighieri.corbind.widget.textChanges


class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private val adapter = SearchWordsAdapter()
    private lateinit var wordsDao: WordDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        wordsDao = WordDatabase.getInstance(this).wordDao()



        initData()
        initListeners()


    }

    private fun initData() {

        val dividerItemDecoration = DividerItemDecoration(
            this, LinearLayoutManager.VERTICAL
        )

        binding.rcView.addItemDecoration(dividerItemDecoration)
        binding.rcView.adapter = adapter
    }


    private fun initListeners() {
        binding.icBack.clicks().debounce(200).onEach {
            finish()
        }.launchIn(lifecycleScope)

        binding.etSearch.textChanges().debounce(200).onEach {
            if (binding.etSearch.text.toString() == "") {
                adapter.models = wordsDao.getAllWords()
            } else {
                adapter.models = wordsDao.search(binding.etSearch.text.toString())
            }
        }.launchIn(lifecycleScope)

        adapter.setOnClick {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("ID", it.id)
            intent.putExtra("WORD", it.word)
            intent.putExtra("TRANSLATION", it.translation)
            intent.putExtra("BOOKMARK", it.bookmarked)
            startActivity(intent)
        }
    }
}