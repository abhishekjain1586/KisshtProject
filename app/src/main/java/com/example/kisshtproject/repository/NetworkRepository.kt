package com.example.kisshtproject.repository

import com.example.kisshtproject.model.ResPhotos
import com.example.kisshtproject.networkutil.NetworkUtils
import com.example.kisshtproject.networkutil.Resource
import com.example.kisshtproject.service.ApiService

class NetworkRepository(private val apiService: ApiService) {

    suspend fun getPhotos(): Resource<ArrayList<ResPhotos>> {
        if (NetworkUtils.isNetworkConnected()) {
            /*val queryParams = HashMap<String, String>()
            queryParams.put(ServiceUtils.PARAM_TERM, ServiceUtils.PARAM_TERM_VAL)
            queryParams.put(ServiceUtils.PARAM_LOCATION, ServiceUtils.PARAM_LOCATION_VAL)*/

            val response = apiService.getPhotos()
            if (response != null) {
                return if (response.isSuccessful) {
                    Resource.success(response.body() as ArrayList<ResPhotos>)
                } else {
                    Resource.failure(response.errorBody().toString())
                }
            } else {
                return Resource.failure(null)
            }
        }

        return Resource.failure(null)
    }
}