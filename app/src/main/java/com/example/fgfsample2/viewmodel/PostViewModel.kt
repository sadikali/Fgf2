package com.example.fgfsample2.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fgfsample2.model.Photos
import com.example.fgfsample2.network.RetrofitClient
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {
    private val _posts = mutableStateOf<List<Photos>>(emptyList())
    val posts: State<List<Photos>> = _posts

    init {
        fetchPhotos()
    }

    private fun fetchPhotos() {
        viewModelScope.launch {
            try {
                _posts.value = RetrofitClient.apiService.getPhotos()
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}