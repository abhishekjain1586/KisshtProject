package com.example.kisshtproject.dagger

import com.example.kisshtproject.service.ApiService
import dagger.Component

@Component(modules = arrayOf(ServiceModule::class))
interface ServiceComponent {
    fun getService(): ApiService
}