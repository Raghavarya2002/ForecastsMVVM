package com.example.forecastsmvvm.data.db.entity


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


const val CURRENT_WEATHER_ID = 0

// entities are the tables in the SQLite database , so we annotate this class with an entity
//annotation which is from android x room library


@Entity(tableName = "current_weather")
data class CurrentWeatherEntry(

    //room is just a wrapper on the top of SqLite database and SQLite only stores primitive datatypes
// it stores int , double , string , but it doesn't store "Condition" bcoz Condition is a custom class
    //
//we want to prefix these fields with "condition_" , so in our database the condition object will not
    //really a object while it'll be in three separate fields, and there names will be the condition_text
    //and like that
    @Embedded(prefix = "condition_")
    val condition: Condition,
    @SerializedName("feelslike_c")
    val feelslikeC: Double,
    @SerializedName("feelslike_f")
    val feelslikeF: Double,
    @SerializedName("gust_kph")
    val gustKph: Double,
    @SerializedName("gust_mph")
    val gustMph: Double,

    @SerializedName("is_day")
    val isDay: Int,

    @SerializedName("last_updated_epoch")
    val lastUpdatedEpoch: Int,
    @SerializedName("precip_in")
    val precipIn: Double,
    @SerializedName("precip_mm")
    val precipMm: Double,

    @SerializedName("temp_c")
    val tempC: Double,
    @SerializedName("temp_f")
    val tempF: Double,
    val uv: Double,
    @SerializedName("vis_km")
    val visKm: Double,
    @SerializedName("vis_miles")
    val visMiles: Double,

    @SerializedName("wind_dir")
    val windDir: String,
    @SerializedName("wind_kph")
    val windKph: Double,
    @SerializedName("wind_mph")
    val windMph: Double
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = CURRENT_WEATHER_ID
}