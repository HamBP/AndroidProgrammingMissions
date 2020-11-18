package org.algosketch.part9_mission1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity {
    RequestQueue queue;
    TextView todayMaxTemperature;
    TextView todayMinTemperature;
    TextView currentTemperature;

    String API_KEY = "e7d7b4b3f40299f32bf2c14dcae22048";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 오늘의 날씨
        todayMaxTemperature = findViewById(R.id.today_max_temperature);
        todayMinTemperature = findViewById(R.id.today_min_temperature);
        currentTemperature = findViewById(R.id.current_temperature);
/*
        queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonTodayRequest = new JsonObjectRequest(Request.Method.GET, "https://api.openweathermap.org/data/2.5/weather?q=seoul&mode=json&units=metric&appid=" + API_KEY,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject main = response.getJSONObject("main");
                    currentTemperature.setText(valueOf(main.getDouble("temp")));
                    todayMaxTemperature.setText(valueOf(main.getDouble("temp_max")));
                    todayMinTemperature.setText(valueOf(main.getDouble("temp_min")));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(jsonTodayRequest);
*/
        RetrofitService networkService = RetrofitFactory.getRetrofitService();

        Call<TodayWeather> todayWeatherCall = networkService.getTodayWeather("seoul", "json", "metric", API_KEY);
        todayWeatherCall.enqueue(new Callback<TodayWeather>() {
            @Override
            public void onResponse(Call<TodayWeather> call, Response<TodayWeather> response) {
                if(response.isSuccessful()) {
                    TodayWeather data = response.body();
                    currentTemperature.setText(valueOf(data.main.temp));
                    todayMaxTemperature.setText(valueOf(data.main.temp_max));
                    todayMinTemperature.setText(valueOf(data.main.temp_min));
                } else {
                    Log.d("algoerror", "enqueue fail1");
                }
            }

            @Override
            public void onFailure(Call<TodayWeather> call, Throwable t) {
                t.printStackTrace();
            }
        });

        Call<DailyWeather> call = networkService.getDailyWeather("seoul", "json", "metric", API_KEY);
        call.enqueue(new Callback<DailyWeather>() {
            @Override
            public void onResponse(Call<DailyWeather> call, retrofit2.Response<DailyWeather> response) {
                if(response.isSuccessful()) {
                    Log.i("algoInfo - ", "success");
                    //response.body().list.get(0).temp.max
                } else {
                    Log.d("algoerror", "enqueue fail2");
                }
            }

            @Override
            public void onFailure(Call<DailyWeather> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}