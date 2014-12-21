Myunimol WebServices API
==========

In questa pagina sono riportate le API dei servizi web forniti dal software [myunimol-webservices](https://github.com/cbranca/myunimol-webservices).

# Endpoint del servizio

L'applicativo che fornisce i webservices dovrebbe essere installato sul server di dipartimento al seguente URL:

**`https://myunimol-api.unimol.it`**

**Ovviamente non è detto che ciò accada.**

# Elenco delle API

* Generali
  * [`version`](#version)
* Login
  * [`testCredentials`](#testcredentials)
* Appelli
  * [`getExamSessions`](#getexamsessions)
  * [`getEnrolledExams`](#getenrolledexams)
* Libretto
  * [`getRecordBook`](#getrecordbook)
  * [`getRecordBookExam`](#getrecordbookexam)
* Rubrica
  * [`getAddressBook`](#getaddressbook)
  * [`searchContacts`](#searchcontacts)
* News
  * [`getUniversityNews`](#getuniversitynews)
  * [`getDepartmentNews`](#getdepartmentnews)
* Tasse
  * [`getTaxes`](#gettaxes)

# Specifica API

Seguono le specifiche delle API.

## testCredentials
Questa API consente di testare le credenziali fornite dallo studente direttamente sul portale esse3. Restituisce inoltre le informazioni base necessarie alla realizzazione di una pagina 'home'.
### Nome
`testCredentials`
### Metodo
`POST`

### Parametri

`username`|`password`|`token`
----------|----------|------
*Lo username dell'utente su esse3*|*La password dell'utente su esse3*|*Il token che identifica il client sul server dei webservices*

### Risposta

`200`
*Informazioni estratte correttamente*

### Esempi
##### Esempio per `200`
```json
{
  "result": "success",
  "name": "NOME",
  "surname": "COGNOME",
  "studentID": "000001",
  "studentClass": "INFORMATICA 3° ANNO",
  "taxes": " situazione regolare",
  "careerPlan": " modificabile",
  "availableExams": 0,
  "enrolledExams": 1,
  "course": "INFORMATICA",
  "department": "Bioscienze e Territorio",
  "coursePath": "comune - PDS0-2008",
  "courseLength": 3,
  "registrationDate": "25/09/2009"
}
```


# Risposte comuni
Di seguito alcune delle risposte che possono essere data da uno dei servizi web
## Richiesta non ben formata o malposta
Quando si inoltra una richiesta che non rispetta la specifica di un servizio web si ottiene una risposta con codice `400` *Bad request*. La risposta è esemplificata come segue:
##### Esempio per `400`
```json
{
  "result": "failure",
  "msg": "You probably mistyped something into your username or password: check them twice next time."
}
```
## Token non presente o invalido
Quando il token che viene passato a ciascun web service non è autorizzato a consumare il servizio viere restituita una risposta con codice `401` *Unauthorized*. La risposta è esemplificata come segue:
##### Esempio per `401`
```json
{
  "result": "failure",
  "msg": "Sorry, mummy said I do not have to talk with strangers..."
}
```
## Errore generico del servizio web
Quando si verifica un errore non gestito all'interno del servizio web e questo non è in grado di fornire una risposta al client viene ritornata una risposta con codice `500` *Internal server error*. La risposta è esemplificata come segue:
##### Esempio per `500`
```json
{
  "result": "failure",
  "msg": "Ooops... Something went wrong while processing your request. Don't blame on us for that!"
}
```
