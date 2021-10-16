package com.notes.notes.lists.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.notes.notes.database.ListRepository
import com.notes.notes.database.ModelListItems
import kotlinx.coroutines.launch

class ListViewModel(
    private val repository: ListRepository
) : ViewModel() {

    val state: MutableLiveData<UiEvent> = MutableLiveData<UiEvent>()

    val allLists: LiveData<List<ModelListItems>> =
        repository.allModels.asLiveData()

    fun insert(modelListItems: ModelListItems) = viewModelScope.launch {
        repository.insert(modelListItems)
    }

    fun processUi(uiEvent: UiEvent) = viewModelScope.launch {
        when (uiEvent) {
            UiEvent.Loading -> Log.d("ListFragment", "processUi onLoading")
            is UiEvent.ListSelected -> {
                Log.d("ListFragment", "processUi : ${uiEvent.position}")
            }
        }
    }
}

class ListViewModelFactory(private val repository: ListRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

sealed class UiEvent {
    object Loading : UiEvent()
    object Success : UiEvent()
    object Failure : UiEvent()
    data class ListSelected(val position: Int) : UiEvent()
    data class ListLastPosition(val lastPosition: Int) :
        UiEvent() // for insert in DataBase, last element in list
}