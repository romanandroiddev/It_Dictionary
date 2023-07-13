package com.example.itdictionary.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "words")
data class WordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val word: String,
    val translation: String,
    var bookmarked: Int
)