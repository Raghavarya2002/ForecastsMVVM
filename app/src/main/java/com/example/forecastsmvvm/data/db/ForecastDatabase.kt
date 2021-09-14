package com.example.forecastsmvvm.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.forecastsmvvm.data.db.entity.CurrentWeatherEntry

@Database(
    entities = [CurrentWeatherEntry::class],
    version = 1
)
abstract class ForecastDatabase {

    abstract fun currentWeatherDao(): CurrentWeatherDao

    //bcoz database need to be a singleton bcoz it doesn't make sense to having multiple instances
    //of the database at a same time
    //also the first time when we creae an instance of the forecast database , we want to build a database
    //with special method call throgh room library
    companion object {


        //so all of the threads will have immediate access to this property
        @Volatile
        private var instance: ForecastDatabase? = null

        //LOCK object , it doesn't matter what type of this lock object is
        //basically its a dummy object to make sure that no two threads are currently doing the same thing
        //bcoz then it could happen that we have actually  two instances at the same time bcoz
        //two threads are working in parallel and its all messed up
        private var Lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(Lock) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ForecastDatabase::class.java, "forecast.db"
            )
                .build()


    }


}