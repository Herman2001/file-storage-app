# File Storage API
TEST

Ett Spring Boot-baserat API som fungerar som ett enklare Google Drive / Dropbox.  
Bygger på controller-service-repository-arkitektur och använder JWT för autentisering.

## Funktioner

- Skapa ny mapp
- Ladda upp fil till specifik mapp
- Ladda ned fil
- Ta bort fil
- Registrera användare
- Logga in användare (JWT)
- Alla mappar och filer kopplas till användare
- Användare kan inte se andra användares filer eller mappar
- Säkerhet med Spring Security + BCrypt

## Teknologier

- Spring Boot
- PostgreSQL
- Spring Data JPA
- Spring Security med JWT
- Gradle

