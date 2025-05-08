package com.stanley.mindbridge.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("SELECT * FROM Contact ORDER BY id DESC")
    fun getAllContact(): Flow<List<Contact>>

    @Query("SELECT * FROM Contact WHERE id = :id")
    suspend fun getContactById(id: Int): Contact?
}