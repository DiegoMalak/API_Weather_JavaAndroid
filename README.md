# API_Weather_JavaAndroid.
## Aplicación del tiempo con Java y AndroidStudio.

```
Este repositorio contiene dos proyectos, uno está hecho accediendo a los datos al 100% de la API.
Y el otro es usando un Intent para mostrar en otra ventana el resultado y cogiendo los iconos desde
la carpeta drawable.
```

> Consulta el Tiempo.  

Esta es una práctica para la asignatura de Programación Multimedia y Dispositivos Móviles en la que debemos importar una API del tiempo (darksky) 
para mostrar el tiempo. Hay dos versiones de la práctica: una en la que se obtienen todos los datos de la API y otra en la que se utiliza un Intent 
para pasar la información entre actividades y se muestran los iconos desde el directorio drawable.

La aplicación consta de dos actividades: MainActivity y TimeActivity. En MainActivity se solicita al usuario la latitud y longitud del lugar del 
que quiere consultar el tiempo. Al hacer clic en el botón "Consultar tiempo" se abre TimeActivity, donde se realiza una petición a la API de Dark 
Sky para obtener información del tiempo actual.

Se utiliza Glide para mostrar la imagen del icono correspondiente. En la versión que utiliza un Intent, se obtienen los iconos del directorio drawable.

Aquí está la URL de ejemplo para realizar la petición: 
https://api.darksky.net/forecast/11ce4328111023379e0fdc9d28c24a02/latitud,longitud?exclude=minutely,hourly,daily,alerts,flags&lang=es

## Requisitos.

Para utilizar este proyecto, se necesitan los siguientes requisitos:

- Android Studio instalado en el equipo.
- Una clave de API de OpenWeatherMap. Puedes obtener una clave gratuita en su sitio web oficial.

## Instalación

1. Clona este repositorio en tu equipo local.
2. Abre el proyecto en Android Studio.
3. Crea un archivo "gradle.properties" en la raíz del proyecto y agrega la siguiente línea con tu clave de API: `OPEN_WEATHER_MAP_API_KEY="tu_clave_de_api"`
4. Ejecuta el proyecto en el emulador o en un dispositivo Android conectado.

## Contribuir

Si deseas contribuir a este proyecto, por favor sigue los siguientes pasos:

1. Haz un fork del repositorio.
2. Crea una rama con tus cambios: `git checkout -b mi-rama`
3. Realiza los cambios y haz un commit: `git commit -m "Descripción de los cambios"`
4. Haz push a la rama: `git push origin mi-rama`
5. Abre un Pull Request en el repositorio original.

## Licencia

Este proyecto está bajo la licencia MIT. Puedes ver el archivo de licencia para más detalles.

