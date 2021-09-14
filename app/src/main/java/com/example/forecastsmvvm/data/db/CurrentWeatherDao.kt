package com.example.forecastsmvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.forecastsmvvm.data.db.entity.CURRENT_WEATHER_ID
import com.example.forecastsmvvm.data.db.entity.CurrentWeatherEntry
import com.example.forecastsmvvm.data.db.unitlocalized.ImperialCurrentWeatherEntry
import com.example.forecastsmvvm.data.db.unitlocalized.MetricCurrentWeatherEntry

//DAOs are always interfaces when we used room
@Dao
interface CurrentWeatherDao {
    //update and insert at the same time

    // bcoz it's replaced whenever there is a conflict , bcoz conflict
    // happens when we've two instances of currentweatherEntry which have the same id and basically
    //all of the instances which we've currentweatherentry will have the same id bcoz its by design
    //bcoz their ids are constant and they are all zero , so we are always have a conflict so
    //always when we want to add a new weatherentry and there is already one present
    //we're gonna get a conflict and we simply replace the old one with the new currentweatherentry
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry: CurrentWeatherEntry) {

    }

    @Query("Select * from current_weather where id = $CURRENT_WEATHER_ID")
    fun getWeatherMetric(): LiveData<MetricCurrentWeatherEntry>

    @Query("Select * from current_weather where id = $CURRENT_WEATHER_ID")
    fun getWeatherImperial(): LiveData<ImperialCurrentWeatherEntry>
}