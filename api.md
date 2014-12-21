Myunimol WebServices API
==========

In questa pagina sono riportate le API dei servizi web forniti dal software [myunimol-webservices](https://github.com/cbranca/myunimol-webservices).

### Endpoint del servizio
*da decidere*

### Elenco delle API

[`testCredentials`](#testCredentials)

## Specifica API

Seguono le specifiche delle API.

### testCredentials

**Nome:** `testCredentials` **Metodo:** `POST`

**Parametri:**

`username`|`password`|`token`
----------|----------|------
*Lo username dell'utente su esse3*|*La password dell'utente su esse3*|*Il token che identifica il client sul server dei webservices*

**Risposta:**

`200`|`400`|`401`|`500`
-----|-----|-----|-----
*Informazioni estratte correttamente*|*Richiesta mal posta*|*Non autorizzato: le credenziali non sono corrette*|*Errore interno del server*

**Esempio per `200`**
```json
{
  "result": "success",
  "name": "NOME",
  "surname": "COGNOME",
  "studentID": "000001",
  "studentClass": "INFORMATICA 3° ANNO",
  "taxes": " situazione regolare",
  "careerPlan": " modificabile",
  "availableExams": " 0 appelli disponibili",
  "enrolledExams": " 1 prenotazioni",
  "course": "INFORMATICA",
  "department": "Bioscienze e Territorio",
  "coursePath": "comune - PDS0-2008",
  "courseLength": 3,
  "registrationDate": "25/09/2009"
}
```
**Esempio per `400`**
```json
{
  "result": "failure",
  "msg": "You probably mistyped something into your username or password: check them twice next time."
}
```
**Esempio per `401`**
```json
{
  "result": "failure",
  "msg": "Sorry, mummy said I do not have to talk with strangers..."
}
```
**Esempio per `500`**
```json
{
  "result": "failure",
  "msg": "Ooops... Something went wrong while processing your request. Don't blame on us for that!"
}
```
