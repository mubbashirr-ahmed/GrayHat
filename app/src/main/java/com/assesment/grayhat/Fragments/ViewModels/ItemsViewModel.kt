package com.assesment.grayhat.Fragments.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.assesment.grayhat.data.Model.Items
import com.assesment.grayhat.data.repository.IRepository
import com.assesment.grayhat.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor
    (val repository: IRepository) : ViewModel() {
    val _items = MutableLiveData<UiState<List<Items>>>()
    val items:LiveData<UiState<List<Items>>> get() = _items


    fun getAllItems() {
        if (_items.value !is UiState.Success) {
            _items.value = UiState.Loading
            repository.getItems {
                _items.value = it
            }
        }
    }


}