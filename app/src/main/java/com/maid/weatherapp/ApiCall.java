package com.maid.weatherapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCall {
    @GET("weather")
    Call<WeatherInfo> getWeatherData(@Query("q") String city, @Query("appid") String apiKey);
}
