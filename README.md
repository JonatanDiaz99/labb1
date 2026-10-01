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

## Databas

`database/init.sql` skapar schemat och exempelvarorna. Postgres kör skriptet bara första gången volymen `postgres_data` skapas.

För att köra om skriptet, ta bort volymen och starta igen. Det raderar databasen:

```text
docker compose down -v
docker compose up -d --build
```

## Teknik

Projektet använder:

* Java 21
* Maven
* Javax
* JSP och JSTL
* Tomcat 9
* PostgreSQL 16
* Docker Compose

## Struktur och ansvar

Produktlistan är inkopplad. Övriga labbmoment är inte klara.

`ItemServlet` anropar `ItemFacade`, som hämtar produkter via `Item` och `ItemDB`. JSP visar data och anropar inte databasen. Servleten innehåller ingen SQL.

- `src/main/java/se/kth/labb/ui/controller`: servlets som tar emot HTTP-anrop.
- `src/main/java/se/kth/labb/ui/dto`: enkla objekt för data som ska visas.
- `src/main/java/se/kth/labb/bo`: affärsobjekt och affärslogik.
- `src/main/java/se/kth/labb/db`: SQL, JDBC och anslutningshantering.
- `src/main/webapp/WEB-INF/views`: JSP-vyer, endast åtkomliga genom serverns vidarebefordran.
- `src/main/webapp/css`: stilmallar.
- `src/main/resources`: resurser och konfiguration; lägg inte in lösenord i Git.
- `src/test/java/se/kth/labb`: framtida tester.
- `database/init.sql`: schema och exempeldata.
- `docs/klassdiagram.puml`: klassdiagram i PlantUML-format; uppdatera när funktioner läggs till.

Filerna `.gitkeep` gör att tomma mappar följer med i Git.

### Öppna i IntelliJ

Öppna projektets `pom.xml` som projekt och välj Java 21 som Project SDK.
Synkronisera Maven-projektet. Java-koden ligger under `src/main/java`,
medan JSP och CSS ligger under `src/main/webapp`.
IntelliJs Maven-panel kan användas för att köra Lifecycle → package
även om kommandot `mvn` inte finns installerat i terminalen.

### Kvar att göra

Inloggning, varukorg, ordrar i en transaktion, användaradministration med roller, administration av varor och kategorier, packning av ordrar, automatiska tester och ett uppdaterat klassdiagram.

Vid beställningar ska affärslagret samordna en transaktion på samma JDBC-anslutning för alla databasoperationer som måste lyckas tillsammans. MVC kombineras med lagren: servleten är Controller, JSP är View och modellens affärsobjekt och logik finns i BO-lagret.

### Manuell kontroll efter start

- `/labb1/` visar startsidan med länk till produkterna.
- `/labb1/items` visar produkter från databasen.
- `/labb1/hello` fungerar fortfarande som alternativ adress till produktvyn.
- `/labb1/WEB-INF/views/items.jsp` ska inte gå att öppna direkt (404).
