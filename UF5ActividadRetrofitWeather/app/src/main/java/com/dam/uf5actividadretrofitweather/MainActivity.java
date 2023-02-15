package com.dam.uf5actividadretrofitweather;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dam.uf5actividadretrofitweather.retrofitData.WeatherRes;
import com.dam.uf5actividadretrofitweather.retrofitUtils.APIRestService;
import com.dam.uf5actividadretrofitweather.retrofitUtils.RetrofitClient;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

// ANTES DE NADA ME TENGO QUE ACORDAR DE LAS DEPENDECIAS DEL GRADLE APP Y
// DE LOS PERMISOS DE INTERNET EN EL MANIFEST. A PARTE DE HACER EL GSON.

public class MainActivity extends AppCompatActivity {

    private static final String API_KEY = "11ce4328111023379e0fdc9d28c24a02";
    EditText etLat, etLon;
    Button btn;
    LinearLayout llRes;
    TextView tvRes;
    TextView tvTimeRes;
    TextView tvGradosRes;
    TextView tvResHum;
    TextView tvResLlu;
    TextView tvResTiDet;

    ProgressBar pb;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etLat = findViewById(R.id.etLatitud);
        etLon = findViewById(R.id.etLongitud);
        btn = findViewById(R.id.btnConsultar);
        llRes = findViewById(R.id.llResultado);
        tvRes = findViewById(R.id.tvResultado);
        tvTimeRes = findViewById(R.id.tvHoraRes);
        tvGradosRes = findViewById(R.id.tvGradosRes);
        tvResHum = findViewById(R.id.tvResHumedad);
        tvResLlu = findViewById(R.id.tvResLluvia);
        tvResTiDet = findViewById(R.id.tvResTiempoDetalles);

        pb = findViewById(R.id.progressBar);
        iv = findViewById(R.id.ivIcono);

        llRes.setVisibility(View.GONE);
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
                llRes.setVisibility(View.VISIBLE);
                if (response.isSuccessful()) {
                    WeatherRes weatherRes = response.body();
                    tvRes.setText(weatherRes.getTimezone());
                    //*********************************************************************************
                    // PREPARAMOS LA HORA PARA QUE SOLO MUESTRE LA HORA Y LOS MINUTOS.
                    //*********************************************************************************
                    // Obtenemos el tiempo en milisegundos y lo convertimos a la hora
                    Date date = new Date(weatherRes.getCurrently().getTime() * 1000);
                    // Lo mostramos en el TextView pero que solo muestre la hora y los minutos.
                    tvTimeRes.setText(date.getHours() + ":" + date.getMinutes());
                    //*********************************************************************************
                    // PREPARAMOS LA TEMPERATURA PARA QUE SOLO MUESTRE LOS GRADOS ENTEROS Y EN GRADOS CELSIUS.
                    //*********************************************************************************
                    Double temperatura = weatherRes.getCurrently().getTemperature();
                    // Lo convertimos a grados celsius.
                    temperatura = (temperatura - 32) * 5 / 9;
                    // Lo mostramos en el TextView pero que solo muestre los grados Enteros.
                    // Le pasamos %d para que muestre un entero y le pasamos el valor de la temperatura.
                    tvGradosRes.setText(String.format("%d ºC", temperatura.intValue()));
                    tvResHum.setText(weatherRes.getCurrently().getHumidity() + "%");
                    tvResLlu.setText(weatherRes.getCurrently().getPrecipProbability() + "%");
                    tvResTiDet.setText(weatherRes.getCurrently().getSummary());
                    // Cargar la imagen en el ImageView. Tenemos que usarlo porque no podemos cargar una imagen desde una URL.
                    String url = "https://darksky.net/images/weather-icons/" + weatherRes.getCurrently().getIcon() + ".png";
                    Glide.with(MainActivity.this).load(url).into(iv);

                    System.out.println(weatherRes);
                } else {
                    System.out.println("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<WeatherRes> call, Throwable t) {
                pb.setVisibility(View.GONE);
                llRes.setVisibility(View.VISIBLE);
                System.out.println("Error: " + t.getMessage());
            }
        });

    }
}