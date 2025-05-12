package com.stanley.mindbridge.repository

import com.stanley.mindbridge.model.Contact
import com.stanley.mindbridge.model.ContactDao

class ContactRepository(private val ContactDao: ContactDao) {
    val allContact = ContactDao.getAllContact()

    suspend fun insert(contact: Contact) = ContactDao.insertContact(contact)
    suspend fun update(contact: Contact) = ContactDao.updateContact(contact)
    suspend fun delete(contact: Contact) = ContactDao.deleteContact(contact)
    suspend fun getById(id: Int) = ContactDao.getContactById(id)
}