package com.example.databinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserViewModel(private val userApi: UserApi) : ViewModel() {

    private val _loading = MutableLiveData<LoadingState>()
    val loading: LiveData<LoadingState>
        get() = _loading

    private val _data = MutableLiveData<List<User>>()
    val data: LiveData<List<User>>
        get() = _data

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                _loading.value = LoadingState.LOADING
                val response = userApi.getUserAsync()
                if (response.isSuccessful) {
                    _data.value = response.body()
                    _loading.value = LoadingState.LOADED
                } else {
                    _loading.value = LoadingState.error(response.message())
                }
            } catch (e: Exception) {
                _loading.value = LoadingState.error(e.message)
            }
        }
    }
}
