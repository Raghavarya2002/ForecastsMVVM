package com.example.forecastsmvvm.data

import com.example.forecastsmvvm.data.response.CurrentWeatherResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//ef8738d496c04ef87833f45e863bbbf0
const val API_Key = "ef8738d496c04ef87833f45e863bbbf0"

//http://api.weatherstack.com/current?access_key=ef8738d496c04ef87833f45e863bbbf0&query=New%20York&lang=en
interface ApixuWeatherApiService {

    @GET("current")
    fun getCurrentWeatherAsync(

        @Query("query") location: String,
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
                    .addQueryParameter("access_key", API_Key)
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
                .baseUrl("https://api.weatherstack.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApixuWeatherApiService::class.java)


        }
    }

}