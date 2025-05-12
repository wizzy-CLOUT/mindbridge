package com.stanley.mindbridge.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.stanley.mindbridge.model.Journal


@Database(entities = [Journal::class], version = 1, exportSchema = false)
abstract class JournalDatabase : RoomDatabase() {
    abstract fun JournalDao(): JournalDao

    companion object {
        @Volatile private var INSTANCE: JournalDatabase? = null

        fun getDatabase(context: Context): JournalDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    JournalDatabase::class.java,
                    "Journal_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
