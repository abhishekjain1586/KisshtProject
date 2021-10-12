package com.example.kisshtproject.view.main

import android.app.Application
import android.content.res.Resources
import android.util.DisplayMetrics
import com.example.kisshtproject.dagger.DaggerServiceComponent
import com.example.kisshtproject.service.ApiService

class MyApp : Application() {

    private var apiService: ApiService? = null
    private val displayMetrics = DisplayMetrics()
    companion object {
        lateinit var INSTANCE: MyApp
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    fun getService(): ApiService {
        if (apiService == null) {
            apiService = DaggerServiceComponent.create().getService()
        }
        return apiService!!
    }

    fun getWindowHeight(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }

    fun getWindowWidth(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }
}