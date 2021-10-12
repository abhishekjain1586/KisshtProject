package com.example.kisshtproject.service

import com.example.kisshtproject.model.ResPhotos
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {

    // ?client_id=YOUR_ACCESS_KEY
    @GET("photos/")
    suspend fun getPhotos(/*@QueryMap map: Map<String, String>*/): Response<List<ResPhotos>>?
}