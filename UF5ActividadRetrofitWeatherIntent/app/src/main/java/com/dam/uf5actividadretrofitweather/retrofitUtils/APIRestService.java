package com.dam.uf5actividadretrofitweather.retrofitUtils;

import com.dam.uf5actividadretrofitweather.retrofitData.WeatherRes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIRestService {

    // Debería haber puesto aquí el key, pero como lo voy a usar en varios sitios, lo he
    // declarado en el MainActivity, pero como la key es una variable debería haberla
    // declarado aquí como una variable.
    public static final String BASE_URL = "https://api.darksky.net/forecast/";

    // GET es una petición al servidor para obtener recursos o colección de estos.
    // Para que salga con los datos en español debo pasarle:
    // ?exclude=minutely,hourly,daily,alerts,flags&lang=es.
    // De esta manera dejo la parte del exclude fija de tal manera que con lo que me pide el
    // me funciona y me muestra en español.
    // Si quisiera hacerlo que fuera dinámico, tendría que hacer un objeto con los datos
    // que me pide el exclude y pasarle ese objeto en el parámetro de la función.
    // Para que me funcione el lang, tengo que ponerlo en el @GET.
    // Para que me funcione el key, tengo que ponerlo en el @Path.
    // Para que me funcione el latitude, tengo que ponerlo en el @Path.
    // Para que me funcione el longitude, tengo que ponerlo en el @Path.
    // Y ahora tendría que poner el exclude en el @Query.
    // Y ahora tendría que poner el lang en el @Query.
    @GET("{key}/{latitude},{longitude}?exclude=minutely,hourly,daily,alerts,flags&lang=es")
    Call<WeatherRes> obtenerWeather(
        @Path("key") String key,
        @Path("latitude") String latitude,
        @Path("longitude") String longitude);
}
