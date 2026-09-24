# Labb 1

Java-webbapplikation byggd med Maven och körd med Tomcat 9 i Docker.

## Krav

Du behöver ha installerat:

* Java 21
* Maven
* Docker

## Bygg projektet

Kör från projektets rot:

```text
mvn clean package
```

## Bygg Docker-image

```text
docker build -t labb1 .
```

## Starta applikationen

```text
docker run -p 8080:8080 labb1
```

Öppna sedan:

```text
http://localhost:8080/labb1/hello
```

## Teknik

Projektet använder:

* Java 21
* Maven
* Javax
* JSP
* Tomcat 9
* Docker

