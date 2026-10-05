# Labb 1

Java-webbapplikation byggd med Maven och körd med Tomcat 9 och PostgreSQL 16 i Docker Compose.

## Krav

Du behöver ha installerat:

* Java 21
* Maven
* Docker, med Docker Desktop igång

## Starta applikationen

WAR-filen byggs lokalt. Imagen kopierar bara `target/labb1.war` och kör inte Maven.

Kör från projektets rot:

```text
mvn clean package
docker compose up -d --build
```

Öppna sedan:

```text
http://localhost:8080/labb1/
```

Appen ansluter till Postgres på värdnamnet `db`. Det namnet finns i Compose-nätverket, så `docker run` ensamt räcker inte.
