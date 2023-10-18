package com.maid.weatherapp;

import com.google.gson.annotations.SerializedName;

public class WeatherInfo {
    @SerializedName("main")
    private MainData mainData;

    public MainData getMainData() {
        return mainData;
    }
}
