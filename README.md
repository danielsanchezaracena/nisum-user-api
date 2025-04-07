# Proyecto myuser

Esta es una RESTful API que realiza el registro de usuarios. 

## Requisitos previos

Para poder probar la API, en tu PC debes de tener instalado lo siguiente:

- [Java 21](https://www-oracle-com.translate.goog/java/technologies/javase/jdk21-archive-downloads.html?_x_tr_sl=en&_x_tr_tl=es&_x_tr_hl=es&_x_tr_pto=tc)(Preferiblemente el JDK 21.0.5)
- [Maven](https://maven.apache.org/download.cgi)


## Instalación

1. Clona el repositorio:

   ```bash
   git clone [nisum-user-api](https://github.com/danielsanchezaracena/nisum-user-api)

2. Ve al folder clonado y ejecuta el siguiente comando para iniciar la API:

   ./mvnw spring-boot:run

3. Listo, aqui te paso la info para hacer los request:

   url:localhost:8080/api/user
   HTTP Method:POST  

4. Ten en cuenta lo siguiente:

   El password debe cumplir con los siguientes requerimientos:
   Mínimo 8 caracteres.
   Al menos un digito entre 0 y 9.
   Al menos una letra minúscula.
   Al menos una letra mayúscula.
   Al menos un caracter especial entre @,#,$,%,^,&,+,=.
   Los espacios no son permitidos.

   Para que un email sea valido debe terminar en ".cl" así como lo indicaba el PDF del ejercicio.

   Para acceder al Swagger solo debes ir a la url localhost:8080/swagger-ui.html.

   Asegurate que para el telefono, el parametro enviado sea "countrycode". En el PDF del ejercicio este parámetro está mal escrito.
