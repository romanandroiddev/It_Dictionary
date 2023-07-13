package com.example.itdictionary.data

import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.itdictionary.data.models.WordEntity

@Dao
interface WordDao{


    @Query("SELECT * FROM words")
    suspend fun getAllWords(): List<WordEntity>

    @Query("SELECT * FROM words WHERE word LIKE '%' || :searchValue|| '%'")
    suspend fun search(searchValue: String): List<WordEntity>

    @Query("SELECT * FROM words  WHERE bookmarked = 1")
    suspend fun getBookmarkeds(): List<WordEntity>


    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWord(word: WordEntity)

}