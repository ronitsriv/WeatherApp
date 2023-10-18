package com.maid.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView txtInfo;
    private EditText edtCity;
    private Button btnFetch;

    ApiCall apiCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtInfo = findViewById(R.id.txt_result);
        edtCity = findViewById(R.id.edt_city);
        btnFetch = findViewById(R.id.btn_fetch);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServerInfo.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                                .build();

        apiCall = retrofit.create(ApiCall.class);


        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<WeatherInfo> call =  apiCall.getWeatherData(edtCity.getText().toString(), ServerInfo.API_KEY);
                call.enqueue(new Callback<WeatherInfo>() {
                    @Override
                    public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
                        WeatherInfo weatherInfo = response.body();
                        txtInfo.setText("Pressure: " + weatherInfo.getMainData().getPressure() + "Temperature: " + String.valueOf((weatherInfo.getMainData().getTemperature()) - 273));
                    }

                    @Override
                    public void onFailure(Call<WeatherInfo> call, Throwable t) {

                    }
                });
            }
        });
    }
}