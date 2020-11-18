package org.algosketch.part9_mission1;

import java.util.List;

public class DailyWeather {
    List<WeatherList> list = null;

    public class WeatherList {
        Temp temp;
        Weather weather;

        public class Temp {
            public double max;
            public double min;
        }

        public class Weather {
            public String icon;
        }
    }
}
