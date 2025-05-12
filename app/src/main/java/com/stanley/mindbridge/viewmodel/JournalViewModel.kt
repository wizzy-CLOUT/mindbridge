package com.stanley.mindbridge.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stanley.mindbridge.model.Contact
import com.stanley.mindbridge.model.Journal
import com.stanley.mindbridge.repository.JournalRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class JournalViewModel(private val repository: JournalRepository) : ViewModel() {
    val allJournal = repository.allJournal

    private val _selectedJournal = MutableStateFlow<Journal?>(null)
    val selectedJournal: StateFlow<Journal?> = _selectedJournal.asStateFlow()

    private val _selectedContact = MutableStateFlow<Contact?>(null)
    val selectedContact: StateFlow<Contact?> = _selectedContact.asStateFlow()


    fun insert(journal: Journal) = viewModelScope.launch {
        repository.insert(journal)
    }

    fun update(journal: Journal) = viewModelScope.launch {
        repository.update(journal)
    }

    fun delete(journal: Journal) = viewModelScope.launch {
        repository.delete(journal)
    }

    fun loadJournalById(id: Int) = viewModelScope.launch {
        _selectedJournal.value = repository.getById(id)
    }
}