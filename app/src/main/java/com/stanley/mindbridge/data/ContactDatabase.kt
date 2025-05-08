package com.stanley.mindbridge.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.stanley.mindbridge.model.Contact
import com.stanley.mindbridge.model.ContactDao


@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun ContactDao(): ContactDao

    companion object {
        @Volatile private var INSTANCE: ContactDatabase? = null

        fun getDatabase(context: Context): ContactDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    ContactDatabase::class.java,
                    "Contact_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
