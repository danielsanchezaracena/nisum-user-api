# Proyecto myuser

Esta es una RESTful API que realiza el registro de usuarios. 

## Requisitos previos

Para poder probar la API, en tu PC debes de tener instalado lo siguiente:

- [Java 21](https://www-oracle-com.translate.goog/java/technologies/javase/jdk21-archive-downloads.html?_x_tr_sl=en&_x_tr_tl=es&_x_tr_hl=es&_x_tr_pto=tc) (Preferiblemente el JDK 21.0.5)
- [Maven](https://maven.apache.org/download.cgi)


## Instalación

1. Clona el repositorio:

   ```bash
   git clone https://github.com/danielsanchezaracena/nisum-user-api

2. Ve al folder clonado y ejecuta el siguiente comando para iniciar la API:

   ```bash
   ./mvnw spring-boot:run

3. Listo, aqui te paso la info para hacer los request:

-   url:localhost:8080/api/user
-   HTTP Method:POST  

4. Ten en cuenta lo siguiente:

   - Para que un email sea válido debe terminar en ".cl" así como lo indica el PDF del ejercicio.

   - Para acceder al Swagger solo debes ir a la url localhost:8080/swagger-ui.html.

   - Asegurate que para el teléfono, el parámetro enviado sea "countrycode". En el PDF del ejercicio este parámetro está mal escrito.

   El password debe cumplir con los siguientes requerimientos:
   - Mínimo 8 caracteres.
   - Al menos un dígito entre 0 y 9.
   - Al menos una letra minúscula.
   - Al menos una letra mayúscula.
   - Al menos un caracter especial entre @,#,$,%,^,&,+,=.
   - Los espacios no son permitidos.

   - La ruta del script de la base de datos es nisum-user-api/schema.sql 

## Diagrama de la solución

<img width="500" alt="image" src="https://github.com/user-attachments/assets/95b10557-eafa-4448-89a7-81e7389474b1">

