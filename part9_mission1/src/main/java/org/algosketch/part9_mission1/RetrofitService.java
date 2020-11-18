package org.algosketch.part9_mission1;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET("forecast/daily")
    Call<DailyWeather> getDailyWeather(@Query("q") String q,
                                       @Query("mode") String mode,
                                       @Query("units") String units,
                                       @Query("appid") String appid);

    @GET("weather")
    Call<TodayWeather> getTodayWeather(@Query("q") String q,
                               @Query("mode") String mode,
                               @Query("units") String units,
                               @Query("appid") String appid);
}
