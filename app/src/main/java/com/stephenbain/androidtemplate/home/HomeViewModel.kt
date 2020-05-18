package com.stephenbain.androidtemplate.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Example ViewModel fetching data using a coroutine. Delete and replace as needed.
 */
class HomeViewModel @Inject constructor(private val getHomeText: GetHomeText) : ViewModel() {
    private val _state = MutableLiveData<HomeState>()

    val state: LiveData<HomeState>
        get() = _state

    init {
        _state.value = HomeState.Loading
        viewModelScope.launch {
            val text = withContext(Dispatchers.IO) { getHomeText() }
            _state.value = HomeState.Success(text)
        }
    }
}

sealed class HomeState {
    object Loading : HomeState()
    data class Success(val text: String) : HomeState()
}

/**
 * Example of using a coroutine to fetch data. delete and replace as necessary
 */
class GetHomeText @Inject constructor() {
    suspend operator fun invoke(): String {
        delay(2000)
        return "haha this is ur app lol"
    }
}