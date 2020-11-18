package org.algosketch.part9_mission1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity {
    RequestQueue queue;
    TextView todayMaxTemperature;
    TextView todayMinTemperature;
    TextView currentTemperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 오늘의 날씨
        todayMaxTemperature = findViewById(R.id.today_max_temperature);
        todayMinTemperature = findViewById(R.id.today_min_temperature);
        currentTemperature = findViewById(R.id.current_temperature);

        queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonTodayRequest = new JsonObjectRequest(Request.Method.GET, "https://api.openweathermap.org/data/2.5/weather?q=seoul&mode=json&units=metric&appid=e7d7b4b3f40299f32bf2c14dcae22048",
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


        // 주간 날씨
        JsonObjectRequest jsonWeekRequest = new JsonObjectRequest(Request.Method.GET, "https://api.openweathermap.org/data/2.5/forecast/daily?q=seoul&cnt=7&mode=json&units=metric&appid=e7d7b4b3f40299f32bf2c14dcae22048",
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String a = valueOf(response.getInt("cnt"));
                    currentTemperature.setText(a);
                    //JSONArray weathers = response.getJSONArray("list");
                    //for (int i = 0; i < 5; ++i) {
                    //    currentTemperature.setText(response.getString("cod"));
                    //}
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(jsonWeekRequest);
    }
}