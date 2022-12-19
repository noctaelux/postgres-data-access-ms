# Spring boot - Postgres Data Access

## Requerimientos
- Java v11+
- Maven 3.8.3+ 
- Docker v20+ (Opcional)
- PostgreSQL Server v11+ ([Docker container](https://hub.docker.com/_/postgres))

## Instalación / Ejecución
### Postgres
Para esta prueba de concepto, debemos instalar o tener a nuestra disposición una conexión de PostgreSQL, en caso de no
tenerla, para ejecutar una instancia de postgres mediante docker, ejecutamos el siguiente comando:
```shell
docker run --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=postgres -d postgres
```
De esta manera, de descargará y se instalará una instancia local de `Postgres` ejecutándose en el `puerto 5432`, con 
nombre de usuario y contraseña `postgres`.

### Spring boot
Para este proyecto, se hace uso de las librerías postgresql y hibernate-types-52, las cuales permitirán conferirnos 
acceso a la base de datos y el manejo de algunos tipos especiales de datos.
```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
```
```xml
<dependency>
    <groupId>com.vladmihalcea</groupId>
    <artifactId>hibernate-types-52</artifactId>
    <version>2.21.0</version>
</dependency>
```
Finalmente, compilaremos el proyecto, como de costumbre:
```shell
mvn spring-boot:run
```
La aplicación se encontrará escuchando en el puerto 8080.

Para modificar las configuraciones específicas de la conexión o el comportamiento de la aplicación frente a Postgres, se
sugiere revisar el archivo de configuraciones `application.properties`.

## Descripción
Esta prueba de concepto detalla aspectos básicos de una conexión a base de datos (Postgres) por medio de librerías spring
conocidas (spring-data-jpa), por lo que estaremos ocupando hibernate como nuestro motor principal de conexión a BD.

Esta aplicación se encuentra configurada como una web application, en el package `controller` se encuentra la clase 
`ApiController` que integra una serie de endpoints que permiten realizar varias operaciones sencillas dentro de la base 
de datos (Postgres). La más interesante de estas operaciones es la primera: `/api/v1/postgres-stress/agregar/{datos}`, 
que sirve como un agregador de datos automático que intentará probar el performance de la base, se puede probar variando 
el parámetro _datos_ con valores enteros positivos (100, 1000, 10000, etc...), con valores lo suficientemente grandes 
observaremos cuánto tiempo tardaría en completarse una serie de inserciones.

Por otro lado, tenemos las operaciones comunes de un CRUD (create, read, update, delete) que seguramente encontraremos
dentro de varias implementaciones de distintas base de datos.

En todos los métodos definidos en la clase `ApiController` tenemos dependencia por medio de composición a la interfaz 
`ApiService` la cual es consumida sólo por la clase `ApiServiceImpl`; en esta clase se define toda la lógica necesaria
para interactuar con la base de datos, adicionalmente de otras operaciones que sean requeridas para el funcionamiento de
nuestra aplicación. Finalmente, luego de observar las operaciones CRUD dentro de `ApiServiceImpl`, observamos que todos 
los métodos en esta parte ocupan `ClienteRepository` como dependencia principal, dentro de `ClienteRepository` tendremos
por defecto todas aquellas definiciones particulares que `hibernate` no maneje nativamente.

Dentro de la capa de Excepciones (paquete `exception`) se ha generado una excepción genérica `NoSuchElementFoundException`
para identificar a todos aquellos objetos que no se encuentran almacenados. Para evitar que por defecto se arroje el error
500, se ha anotado la clase con `@ResponseStatus(value = HttpStatus.NOT_FOUND)` para lanzar error 404.

Dentro de los parámetros que se encuentran en el archivo `application.properties`:
```properties
## Exceptions
server.error.include-stacktrace=never
```
Dentro de las configuraciones de `server.error.*` podemos configurar el output que retornará nuestro servicio al momento 
de generarse una excepción. En esta configuración particular, se remueve el mensaje que incluye la traza completa del error.
```properties
## Pool
spring.datasource.hikari.connection-timeout=20000
spring.datasource.hikari.maximum-pool-size=5
```
Indican el tiempo que debe pasar sin conexión el servicio antes de mandar timeout y el tamaño máximo del pool de conexiones
permitidas por la base.

Para ajustar la creación / actualización / borrado de las entidades en base de datos, se puede modificar este parámetro.
```properties
spring.jpa.hibernate.ddl-auto=update
```

### Fuentes
- https://www.baeldung.com/jaxb
- https://howtodoinjava.com/jaxb/write-object-to-xml/
- https://wiki.postgresql.org/wiki/Hibernate_XML_Type
- https://reflectoring.io/spring-boot-exception-handling/