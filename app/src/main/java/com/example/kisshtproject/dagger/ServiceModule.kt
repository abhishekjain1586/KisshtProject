package com.example.kisshtproject.dagger

import com.example.kisshtproject.service.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val BASE_URL = "https://api.unsplash.com/"
private const val ACCESS_KEY_VALUE = "Mrwx6C8iMyK1V7-nV7Kh7zqI0qEaK2TlmQFlZRF_pns"
//private const val SECRET_KEY_VALUE = "11NUOk5_Kh8IyveI7Cnm6dUjht_Y0sXq4cv6pSZvJDE"

@Module
class ServiceModule {

    @Provides
    fun getLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    fun provideGsonFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideAuthenticationInterceptor(): Interceptor {
        return object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val builder = chain.request().newBuilder()
                    .addHeader("Authorization", "Client-ID " + ACCESS_KEY_VALUE)
                    .build()
                return chain.proceed(builder)
            }
        }
    }

    @Provides
    fun provideHttpClient(loggingInterceptor: HttpLoggingInterceptor, headerInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder().let {
            it.connectTimeout(1, TimeUnit.MINUTES)
            it.readTimeout(30, TimeUnit.SECONDS)
            it.writeTimeout(30, TimeUnit.SECONDS)
            it.addInterceptor(loggingInterceptor)
            it.addInterceptor(headerInterceptor)
            it.build()
        }
    }

    @Provides
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().let {
            it.baseUrl(BASE_URL)
            it.addConverterFactory(gsonConverterFactory)
            it.client(okHttpClient)
            it.build()
        }
    }

    @Provides
    fun provideService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}