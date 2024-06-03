# //INSERIRE TITOLO

# Report
## Indice
- ### [**1 - Introduzione**](#1---introduzione)
  -  [**1.1 - Sviluppatori**](#partecipanti-al-progetto)
  -  [**1.2 - Descrizione Progetto**](#descrizione-progetto)
- ### [**2 - Modello di Dominio**](#2---modello-di-dominio)
  - [**2.1 - Chiarimenti scelte progettuali**](#chiarimenti-scelte-progettuali)
- ### [**3 - Progettazione**](#3---progettazione)
  - [**3.1 - Diagramma delle Classi**](#diagramma-delle-classi)
- ### [**4 - Requisiti Specifici**](#4---requisiti-specifici)
  -  [**4.1 - Requisiti Funzionali**](#requisiti-funzionali)
  - [**4.2 - Requisiti Non Funzionali**](#requisiti-non-funzionali)
- ### [**5 - Specifiche Algebriche**](#5---specifiche-algebriche)
- ### [**6 - Applicazione Argomenti del Corso**](#6---applicazione-argomenti-del-corso)
- ### [**7 - Manuale Utente**](#7---manuale-utente)
  - [**7.1 - Procedura Preliminare**](#procedura-preliminare)
- ### [**8 - Analisi Retrospettiva**](#8---analisi-retrospettiva)
  - [**8.1 - Mad-Sad-Glad**](#mad-sad-glad)
  - [**8.2 - Miglioramenti Proposti**](#miglioramenti-proposti)
- ### [**9 - Conclusioni e Opinioni Finali**](#9---conclusioni-e-opinioni-finali)

## 1 - Introduzione
<hr>

## Partecipanti al progetto
<hr>

Il team di sviluppatori è composto da:
+ **Michele Pontrelli** ([MikiMik88](https://github.com/MikiMik88))
+ **Nicolo' Pacucci** ([Pascoooo](https://github.com/Pascoooo))
+ **Ruggiero Marco Santeramo** ([Apand0](https://github.com/Apand0))
<!-- 
<div style="text-align: left">
    <img src="img/Foto_Marco.jpg" width="130"/>
</div>
-->

## Descrizione Progetto
<hr>

L'avventura testuale //INSERIRE TITOLO è stata implementata come progetto per l'esame "Metodi Avanzati di Programmazione", corso tenutosi dal docente [Pierpaolo Basile](https://github.com/pippokill)
nell'anno accademico 2023/24, presso l'Università degli Studi di Bari "Aldo Moro".

Il progetto è stato sviluppato in linguaggio di programmazione Java, utilizzando il framework Maven per la gestione delle dipendenze e per la compilazione del progetto.

#### [Ritorna all'Indice](#indice)

## 2 - Modello di Dominio
<hr>

- Il seguente diagramma rappresenta il modello di dominio della nostra avventura testuale, realizzata utilizzando il web software [Lucidchart](https://www.lucidchart.com/)
  ![img_Modello_di_dominio](img/Modello_di_dominio.png)

## Chiarimenti scelte progettuali

- Relazioni **Parser**  ⇄ **Partita:**
<hr>
La relazione tra il <b>Parser</b> e la <b>Partita</b> è stata progettata in modo tale che il **Parser** possa ricevere i comandi dall'utente e trasmetterli alla **Partita** per l'esecuzione. Inoltre, il **Parser** può ricevere informazioni sulla situazione di gioco dalla **Partita** e presentarle all'utente.
- Relazioni **Parser**  ⇄ **Comandi:**
<hr>

- Relazioni **Parser**  ⇄ **Agente:**
<hr>

- Relazioni **Partita**  ⇄ **Stanza:**
<hr>

- Relazioni **Stanza**  ⇄ **Agente:**
<hr>

- Relazioni **Agente**  ⇄ **Oggetto/Personaggio:**
<hr>


#### [Ritorna all'Indice](#indice)

## 3 - Progettazione
- Il nostro principale obiettivo di progettazione è stato:
  - Creare un'architettura modulare e scalabile che permetta di aggiungere nuove funzionalità in modo semplice e flessibile.

Questo obiettivo è stato pienamente rispettato dal modello di progettazione adottato, ossia il **Modello Entity-Controller-Boundary (ECB)**.
<h3> Che cos'è il Modello Entity-Controller-Boundary?</h3>
- Il **Modello ECB** è un modello architetturale che permette di separare le entità (Entity) dal controllo (Controller) e dalla presentazione (Boundary) all'interno di un sistema software.
  - **Entity:** rappresenta i dati e le regole di business del sistema.
  - **Controller:** gestisce le interazioni tra le entità e le boundary, implementando la logica di controllo del sistema.
  - **Boundary:** fornisce l'interfaccia utente per interagire con il sistema, presentando i dati e ricevendo input dall'utente.
- Questo modello permette di ottenere una maggiore modularità e flessibilità del sistema, facilitando la manutenzione e l'estensione del codice.

Esempio Pratico:
<table>
<tr>
<th> Entity </th>
<th> Control </th>
<th> Boundary </th>
</tr>
<tr>
<td>

```java
// Esempio di classe Entity
public class User {
    private String username;
    private String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
}

```

</td>
<td>

```java
// Esempio di classe Controller
public class UserController {
private List<User> users = new ArrayList<>();

        public void addUser(String username, String email) {
          User user = new User(username, email);
          users.add(user);
          System.out.println("User added: " + user);
        }
        public List<User> getAllUsers() {
            return users;
        }
}

```
</td>
<td>

```java
//Esempio di classe Boundary
import java.util.Scanner;

  public class UserInterface {
      private UserController userController;
      private Scanner scanner;

    private void addUser() {
      System.out.print("Enter username: ");
      String username = scanner.nextLine();
      System.out.print("Enter email: ");
      String email = scanner.nextLine();
      userController.addUser(username, email);
    }

    private void viewUser() {
      System.out.print("Enter username: ");
      String username = scanner.nextLine();
      User user = userController.getUser(username);
      if (user != null) {
        System.out.println("User found: " + user);
      } else {
        System.out.println("User not found.");
      }
    }
      }

```
</td>
</tr>

</table>

intolo
## Diagramma delle classi
- Il seguente diagramma rappresenta le classi del nostro progetto, realizzato utilizzando il web software [Lucidchart](https://www.lucidchart.com/)



#### [Ritorna all'Indice](#indice)
## 4 - Requisiti Specifici
- In questa sezione verranno elencati i requisiti funzionali e non funzionali del progetto.

### Requisiti Funzionali


### Requisiti Non Funzionali


#### [Ritorna all'Indice](#indice)
## 5 - Specifiche Algebriche
#### [Ritorna all'Indice](#indice)
## 6 - Applicazione Argomenti del Corso
- In questa sezione verrà spiegato come sono stati applicati gli argomenti trattati durante il corso di "Metodi Avanzati di Programmazione" all'interno del progetto.

<ul>
    <li>
        <h2>1) Utilizzo dei file</h2>
        <details open>
            <summary>Visualizza dettagli</summary>
            All'interno del nostro progetto i file sono stati utilizzati sia per l'inizializzazione del gioco, delle stanze e per il salvataggio di quest'ultime. La gestione dei file ci ha permesso di memorizzare e recuperare i dati del gioco in modo persistente, garantendo la continuità dell'esperienza di gioco per gli utenti.
        </details>
    </li>
    <li>
        <h2>2) Utilizzo del Database</h2>
        <details open>
            <summary>Visualizza dettagli</summary>
            Per il nostro progetto, abbiamo utilizzato un database per memorizzare in modo efficiente e strutturato le informazioni relative agli utenti, ai punteggi e alle statistiche di gioco. L'uso del database ci ha permesso di gestire grandi quantità di dati e di eseguire query complesse in modo rapido e sicuro.
        </details>
    </li>
    <li>
        <h2>3) Utilizzo dei Thread</h2>
        <details open>
            <summary>Visualizza dettagli</summary>
            I thread sono stati utilizzati nel progetto per gestire operazioni simultanee, come il caricamento dei dati in background e la gestione delle connessioni di rete. Questo ha migliorato la reattività e le prestazioni complessive dell'applicazione.
        </details>
    </li>
    <li>
        <h2>4) Utilizzo dei Socket</h2>
        <details open>
            <summary>Visualizza dettagli</summary>
            <h3><b>Cosa sono i socket ?</b></h3>
            <p> Come appreso durante il corso, il socket è una astrazione software usata per rappresentare i terminali di una connessione tra due macchine. 
        </details>
    </li>
    <li>
        <h2>5) Utilizzo di Java Swing</h2>
        <details open>
            <summary>Visualizza dettagli</summary>
            Java Swing è stato utilizzato per creare l'interfaccia grafica del nostro progetto. Questo ci ha permesso di costruire una GUI interattiva e user-friendly, migliorando l'esperienza utente grazie a componenti come finestre, pulsanti e pannelli.
        </details>
    </li>
    <li>
    <h2>6) Utilizzo delle Java REST</h2>
    <details open>
        <summary>Visualizza dettagli</summary>
          <h3><b>Cosa è la Java REST?</b></h3>
La <b>Java Representational State Transfer (REST)</b> è un tipo di architettura software per i sistemi distribuiti ed è basata sulla trasmissione di dati tramite protocollo <b>HTTP</b>.

Proprio perché utilizza il protocollo HTTP, l'utilizzo principale della <b>Java REST</b> è quello di andare ad effettuare delle richieste ad un server e riceverà o invierà informazioni a seconda del verbo HTTP utilizzato.

I verbi HTTP principali sono:

- <b>GET</b>: per recuperare informazioni dal server.
- <b>POST</b>: per inviare informazioni al server.
- <b>PUT</b>: per aggiornare informazioni sul server.
- <b>DELETE</b>: per eliminare informazioni dal server.
  <h3><b>Come abbiamo utilizzato la Java REST nel nostro progetto?</b></h3>
  Nel nostro progetto abbiamo utilizzato le <b>Java REST</b> per rendere gli enigmi nelle stanze dinamici, in modo che l'utente possa avere un'esperienza di gioco più coinvolgente e varia, e andando a simulare in locale quello che è lo scambio e la ricezione dati di tipo client-server.
- Enigma della Sfinge per l'entrata nella Piramide:
  Nella stanza iniziale del gioco, ossia il Deserto, la Sfinge pone un enigma all'utente, ossia indovinare una parola segreta di 5 lettere per poter entrare nella Piramide. Questo enigma è stato implementato utilizzando la Java REST, in modo che l'utente possa ricevere una parola diversa ogni partita, rendendo il gioco più interessante e stimolante.

L'API da noi utilizzata per generare le parole casuali è <a href="https://developer.wordnik.com/">Wordnik API</a>.

L'interfaccia dell'API è la seguente:
![img_Random_Words_API](img/immagine_api1.png)
Come è possibile visualizzare dalla foto, questa API è molto ricca di funzionalità, come per esempio la possibilità di richiedere la parola del giorno, sinonimi, antonimi e ben frasi di esempio per oltre 10 milioni di parole.

Tra le varie possibilità offerte da questa API, la funzionalità che ci interessava era quella di ottenere una parola casuale di una determinata lunghezza, dunque abbiamo modificato i parametri della richiesta di default per ottenere una parola di 5 lettere, come mostrato in figura:

![img_Random_Words_API](img/immagine_API3.png)

A questo punto, cliccando il pulsante "Try it out", possiamo testare la nostra richiesta e vedere il risultato:

![img_Random_Words_API](img/immagine_API4.png)

Oltre quindi a fornirci immediatamente il link per effettuare la richiesta, possiamo notare anche la risposta in formato JSON e la sua semplice struttura:
```json
{
  "word": "mbari",
  "id": 1
}
```
Ovviamente il campo che ci interessa è "word", dunque all'interno del nostro codice Java, andremo a recuperare il valore di questo campo per utilizzarlo come parola segreta dell'enigma della Sfinge effettuando il seguente parsing:
```java
// Codice per effettuare il parsing del JSON

```
- Enigma per poter entrare nella stanza del sarcofago:
  All'interno della stanza numero 6 della nostra mappa abbiamo deciso di rendere difficile l'ingresso alla stanza del sarcofago mettendo alla prova l'utente con una serie di domande di varie categorie, dalla cultura generale a domande sui Computer o videogiochi. Per poter superare questa prova l'utente dovrà rispondere correttamente a 3 domande consecutive e, in caso di risposta errata, dovrà ricominciare da capo.

Per implementare questa funzionalità abbiamo utilizzato la Java REST, in particolare andando a cercare un database che permettesse di ottenere domande di varie categorie e difficoltà.

A nostro avviso l'API più adatta per questo scopo è <a href="https://opentdb.com/">Open Trivia Database</a>, un database di domande di trivia open-source che offre una vasta gamma di domande di varie categorie e difficoltà.

L'interfaccia del database è la seguente:
![img_Open_Trivia_Database](img/immagine_trivia1.png)

A questo punto, cliccando il pulsante API, possiamo andare ad analizzare l'interfaccia di nostro maggiore interesse, ossia la richiesta per ottenere domande di varie categorie e difficoltà:

![img_Open_Trivia_Database](img/immagine_trivia2.png)
I parametri possibili per la richiesta sono i seguenti:
- <b>amount</b>: il numero di domande da ottenere, nel nostro caso 3.
- <b>category</b>: l'ID della categoria delle domande, nel nostro  caso la scelta è stata lasciata a "Any Category", indicando che le domande possono essere di qualsiasi categoria.
- <b>difficulty</b>: la difficoltà delle domande, nel nostro caso la scelta è stata "Easy", indicando che le domande saranno di facile difficoltà, vista già la complessità della prova.
- <b>type</b>: il tipo di domande (multiple o boolean), nel nostro caso la scelta è stata "boolean", indicando che la risposta sarà vera o falsa.
- <b>encode</b>: il tipo di encoding della risposta, nel nostro caso la scelta è stata lasciata a "Default", evitando di aggiungere campi inutili alla richiesta.

A questo punto, cliccando il pulsante "Generate API URL", possiamo ottenere il link per effettuare la richiesta e vedere il risultato, che con i campi modificati risulta essere il seguente:

```http request
https://opentdb.com/api.php?amount=3&difficulty=easy&type=boolean
```
Dopo aver compreso il funzionamento dell'API, abbiamo implementato la richiesta all'interno del nostro codice Java, attraverso una semplice richesta <b>GET</b>, come mostrato di seguito:
```java
public static void getQAndA() {
  String urlToRead = "https://opentdb.com/api.php?amount=3&difficulty=easy&type=boolean";
  int maxAttempts = 3;
  for (int attempt = 0; attempt < maxAttempts; attempt++) {
    try {
      URL url = new URL(urlToRead);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");

      StringBuilder result = new StringBuilder();
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
        String line;
        while ((line = reader.readLine()) != null) {
          result.append(line);
        }
      }
      Gson gson = new Gson();
      JsonObject jsonObject = JsonParser.parseString(result.toString()).getAsJsonObject();
      question = jsonObject.getAsJsonArray("results").get(0).getAsJsonObject().get("question").getAsString();
      correctAnswer = jsonObject.getAsJsonArray("results").get(0).getAsJsonObject().get("correct_answer").getAsString();
      displayQuestion(question);
      break;
    } catch (IOException e) {
      System.err.println("Attempt " + (attempt + 1) + " failed. Retrying...");
      if (attempt == maxAttempts - 1) {
        System.err.println("All attempts failed. Please check your network connection.");
        throw new RuntimeException("Error reading from URL", e);
      }
    }
  }
}
```
Come è possibile notare dal codice, la richiesta viene effettuata attraverso un ciclo <b>for</b>, dovuto principalmente al fatto che in fase di testing l'API non rispondeva sempre correttamente, dunque abbiamo deciso di effettuare più tentativi per ottenere le domande e di andare a richiedere, nella singola chiamata, tutte e 3 le domande necessarie per la prova, anzi che effettuare una chiamata per ogni domanda.

Per quanto riguarda la risposta, la struttura del JSON restituito è la seguente:
```json
{
  "response_code": 0,
  "results": [
    {
      "category": "General Knowledge",
      "type": "boolean",
      "difficulty": "easy",
      "question": "The Great Wall of China is visible from the moon.",
      "correct_answer": "False",
      "incorrect_answers": [
        "True"
      ]
    }
  ]
}
```
</details>
</li>
        <h2>7) Utilizzo delle Espressioni Lambda λ</h2>
        <details open>
            <summary>Visualizza dettagli</summary>
            Le espressioni lambda sono state utilizzate per semplificare il codice, specialmente nella gestione degli eventi e nella manipolazione delle collezioni. Questo ha reso il nostro codice più leggibile e conciso.
        </details>
    </li>
</ul>

#### [Ritorna all'Indice](#indice)
## 7 - Manuale Utente
- In questa sezione verrà spiegato come utilizzare l'avventura testuale //INSERIRE TITOLO

### Procedura Preliminare

#### [Ritorna all'Indice](#indice)
## 8 - Analisi Retrospettiva
- In questa sezione andremo ad analizzare i nostri comportamenti durante lo sviluppo dell'avventura testuale
  ed il rispetto delle regole di sviluppo imposte dal team.
  Si riporteranno i punti forza e debolezza emersi durante lo sviluppo del progetto tramite tabelle ed infine
  verranno suggeriti i miglioramenti proposti per evitare errori simili in futuro.
## Modello Mad-Sad-Glad
Di seguito viene riportato il modello Mad-Sad-Glad, che rappresenta i punti di forza e debolezza emersi durante lo sviluppo del progetto.<br>
Dalla tabella si evince che:
<ul>
    <li>Il team è contento di:
        <ul>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
        </ul>
    </li>
    <li>Il team è triste per:
        <ul>
            <li></li>
            <li></li>
        </ul>
    </li>
    <li>Il team è arrabbiato per:
        <ul>
            <li></li>
        </ul>
    </li>
</ul>

## Miglioramenti Proposti
#### [Ritorna all'Indice](#indice)
## 9 - Conclusioni e Opinioni Finali


#### [Ritorna all'Indice](#indice)
