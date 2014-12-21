Myunimol WebServices API
==========

In questa pagina sono riportate le API dei servizi web forniti dal software [myunimol-webservices](https://github.com/cbranca/myunimol-webservices).

# Indice
1. [End point del servizio](#endpoint-del-servizio)
2. [Specifica API](#specifica-api)
  1. Generali
    * [`version`](#version)
  2. Login
    * [`testCredentials`](#testcredentials)
  3. Appelli
    * [`getExamSessions`](#getexamsessions)
    * [`getEnrolledExams`](#getenrolledexams)
  4. Libretto
    * [`getRecordBook`](#getrecordbook)
    * [`getRecordBookExam`](#getrecordbookexam)
  5. Rubrica
    * [`getAddressBook`](#getaddressbook)
    * [`searchContacts`](#searchcontacts)
  6. News
    * [`getUniversityNews`](#getuniversitynews)
    * [`getDepartmentNews`](#getdepartmentnews)
    * [`getNewsBoard`](#getnewsboard)
  7. Tasse
    * [`getTaxes`](#gettaxes)
3. [Risposte comuni](#risposte-comuni)

# Endpoint del servizio

L'applicativo che fornisce i webservices dovrebbe essere installato sul server di dipartimento al seguente URL:

**`https://myunimol-api.unimol.it`**

**Ovviamente non è detto che ciò accada.**

# Specifica API

Seguono le specifiche delle API.
## Generali
In questa sezione sono riportate le API che forniscono servizi generici che riguradano l'applicativo dei webservice in generale.
### version
> Qual è la versione di API che gira sul server dei web services al quale desidero connettermi?

Questo servizio web consente di ottenere un numero che rappresenta la versione di API implementata dal server.
#### Nome
`getVersion`
#### Metodo
`POST`
#### Parametri
|`token`|
|-------|
|*Il token che identifica il client sul server dei webservices* |
#### Risposta

`200`
*Informazioni estratte correttamente*

#### Esempi
##### Esempio per `200`
```json
{
  "APIVersion": "1.0"
}
```
-----------------
### testCredentials
Questa API consente di testare le credenziali fornite dallo studente direttamente sul portale esse3. Restituisce inoltre le informazioni base necessarie alla realizzazione di una pagina 'home'.
#### Nome
`testCredentials`
#### Metodo
`POST`

#### Parametri

`username`|`password`|`token`
----------|----------|------
*Lo username dell'utente su esse3*|*La password dell'utente su esse3*|*Il token che identifica il client sul server dei webservices*

#### Risposta

`200`
*Informazioni estratte correttamente*

#### Esempi
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
----------------
## getExamSessions
> Che appelli sono disponibili attualmente?

Questa API serve a recuperare le sessioni d'esame (appelli) disponibili al momento dell'interrogazione. Gli appelli vengono reperiti dalla piattaforma esse3.
### Nome
`getExamSessions`
### Metodo
`POST`
### Parametri
`username`|`password`|`token`
----------|----------|------
*Lo username dell'utente su esse3*|*La password dell'utente su esse3*|*Il token che identifica il client sul server dei webservices*
### Risposta
`200` *Informazioni estratte correttamente*
### Esempi
```json
{
  "result": "success",
  "exams": [
    {
      "name": "Filologia romanza",
      "cfu": 6,
      "professor": "Roberto De Benedictis",
      "date": "12/12/2015",
      "expiringDate": "1/12/2015",
      "room": "12A",
      "notes": "Scritto ore 10 orale giorno dopo"
    },
    {
      "name": "Scienze delle scienze",
      "cfu": 12,
      "professor": "Andrea De Robertis",
      "date": "12/12/2015",
      "expiringDate": "1/12/2015",
      "room": "Del patrono",
      "notes": ""
    }
  ]
}
```
------------------------
## getEnrolledExams
> A quali appelli è prenotato uno studente?

Questa API serve a recuperare la lista degli appelli d'esame ai quali uno studente è prenotato.
### Nome
`getEnrolledExams`
### Metodo
`POST`
### Parametri
`username`|`password`|`token`
----------|----------|------
*Lo username dell'utente su esse3*|*La password dell'utente su esse3*|*Il token che identifica il client sul server dei webservices*
### Risposta
`200` *Informazioni estratte correttamente*
### Esempi
```json
{
  "result": "success",
  "exams": [
    {
      "name": "Filologia romanza",
      "cfu": 6,
      "professor": "Roberto De Benedictis",
      "date": "12/12/2015",
      "expiringDate": "1/12/2015",
      "room": "12A",
      "enrollementPosition": 1,
      "enrolled": 10,
      "notes": "Scritto ore 10 orale giorno dopo"
    },
    {
      "name": "Scienze delle scienze",
      "cfu": 12,
      "professor": "Andrea De Robertis",
      "date": "12/12/2015",
      "expiringDate": "1/12/2015",
      "room": "Del patrono",
      "enrollementPosition": 6,
      "enrolled": 12,
      "notes": ""
    }
  ]
}
```
------------------------
## getRecordBook
> Quali sono gli esami che uno studente ha sostenuto e quali sono i relativi voti ricevuti???

Questa API consente di recuperare la lista degli esami sostenuti da uno studente. Fornisce inoltre delle informazioni su ciascun esame e sulla media (aritmetica e ponderata) relativa all'intera carriera.
### Nome
`getRecordBook`
### Metodo
`POST`
### Parametri
`username`|`password`|`token`
----------|----------|------
*Lo username dell'utente su esse3*|*La password dell'utente su esse3*|*Il token che identifica il client sul server dei webservices*
### Risposta
`200` *Informazioni estratte correttamente*
### Esempi
```json
{
  "exams": [
    {
      "name": "411008 - Lingua Inglese",
      "cfu": "3",
      "vote": "IDO",
      "date": "16/06/2010",
      "year": "2009/2010",
      "id": "1016249"
    },
    {
      "name": "411898 - Lingua inglese B1",
      "cfu": "3",
      "vote": "IDO",
      "date": "18/02/2010",
      "year": "2009/2010",
      "id": "1174445"
    },
    {
      "name": "411006 - Lingua Italiana I",
      "cfu": "4",
      "vote": "28",
      "date": "08/09/2010",
      "year": "2009/2010",
      "id": "1016248"
    },
    {
      "name": "411007 - Lingua Italiana II (Idoneità)",
      "cfu": "2",
      "vote": "IDO",
      "date": "07/07/2010",
      "year": "2009/2010",
      "id": "1016247"
    },
    {
      "name": "411005 - Linguaggi di programmazione e laboratorio",
      "cfu": "11",
      "vote": "30L",
      "date": "18/06/2010",
      "year": "2009/2010",
      "id": "1016246"
    }
  ],
  "average": 27.59,
  "weightedAverage": 27.53
}
```
------------------------
## getRecordBookExam
> Quali sono i dettagli di un singolo esame appartenente al piano di studi di uno studente?

Questa API consente di recuperare direttamente dal sistema esse3 i dettagli relativi a una attività didattica appartenente al piano di studi di uno studente.
### Nome
`getRecordBookExam`
### Metodo
`POST`
### Parametri
`username`|`password`|`token`|`id`
----------|----------|-------|----
*Lo username dell'utente su esse3*|*La password dell'utente su esse3*|*Il token che identifica il client sul server dei webservices*|*L'id relativo alla attività didattica**
###### *Tale id viene restituito dalla lista degli esami ottenibile mediante l'API [`getRecordBook`](#getrecordbook).
### Risposta
`200` *Informazioni estratte correttamente*
### Esempi
```json
{
  "name": "Attività Didattica: Lingua Inglese [411008]",
  "cfu": 3,
  "vote": "IDO",
  "date": "16/06/2010",
  "year": "1",
  "id": "1016249",
  "details": [
    {
      "name": "Inglese scientifico - [4110082]",
      "cfu": 3,
      "hours": 24,
      "area": "L-LIN/12 - LINGUA E TRADUZIONE - LINGUA INGLESE"
    }
  ]
}
```
------------------------
## getAddressBook
> Come ottengo la lista di tutti i contatti inerenti l'Università degli Studi del Molise?

Questa API consente di recuperare tutti i contatti che afferiscono all'Unimol.
### Nome
`getAddressBook`
### Metodo
`POST`
### Parametri
|`token`|
--------|
|*Il token che identifica il client sul server dei webservices* |
### Risposta
`200` *Informazioni estratte correttamente*
### Esempi
```json
{
  "result": "success",
  "contacts": [
    {
      "fullname": "Tizio Caio",
      "role": "Ricercatore",
      "building": "Dipartimento Giuridico c/o I Edificio Polifunzionale - Viale Manzoni - 86100 - Campobasso",
      "internalTelephone": "0099",
      "externalTelephone": "+39 0874 400 099",
      "email": "tiziocaio@unimol.it"
    },
    {
      "fullname": "Sempronio Rocco",
      "role": "Personale Tecnico Amministrativo",
      "building": "Settore Prevenzione e Protezione c/o II Edificio Polifunzionale - Via F. De Sanctis - 86100 - Campobasso",
      "internalTelephone": "0098 / 0097",
      "externalTelephone": "+39 0874 404 098 / +39 0874 404 097",
      "email": "semproniorocco@unimol.it"
    }
  ]
}
```
------------------------
## searchContacts
> Come faccio a ricercare all'interno della rubrica dell'Unimol?

Questa API consente di reperire un contatto particolare dall'elenco dei contatti afferenti all'Unimol tramite una funzionalità di ricerca.
### Nome
`searchContacts`
### Metodo
`POST`
### Parametri
`token`|`search`
-------|--------
*Il token che identifica il client sul server dei webservices*|*La stringa per effettuare la ricerca**
###### * Attraverso tale stringa si effettua una ricerca sia sul nome che sul cognome.

### Risposta
`200` *Informazioni estratte correttamente*
### Esempi
```json
{
  "result": "success",
  "contacts": [
    {
      "fullname": "Fasano Fausto",
      "role": "Ricercatore",
      "building": "Dipartimento di Bioscienze e Territorio - sede di Pesche c/o Dipartimento di Bioscienze e Territorio - Contrada Fonte Lappone - 86090 - Pesche",
      "internalTelephone": "4126",
      "externalTelephone": "+39 0874 404 126",
      "email": "fausto.fasano@unimol.it"
    }
  ]
}
```
------------------------
## nome
Descrizione
### Nome
`nome`
### Metodo
`POST`
### Parametri
`username`|`password`|`token`
----------|----------|------
*Lo username dell'utente su esse3*|*La password dell'utente su esse3*|*Il token che identifica il client sul server dei webservices*
### Risposta
`200` *Informazioni estratte correttamente*
### Esempi
```json

```
------------------------
## nome
Descrizione
### Nome
`nome`
### Metodo
`POST`
### Parametri
`username`|`password`|`token`
----------|----------|------
*Lo username dell'utente su esse3*|*La password dell'utente su esse3*|*Il token che identifica il client sul server dei webservices*
### Risposta
`200` *Informazioni estratte correttamente*
### Esempi
```json

```
------------------------
## nome
Descrizione
### Nome
`nome`
### Metodo
`POST`
### Parametri
`username`|`password`|`token`
----------|----------|------
*Lo username dell'utente su esse3*|*La password dell'utente su esse3*|*Il token che identifica il client sul server dei webservices*
### Risposta
`200` *Informazioni estratte correttamente*
### Esempi
```json

```
------------------------
## nome
Descrizione
### Nome
`nome`
### Metodo
`POST`
### Parametri
`username`|`password`|`token`
----------|----------|------
*Lo username dell'utente su esse3*|*La password dell'utente su esse3*|*Il token che identifica il client sul server dei webservices*
### Risposta
`200` *Informazioni estratte correttamente*
### Esempi
```json

```
------------------------
## nome
Descrizione
### Nome
`nome`
### Metodo
`POST`
### Parametri
`username`|`password`|`token`
----------|----------|------
*Lo username dell'utente su esse3*|*La password dell'utente su esse3*|*Il token che identifica il client sul server dei webservices*
### Risposta
`200` *Informazioni estratte correttamente*
### Esempi
```json

```
------------------------
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
