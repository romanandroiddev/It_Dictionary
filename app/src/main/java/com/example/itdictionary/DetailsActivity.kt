package com.example.itdictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.itdictionary.data.WordDao
import com.example.itdictionary.data.WordDatabase
import com.example.itdictionary.data.models.WordEntity
import com.example.itdictionary.databinding.ActivityDetailsBinding
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private var isBookmarked = 0
    private lateinit var studentDao: WordDao
    private var id = 0
    private var word = ""
    private var translation = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        studentDao = WordDatabase.getInstance(this).wordDao()



        initListeners()
        initData()
    }

    private fun initListeners() {
        binding.icBack.setOnClickListener {
            finish()
        }

        binding.icBookmark.clicks().debounce(200).onEach {
            isBookmarked = if (isBookmarked == 1) {
                0
            } else {
                1
            }

            studentDao.updateWord(WordEntity(id, word, translation, isBookmarked))
            if (isBookmarked == 1) {
                binding.icBookmark.setImageResource(R.drawable.ic_bookarked)
            } else {
                binding.icBookmark.setImageResource(R.drawable.ic_not_bookmarked)
            }
        }.launchIn(lifecycleScope)

    }

    private fun initData() {
        intent.extras?.apply {
            word = this.getString("WORD", "")
            translation = this.getString("TRANSLATION", "")
            isBookmarked = this.getInt("BOOKMARK")
            id = this.getInt("ID")
        }

        binding.tvWord.text = word
        binding.tvDecription.text = translation
        if (isBookmarked == 1) {
            binding.icBookmark.setImageResource(R.drawable.ic_bookarked)
        } else {
            binding.icBookmark.setImageResource(R.drawable.ic_not_bookmarked)
        }
    }


}