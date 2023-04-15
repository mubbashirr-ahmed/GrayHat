package com.assesment.grayhat.data.repository

import com.assesment.grayhat.data.Model.Items
import com.assesment.grayhat.util.UiState

interface IRepository {
    fun getItems(result: (UiState<List<Items>>) -> Unit)
}