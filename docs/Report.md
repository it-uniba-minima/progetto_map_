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

- In questa sezione verranno elencate le specifiche algebriche del progetto.


#### Specifica algebrica Mappa

<table>
    <thead>
        <tr>
            <th>Componente</th>
            <th>Descrizione</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td><strong>Tipo di Dati</strong></td>
            <td><code>Map</code> rappresenta una mappa</td>
        </tr>
        <tr>
            <td colspan="2"><strong>Metodi (Operazioni)</strong></td>
        </tr>
        <tr>
            <td><code>put(key, value)</code></td>
            <td><code>Map × Key × Value → Map</code> - Aggiunge una coppia chiave-valore o aggiorna il valore esistente</td>
        </tr>
        <tr>
            <td><code>get(key)</code></td>
            <td><code>Map × Key → Value</code> - Restituisce il valore associato alla chiave specificata</td>
          </tr>
            <tr>
                <td><code>remove(key)</code></td>
                <td><code>Map × Key → Map</code> - Rimuove la coppia chiave-valore specificata</td>
            </tr> 
            <tr>
                <td><code>containsKey(key)</code></td>
                <td><code>Map × Key → Boolean</code> - Restituisce <code>true</code> se la chiave specificata è presente nella mappa</td>
            </tr> 
            <tr>
                <td><code>containsValue(value)</code></td>
                <td><code>Map × Value → Boolean</code> - Restituisce <code>true</code> se il valore specificato è presente nella mappa</td> 
            </tr> 
            <tr>
                <td><code>size()</code></td>
                <td><code>Map → Integer</code> - Restituisce il numero di coppie chiave-valore presenti nella mappa</td>  
            </tr>
            <tr>
                <td><code>isEmpty()</code></td>
                <td><code>Map → Boolean</code> - Restituisce <code>true</code> se la mappa è vuota</td>
            </tr> 
            <tr>
                <td><code>clear()</code></td>
                <td><code>Map → Map</code> - Rimuove tutte le coppie chiave-valore dalla mappa</td> 
            </tr>
            <tr>
                <td><code>keySet()</code></td>
                <td><code>Map → Set</code> - Restituisce un insieme di tutte le chiavi presenti nella mappa</td>
            </tr> 
            <tr>
                <td><code>values()</code></td>
                <td><code>Map → Collection</code> - Restituisce una collezione di tutti i valori presenti nella mappa</td>
            </tr>
            <tr>
                <td><code>entrySet()</code></td>
                <td><code>Map → Set</code> - Restituisce un insieme di tutte le coppie chiave-valore presenti nella mappa</td>
            </tr>
    </tbody>
</table>


#### [Ritorna all'Indice](#indice)


## 6 - Applicazione Argomenti del Corso

- In questa sezione verrà spiegato come il progetto //INSERIRE TITOLO si collega agli argomenti trattati durante il corso di "Metodi Avanzati di Programmazione".


[Applicazione Argomenti del Corso](Applicazione%20Argomenti%20del%20corso.md)


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
