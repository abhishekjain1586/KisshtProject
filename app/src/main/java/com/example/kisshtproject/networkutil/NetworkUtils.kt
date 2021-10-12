package com.example.kisshtproject.networkutil

import android.content.Context
import android.net.ConnectivityManager
import com.example.kisshtproject.view.main.MyApp

object NetworkUtils {

    fun isNetworkConnected() : Boolean {
        val cm = MyApp.INSTANCE.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo?.isConnected ?: false
    }
}