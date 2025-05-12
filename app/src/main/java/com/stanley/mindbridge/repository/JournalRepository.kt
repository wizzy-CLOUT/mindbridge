package com.stanley.mindbridge.repository

import com.stanley.mindbridge.data.JournalDao
import com.stanley.mindbridge.model.Journal

class JournalRepository(private val JournalDao: JournalDao) {
    val allJournal = JournalDao.getAllJournal()

    suspend fun insert(journal: Journal) = JournalDao.insertJournal(journal)
    suspend fun update(journal: Journal) = JournalDao.updateJournal(journal)
    suspend fun delete(journal: Journal) = JournalDao.deleteJournal(journal)
    suspend fun getById(id: Int) = JournalDao.getJournalById(id)
}