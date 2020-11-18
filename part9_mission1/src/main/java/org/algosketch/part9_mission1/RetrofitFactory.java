package org.algosketch.part9_mission1;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    static String BASE_URL = "https://api.openweathermap.org/data/2.5/";

    static RetrofitService getRetrofitService() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitService.class);
    }
}
