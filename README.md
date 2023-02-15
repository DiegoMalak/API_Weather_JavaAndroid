# API_Weather_JavaAndroid.
## Aplicación del tiempo con Java y AndroidStudio.

```
Este repositorio contiene dos proyectos, uno está hecho accediendo a los 
datos al 100% de la API y extrayendo el icono desde una URL de la API. 
Y el otro es usando un Intent para mostrar en otra ventana el resultado 
y cogiendo los iconos desde la carpeta drawable.
```

- Consulta el Tiempo.  

Este repositorio contiene una práctica para la asignatura de Programación Multimedia y 
Dispositivos Móviles. La aplicación utiliza la API del tiempo de Dark Sky para mostrar el clima actual.  

Hay dos versiones de la práctica:  

Una que obtiene todos los datos de la API y muestra el icono obtenido desde una URL en un ImageView,
mientras que el resto de los datos se muestran en TextViews.  
Otra que también obtiene todos los datos de la API, pero el icono se obtiene desde el directorio 
drawable que está dentro de res.  
La aplicación consta de dos actividades: MainActivity y TimeActivity. En MainActivity, se realiza  
una petición a la API de Dark Sky para obtener información del tiempo actual, y se pasan los datos 
obtenidos a TimeActivity para ser mostrados en los TextViews.  

## Requisitos.

Para utilizar este proyecto, se necesitan los siguientes requisitos:

- Android Studio instalado en el equipo.
- Una clave de API de OpenWeatherMap. Puedes obtener una clave gratuita en su sitio web oficial.

## Instalación

1. Clona este repositorio en tu equipo local.
2. Abre el proyecto en Android Studio.
3. Crea un archivo "gradle.properties" en la raíz del proyecto y agrega la 
siguiente línea con tu clave de API: `OPEN_WEATHER_MAP_API_KEY="tu_clave_de_api"`
4. Ejecuta el proyecto en el emulador o en un dispositivo Android conectado.
