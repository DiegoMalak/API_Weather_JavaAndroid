package com.dam.uf5actividadretrofitweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

public class TimeActivity extends AppCompatActivity {

    TextView tvRes;
    ImageView iv;
    TextView tvTimeRes;
    TextView tvGradosRes;
    TextView tvResHum;
    TextView tvResLlu;
    TextView tvResTiDet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        // Inicializamos los elementos de la vista.
        tvRes = findViewById(R.id.tvResultado);
        tvTimeRes = findViewById(R.id.tvHoraRes);
        tvGradosRes = findViewById(R.id.tvGradosRes);
        tvResHum = findViewById(R.id.tvResHumedad);
        tvResLlu = findViewById(R.id.tvResLluvia);
        tvResTiDet = findViewById(R.id.tvResTiempoDetalles);
        iv = findViewById(R.id.ivIcono);

        // Recogemos los datos que nos ha pasado el MainActivity.
        // El Bundle nos ayuda a coger los datos que le pasamos al Intent.
        Bundle bundleExtras = getIntent().getExtras();
        // Recogemos los datos que nos ha pasado el MainActivity.
        // Tenemos que saber que tipo de dato es para poder cogerlo y pasarle,
        // la clave que le hemos puesto al pasarle los datos para que sepa que dato tiene que coger.
        String localizacion = bundleExtras.getString("localizacion");
        String icono = bundleExtras.getString("icono");
        Integer hora = bundleExtras.getInt("hora");
        String temperatura = bundleExtras.getString("temperatura");
        String humedad = bundleExtras.getString("humedad");
        String lluvia = bundleExtras.getString("lluvia");
        String descripcion = bundleExtras.getString("descripcion");

        // Ponemos los datos recogidos en los TextView, ImageView y así poder mostrarlos.
        tvRes.setText(localizacion);
        //****************************************************************************************************
        // La API nos da el tiempo en segundos desde el 1 de enero de 1970, por lo que tenemos que convertirlo.
        // Para ello multiplicamos la fecha que hemos pasado (hora) por 1000 para que nos de la fecha.
        // Y luego cogemos solo la hora y los minutos.
        // Obtenemos el tiempo en milisegundos y lo convertimos a la hora
        Date date = new Date(hora * 1000);
        // Lo mostramos en el TextView pero que solo muestre la hora y los minutos.
        tvTimeRes.setText(date.getHours() + ":" + date.getMinutes());
        //****************************************************************************************************
        // Lo mostramos en el TextView pero que solo muestre los grados Enteros.
        // Le pasamos %d para que muestre un entero y le pasamos el valor de la temperatura.
        tvGradosRes.setText(temperatura);
        tvResHum.setText(humedad);
        tvResLlu.setText(lluvia);
        tvResTiDet.setText(descripcion);

        // Cargar la imagen en el ImageView. Tenemos que usarlo porque no podemos cargar una imagen desde una URL.
        // Metemos las fotos en la carpeta drawable y le pasamos el nombre de la foto.
        int resourceIdFoto = getApplicationContext().getResources()
                .getIdentifier(icono, "drawable", getApplicationContext().getPackageName());
        iv.setImageResource(resourceIdFoto);
    }
}