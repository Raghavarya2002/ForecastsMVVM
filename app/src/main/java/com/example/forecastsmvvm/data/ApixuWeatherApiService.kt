package com.example.forecastsmvvm.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//38c43fe3163e4551bea162220211109
const val API_Key = "38c43fe3163e4551bea162220211109"

//http://api.weatherapi.com/v1/current.json?key=38c43fe3163e4551bea162220211109&q=London&aqi=no
interface ApixuWeatherApiService {

    @GET("current.json")
    fun getCurrentWeatherAsync(

        @Query("q") location: String,
        @Query("lang") languageCode: String = "en"
    ): Deferred<CurrentWeatherResponse> // Deferred is some kind of analog of Future in Java: in encapsulates an operation that will be finished at some point in future after it's initialization. But is also related to coroutines in Kotlin.

    companion object {
        //companion object are basically like static methods
        //If you need to write a function that can be called without having a class instance but needs access to the internals of a class
        operator fun invoke(): ApixuWeatherApiService {
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("key", API_Key)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)


            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.weatherapi.com/v1/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApixuWeatherApiService::class.java)


        }
    }

}