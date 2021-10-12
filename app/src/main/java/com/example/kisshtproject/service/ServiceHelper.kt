package com.example.kisshtproject.service

import com.example.kisshtproject.view.main.MyApp

object ServiceHelper {

    fun getService(): ApiService {
        return MyApp.INSTANCE.getService()
    }

}