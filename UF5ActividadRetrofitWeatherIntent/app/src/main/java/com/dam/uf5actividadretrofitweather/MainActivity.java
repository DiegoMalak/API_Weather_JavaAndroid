package com.dam.uf5actividadretrofitweather;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.dam.uf5actividadretrofitweather.retrofitData.WeatherRes;
import com.dam.uf5actividadretrofitweather.retrofitUtils.APIRestService;
import com.dam.uf5actividadretrofitweather.retrofitUtils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

// ANTES DE NADA ME TENGO QUE ACORDAR DE LAS DEPENDECIAS DEL GRADLE APP Y
// DE LOS PERMISOS DE INTERNET EN EL MANIFEST. A PARTE DE HACER EL GSON.

public class MainActivity extends AppCompatActivity {

    private static final String API_KEY = "11ce4328111023379e0fdc9d28c24a02";
    EditText etLat, etLon;
    Button btn;

    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etLat = findViewById(R.id.etLatitud);
        etLon = findViewById(R.id.etLongitud);
        btn = findViewById(R.id.btnConsultar);

        pb = findViewById(R.id.progressBar);
        pb.setVisibility(View.GONE);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consumirWebService();
            }
        });
    }

    private void consumirWebService() {
        pb.setVisibility(View.VISIBLE);
        Retrofit r = RetrofitClient.getClient(APIRestService.BASE_URL);
        APIRestService ars = r.create(APIRestService.class);
        Call<WeatherRes> weatherResCall = ars.obtenerWeather(API_KEY, etLat.getText().toString(), etLon.getText().toString());

        weatherResCall.enqueue(new retrofit2.Callback<WeatherRes>() {
            // Poner el @SuppressLint("SetTextI18n") para que no me de error en el setText.
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<WeatherRes> call, Response<WeatherRes> response) {
                pb.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    // De este WeatherRes cogemos los datos que queramos y los pasamos a la siguiente actividad.
                    WeatherRes weatherRes = response.body();
                    // Aquí hacemos el intent para pasar los datos a la siguiente actividad.
                    Intent intent = new Intent(getApplicationContext(), TimeActivity.class);
                    // Con el putExtra y la clave puesta le pasamos los datos a la siguiente actividad.
                    // Pasamos la localización.
                    intent.putExtra("localizacion", weatherRes.getTimezone());
                    // Pasamos el icono.
                    intent.putExtra("icono", weatherRes.getCurrently().getIcon());
                    // Pasamos la hora, que le damos formato en la siguiente actividad.
                    intent.putExtra("hora", weatherRes.getCurrently().getTime());

                    // Le damos formato a la temperatura.
                    Double temperatura = weatherRes.getCurrently().getTemperature();
                    temperatura = (temperatura - 32) * 5 / 9;
                    // Pasamos la temperatura.
                    intent.putExtra("temperatura", String.format("%d ºC", temperatura.intValue()));
                    // Pasamos la humedad.
                    intent.putExtra("humedad", weatherRes.getCurrently().getHumidity() + "%");
                    // Pasamos la lluvia.
                    intent.putExtra("lluvia", weatherRes.getCurrently().getPrecipProbability() + "%");
                    // Pasamos la descripción del tiempo.
                    intent.putExtra("descripcion", weatherRes.getCurrently().getSummary());
                    // Lanzamos la siguiente actividad.
                    startActivity(intent);
                } else {
                    System.out.println("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<WeatherRes> call, Throwable t) {
                pb.setVisibility(View.GONE);
                System.out.println("Error: " + t.getMessage());
            }
        });

    }
}