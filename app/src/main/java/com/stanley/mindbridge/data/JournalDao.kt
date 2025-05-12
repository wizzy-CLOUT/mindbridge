package com.stanley.mindbridge.data

import androidx.room.*
import com.stanley.mindbridge.model.Journal
import kotlinx.coroutines.flow.Flow

@Dao
interface JournalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJournal(journal: Journal)

    @Update
    suspend fun updateJournal(journal: Journal)

    @Delete
    suspend fun deleteJournal(journal: Journal)

    @Query("SELECT * FROM journal ORDER BY id DESC")
    fun getAllJournal(): Flow<List<Journal>>

    @Query("SELECT * FROM journal WHERE id = :id")
    suspend fun getJournalById(id: Int): Journal?
}