package com.dam.uf5actividadretrofitweather.retrofitUtils;

import com.dam.uf5actividadretrofitweather.retrofitData.WeatherRes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIRestService {

    public static final String BASE_URL = "https://api.darksky.net/forecast/";

    // GET es una petición al servidor para obtener recursos o colección de estos.
    // Para que salga con los datos en español debo pasarle:
    // ?exclude=minutely,hourly,daily,alerts,flags&lang=es.
    @GET("{key}/{latitude},{longitude}?exclude=minutely,hourly,daily,alerts,flags&lang=es")
    Call<WeatherRes> obtenerWeather(
        @Path("key") String key,
        @Path("latitude") String latitude,
        @Path("longitude") String longitude);
}
