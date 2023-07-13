package com.example.itdictionary.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.itdictionary.R
import com.example.itdictionary.data.models.WordEntity
import com.example.itdictionary.databinding.ItemDictionaryBookmarkBinding

class WordsAdapter : RecyclerView.Adapter<WordsAdapter.WordViewHolder>() {
    var models = mutableListOf<WordEntity>()
        @SuppressLint("NotifyDataSetChanged") set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class WordViewHolder(private val binding: ItemDictionaryBookmarkBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(word: WordEntity) {
            binding.apply {
                tvWord.text = word.word

                if (word.bookmarked == 1) {
                    icBookmark.setImageResource(R.drawable.ic_bookarked)
                } else {
                    icBookmark.setImageResource(R.drawable.ic_not_bookmarked)
                }

                icBookmark.setOnClickListener {
                    if (word.bookmarked==1){
                        word.bookmarked = 0
                    }else{
                        word.bookmarked = 1
                    }

                    onBookMarkClick.invoke(word, adapterPosition)

                    if (word.bookmarked == 1) {
                        icBookmark.setImageResource(R.drawable.ic_bookarked)
                    } else {
                        icBookmark.setImageResource(R.drawable.ic_not_bookmarked)
                    }
                }

                root.setOnClickListener {
                    onClick.invoke(word)
                }
            }
        }
    }

    override fun getItemCount(): Int = models.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dictionary_bookmark, parent, false)
        val binding = ItemDictionaryBookmarkBinding.bind(view)
        return WordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.bind(models[position])
    }

    private var onClick: (word: WordEntity) -> Unit = {}
    fun setOnClick(onClick: (word: WordEntity) -> Unit) {
        this.onClick = onClick
    }


    private var onBookMarkClick: (word: WordEntity, pos: Int) -> Unit = {word, pos ->  }
    fun setOnBookmarkClickListener(onClick: (word: WordEntity, pos: Int) -> Unit) {
        this.onBookMarkClick = onClick
    }
}