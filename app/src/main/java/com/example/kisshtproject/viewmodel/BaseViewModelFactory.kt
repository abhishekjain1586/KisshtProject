package com.example.kisshtproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kisshtproject.repository.NetworkRepository
import com.example.kisshtproject.service.ApiService
import java.lang.IllegalArgumentException

class BaseViewModelFactory(private val apiService: ApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhotosActivityViewModel::class.java)) {
            return PhotosActivityViewModel(NetworkRepository(apiService)) as T
        }

        throw java.lang.IllegalArgumentException("ViewModel Not Found")
    }
}