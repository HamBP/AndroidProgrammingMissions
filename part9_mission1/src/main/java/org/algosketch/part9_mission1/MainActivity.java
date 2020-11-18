package org.algosketch.part9_mission1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

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

        todayMaxTemperature = findViewById(R.id.today_max_temperature);
        todayMinTemperature = findViewById(R.id.today_min_temperature);
        currentTemperature = findViewById(R.id.current_temperature);

        queue= Volley.newRequestQueue(this);
        JsonObjectRequest jsonRequest=new JsonObjectRequest(Request.Method.GET, "https://api.openweathermap.org/data/2.5/weather?q=seoul&mode=json&units=metric&appid=e7d7b4b3f40299f32bf2c14dcae22048",
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    currentTemperature.setText(valueOf(response.getJSONObject("main").getDouble("temp")));
                    todayMaxTemperature.setText(valueOf(response.getJSONObject("main").getDouble("temp_max")));
                    todayMinTemperature.setText(valueOf(response.getJSONObject("main").getDouble("temp_min")));
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
        queue.add(jsonRequest);
    }
}