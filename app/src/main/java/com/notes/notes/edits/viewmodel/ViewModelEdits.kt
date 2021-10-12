package com.notes.notes.edits.viewmodel

import androidx.lifecycle.*
import com.notes.notes.database.ListRepository
import com.notes.notes.database.ModelListItems
import kotlinx.coroutines.launch

class ViewModelEdits(
    private val repository: ListRepository
) : ViewModel() {

    val allLists: LiveData<List<ModelListItems>> =
        repository.allModels.asLiveData()

    fun getModelById(id: Long): LiveData<ModelListItems> {
        return repository.getById(id).asLiveData()
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(modelListItems: ModelListItems) = viewModelScope.launch {
        repository.insert(modelListItems)
    }

    fun update(modelListItems: ModelListItems) = viewModelScope.launch {
        repository.update(modelListItems)
    }
}

class ListViewModelFactory(private val repository: ListRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelEdits::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ViewModelEdits(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}