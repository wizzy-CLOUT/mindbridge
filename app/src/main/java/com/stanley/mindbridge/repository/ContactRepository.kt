package com.stanley.mindbridge.repository

import com.stanley.mindbridge.model.Contact
import com.stanley.mindbridge.model.ContactDao

class ContactRepository(private val contentDao: ContactDao) {
    val allContact = contentDao.getAllContact()

    suspend fun insert(contact: Contact) = contentDao.insertContact(contact)
    suspend fun update(contact: Contact) = contentDao.updateContact(contact)
    suspend fun delete(contact: Contact) = contentDao.deleteContact(contact)
    suspend fun getById(id: Int) = contentDao.getContactById(id)
}