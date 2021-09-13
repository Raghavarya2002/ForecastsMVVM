package com.example.forecastsmvvm.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.forecastsmvvm.data.db.entity.CurrentWeatherEntry

//DAOs are always interfaces when we used room
@Dao
interface CurrentWeatherDao {
    //update and insert at the same time

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry: CurrentWeatherEntry) {

    }
}