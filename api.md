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
## getUniversityNews
> Come posso recuperare le notizie relative all'Ateneo Unimol?

Questa API consente di avere le ultime notizie pubblicate sul portale d'ateneo in formato json.
### Nome
`getUniversityNews`
### Metodo
`POST`
### Parametri
|`token`|
|-------|
|*Il token che identifica il client sul server dei webservices*|
### Risposta
`200` *Informazioni estratte correttamente*
### Esempi
```json
{
  "newsList": [
    {
      "date": "22 dicembre 2014",
      "title": "Chiusura sedi universitarie per le festività natalizie",
      "text": "Le strutture dell'Università degli Studi del Molise osserveranno nei mesi di dicembre 2014 e gennaio 2015, i seguenti orari:   PRIMO EDIFICIO POLIFUNZIONALE, Viale Manzoni [...]",
      "link": "http://www.unimol.it/blog/chiusura-sedi-universitarie-per-le-festivita-natalizie-8004/"
    },
    {
      "date": "22 dicembre 2014",
      "title": "Piano Integrato 'Giovani Molise' Avviso Pubblico per attuazione 50 Project Work Innovazione",
      "text": "È stato pubblicato l'Avviso per l'attuazione di n. 50 Project Work Innovazione nell'ambito del Piano Integrato 'Giovani Molise', promosso e realizzato mediante un'azione congiunta tra Regione [...]",
      "link": "http://www.unimol.it/blog/7072-7072/"
    }
  ]
}
```
------------------------
## getDepartmentNews
> Come faccio a recuperare le ultime notizie pubblicate sul sito del dipartimento?

Questa API consente di recuperare le ultime notizie pubblicate sul sito del dipartimento.
### Nome
`getDepartmentNews`
### Metodo
`POST`
### Parametri
`token`|`department`
-------|-----------|
*Il token che identifica il client sul server dei webservices*|*Il dipartimento a cui si è interessati**

######  \*Il parametro `department` deve essere impostato come segue:
Valore|Dipartimento
------|------------
`"agricolturaAmbienteAlimenti"`|*Dipartimento di Agricoltura , Ambiente e Alimenti*
`"bioscienzeTerritorio"`|*Dipartimento di Bioscienze e Territorio*
`"economiaGestioneSocietaIstituzioni"`|*Dipartimento di Economia, Gestione, Società e Istituzioni*
`"giuridico"`|*Dipartimento Giuridico*
`"medicinaScienzeSalute"`|*Dipartimento di Medicina e Scienze della Salute*
`"scienzeUmanisticheSocialiFormazione"`|*Dipartimento di Scienze Umanistiche, Sociali e della Formazione*

### Risposta
`200` *Informazioni estratte correttamente*
### Esempi
```json
{
  "newsList": [
    {
      "date": "5 dicembre 2014",
      "title": "Settimana UNESCO",
      "text": "Nell'ambito della settimana UNESCO di Educazione allo Sviluppo Sostenibile 2014, la prof.ssa Gabriella S. Scippa, nell'ambito del Dipartimento di Bioscienze e Territorio, ha organizzato una iniziativa [...]",
      "link": "http://dipbioter.unimol.it/blog/ciao-5916/"
    },
    {
      "date": "5 dicembre 2014",
      "title": "Esami di novembre 2014 e aprile 2015",
      "text": "Gli studenti che nell'a.a. 2013/2014 erano iscritti al III anno di: Informatica Scienze turistiche Ingegneria edile Scienze biologiche al II anno di Ingegneria civile sono equiparati, [...]",
      "link": "http://dipbioter.unimol.it/blog/pp-5914/"
    }
  ]
}
```
------------------------
## getNewsBoard
> Come recupero gli avvisi o le notizie che afferiscono al singolo corso di studio?

Questa API consente di recuperare le notizie o gli avvisi che appartengono al singolo corso di studi. **Per il momento non tutti i corsi di studi sono supportati**
### Nome
`getNewsBoard`
### Metodo
`POST`
### Parametri
`token`|`course`
-------|--------
*Il token che identifica il client sul server dei webservices*|*Il corso di studi di cui si vogliono estrarre le notizie\**
###### \*Il parametro `course` deve essere impostato come segue:
Valore|Corso di Studi
------|------------
`"informatica"`|*Corso di Studi in Informatica@UNIMOL*
`"scienzeBiologiche"`|*Corso di Studi in Scienze Biologiche@UNIMOL*
### Risposta
`200` *Informazioni estratte correttamente*
### Esempi
```json
{
  "newsList": [
    {
      "date": "18 dicembre 2014",
      "title": "Basi di dati e sistemi informativi",
      "text": "La lezione di Basi di dati e sistemi informativi del prof. Oliveto prevista per oggi è sospesa.",
      "link": "http://dipbioter.unimol.it/blog/basi-di-dati-e-sistemi-informativi-7211/"
    },
    {
      "date": "16 dicembre 2014",
      "title": "Tecnologie di Sviluppo per il Web",
      "text": "La lezione di Tecnologie di Sviluppo per il Web del prof. Fasano prevista per il 18 dicembre p.v. è sospesa.",
      "link": "http://dipbioter.unimol.it/blog/tecnologie-di-sviluppo-per-il-web-7029/"
    }
  ]
}
```
------------------------
## getTaxes
> Come faccio a recuperare la situazione di uno studente per quanto riguarda le tasse?

Questa API consente di ottenere informazioni riguardanti le tasse da pagare o pagate che ciascuno studente ha nella propria area personale sul sistema esse3.
### Nome
`getTaxes`
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
  "taxes": [
    {
      "billId": "617602",
      "bullettinCode": "00000000000000617602",
      "year": "11/12",
      "description": "Tasse di Iscrizione",
      "expiringDate": "20/09/2011",
      "amount": 284.62,
      "statusPayment": "pagato"
    },
    {
      "billId": "584872",
      "bullettinCode": "00000000000000584872",
      "year": "10/11",
      "description": "Tasse di Iscrizione",
      "expiringDate": "01/10/2010",
      "amount": 271.54,
      "statusPayment": "pagato"
    }
  ]
}
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
