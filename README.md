# Ejercicio práctico

A continuación se detalla la configuración del proyecto.

## Frameworks y dependencias:

* Spring Boot.
* Spring web.
* Spring Dev Tools.
* MySQL.
* Thymeleaf

El proyecto utiliza una BD en MySQL misma que debe estar creada previamente con el nombre de: **"demo"**. La configuración de conexión a la base de datos se encuentra en el archivo: **/resources/application.properties**. Dentro del cual se deben modificar las propiedades:

* spring.datasource.username=USER
* spring.datasource.password=PASS

## Procedimiento de configuración:

* Crear la BD con nombre "demo".
* Descomprimir el archivo .zip.
* Abrir la carpeta del proyecto con el programa Spring Tool Suite.
* Descargar las dependencias mave.
* Ejecutar el proyecto con click derecho -> Run As -> Spring Boot App.
* Acceder desde un navegador con la ruta  http://localhost:8080/
