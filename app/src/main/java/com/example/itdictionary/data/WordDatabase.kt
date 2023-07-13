package com.example.itdictionary.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.itdictionary.data.models.WordEntity


@Database(entities = [WordEntity::class], version = 1)
abstract class WordDatabase: RoomDatabase() {
    companion object {
        private var INSTANCE: WordDatabase? = null
        private val LOCK = Any()

        fun getInstance(context: Context): WordDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    context,
                    WordDatabase::class.java,
                    "words.db"
                )
                    .createFromAsset("words.db")
                    .build()
                INSTANCE = db
                return db
            }
        }
    }

    abstract fun wordDao(): WordDao
}