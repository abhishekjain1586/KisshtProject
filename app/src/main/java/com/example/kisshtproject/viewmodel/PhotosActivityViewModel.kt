package com.example.kisshtproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kisshtproject.model.ResPhotos
import com.example.kisshtproject.networkutil.Resource
import com.example.kisshtproject.networkutil.Status
import com.example.kisshtproject.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PhotosActivityViewModel(private val repo: NetworkRepository) : ViewModel() {

    private var photosLst: ArrayList<ResPhotos>? = null
    private var lvdLoader = SingleLiveEvent<Boolean>()
    private var lvdErrorMsg = SingleLiveEvent<String>()
    private var lvdPhotosLst = SingleLiveEvent<ArrayList<ResPhotos>>()

    fun obsvShowLoader(): SingleLiveEvent<Boolean> {
        return lvdLoader
    }

    fun obsvGetErrorMsg(): SingleLiveEvent<String> {
        return lvdErrorMsg
    }

    fun obsvGetPhotos(): SingleLiveEvent<ArrayList<ResPhotos>> {
        return lvdPhotosLst
    }

    fun getPhotos() {
        lvdLoader.value = true
        viewModelScope.launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.IO) {
                repo.getPhotos()
            }
            showPhotos(response)
        }
    }

    private fun showPhotos(res: Resource<ArrayList<ResPhotos>>) {
        lvdLoader.value = false
        if (res.status == Status.SUCCESS) {
            photosLst = res.data
            photosLst?.let {
                lvdPhotosLst.value = it
            } ?: run {
                lvdErrorMsg.value = ""
            }
        } else {
            res.errorMsg?.let {
                lvdErrorMsg.value = it
            } ?: run {
                lvdErrorMsg.value = ""
            }
        }
    }
}