package com.example.itdictionary.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.itdictionary.R
import com.example.itdictionary.data.models.WordEntity
import com.example.itdictionary.databinding.ItemDictionaryBinding
import com.example.itdictionary.databinding.ItemDictionaryBookmarkBinding

class SearchWordsAdapter : RecyclerView.Adapter<SearchWordsAdapter.WordViewHolder>() {
    var models = listOf<WordEntity>()
        @SuppressLint("NotifyDataSetChanged") set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class WordViewHolder(private val binding: ItemDictionaryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(word: WordEntity) {
            binding.apply {
                tvWord.text = word.word


                root.setOnClickListener {
                    onClick.invoke(word)
                }
            }
        }
    }

    override fun getItemCount(): Int = models.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dictionary, parent, false)
        val binding = ItemDictionaryBinding.bind(view)
        return WordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.bind(models[position])
    }

    private var onClick: (word: WordEntity) -> Unit = {}
    fun setOnClick(onClick: (word: WordEntity) -> Unit) {
        this.onClick = onClick
    }

}