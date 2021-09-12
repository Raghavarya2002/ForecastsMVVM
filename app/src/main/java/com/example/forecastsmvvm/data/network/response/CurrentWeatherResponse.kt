package com.example.forecastsmvvm.data.network.response

import com.example.forecastsmvvm.data.db.entity.CurrentWeatherEntry
import com.example.forecastsmvvm.data.db.entity.Location
import com.google.gson.annotations.SerializedName


data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,
    val location: Location
)