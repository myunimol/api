Myunimol WebServices API
==========

In questa pagina sono riportate le API dei servizi web forniti dal software [myunimol-webservices](https://github.com/cbranca/myunimol-webservices).

### Endpoint del servizio
*da decidere*

### Elenco delle API

[`testCredentials`](#testCredentials)

## Specifica API

Seguono le specifiche delle API.

<p name="testCredentials" />
### testCredentials

nome: `testCredentials` metodo: `POST`

parametri: `username`, `password`, `token`

risposta: `200`

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
