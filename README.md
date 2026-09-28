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
http://localhost:8080/labb1/
```

## Teknik

Projektet använder:

* Java 21
* Maven
* Javax
* JSP
* Tomcat 9
* Docker


## Startstruktur och ansvar

Detta är en grundstruktur, inte en färdig implementation av labben.
Servlet → JSP är inkopplat. Affärs- och databasklasserna är kommenterade
klasskelett och anropas ännu inte. Ingen JDBC-drivrutin eller databas är vald.

- `src/main/java/se/kth/labb/ui/controller`: servlets som tar emot HTTP-anrop.
- `src/main/java/se/kth/labb/ui/dto`: enkla objekt för data som ska visas.
- `src/main/java/se/kth/labb/bo`: affärsobjekt och affärslogik.
- `src/main/java/se/kth/labb/db`: SQL, JDBC och anslutningshantering.
- `src/main/webapp/WEB-INF/views`: JSP-vyer, endast åtkomliga genom serverns vidarebefordran.
- `src/main/webapp/css`: stilmallar.
- `src/main/resources`: resurser och konfiguration; lägg inte in lösenord i Git.
- `src/test/java/se/kth/labb`: framtida tester.
- `docs/klassdiagram.puml`: start till klassdiagram i PlantUML-format; uppdatera vid implementation.

Filerna `.gitkeep` gör att de tomma mapparna följer med i Git.

### Öppna i IntelliJ

Öppna projektets `pom.xml` som projekt och välj Java 21 som Project SDK.
Synkronisera Maven-projektet. Java-koden ligger under `src/main/java`,
medan JSP och CSS ligger under `src/main/webapp`.
IntelliJs Maven-panel kan användas för att köra Lifecycle → package
även om kommandot `mvn` inte finns installerat i terminalen.

### Nästa steg

Implementera produktlistan i ordningen Item → ItemDB → ItemFacade → ItemServlet → items.jsp.
Välj först databas och lägg dess JDBC-drivrutin i `pom.xml`.
Servleten ska anropa affärslagret och ge vyn visningsdata, utan egen SQL.
JSP visar data och ska inte anropa databasen.

Vid beställningar ska affärslagret samordna en transaktion på samma
JDBC-anslutning för alla databasoperationer som måste lyckas tillsammans.
MVC kombineras med lagren: servleten är Controller, JSP är View och
modellens affärsobjekt och logik finns i BO-lagret.

### Manuell kontroll efter start

- `/labb1/` visar startsidan med länk till produkterna.
- `/labb1/items` visar den förberedda produktvyn.
- `/labb1/hello` fungerar fortfarande som alternativ adress till produktvyn.
- `/labb1/WEB-INF/views/items.jsp` ska inte gå att öppna direkt (404).

Login, varukorg, databaskoppling och automatiska tester återstår.
