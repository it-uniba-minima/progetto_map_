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
            <h3><b>Cosa sono i file?</b></h3>
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
            In Java si usa un socket per creare la connessione ad un’altra macchina attraverso la rete. In particolare, per stabilire una connessione fra due computer occorrerà disporre di un socket su ogni macchina.Quest'ultimo non è che una astrazione software usata per
            rappresentare i terminali di una connessione tra due macchine.

In particolare, Java utilizza, per la comunicazione in rete, il <b>modello a stream</b>.

Un socket può mantenere due tipi di stream: uno di input ed uno di output. Dal punto di vista software, ciò che avviene è che un processo invia dei dati ad un altro processo attraverso la rete, scrivendo sullo stream di output associato ad un socket. Un altro processo, accede ai dati scritti in precedenza leggendo dallo stream di input del socket stesso.

Ci sono due classi socket basate su stream:

- ServerSocket che il server usa per ascoltare una
  richiesta di connessione
- Socket usata dal client per inizializzare la connessione

Una volta che un client richiede una connessione
socket, il ServerSocket restituisce  un Socket corrispondente
attraverso il quale la comunicazione può avvenire
dal lato server.

Generalmente quando si crea un Serversocket, si specifica solo la porta del server a cui ci si vuole connettere, mentre quando si crea un Clientsocket si specifica, oltre alla porta del server a cui ci si vuole connettere, anche l'indirizzo IP del server.

Nel nostro caso, essendo il server e il client sulla stessa macchina, non è stato necessario specificare l'indirizzo IP.
<h3><b>Come abbiamo utilizzato i Socket nel nostro progetto?</b></h3>
Nel menu  del nostro gioco è presente un bottone "", che apre, al click, una pagina web contenente le informazioni principali del gioco e le istruzioni per giocare.

Questo bottone è stato implementato utilizzando i socket sulla porta 8080, in particolare il metodo <b>openWebpage</b> che apre una connessione con il browser predefinito del sistema e carica la pagina web desiderata.
<h4><b>Che informazioni contiene il sito web?</b></h4>
Ecco come appare il sito web:
![img_Sito_Web](img/immagine_sito_web.png)

Il sito web contiene le seguenti informazioni:
- **Titolo del gioco**: il nome del gioco, ossia "Escape the Pyramid".
- **Bottone Play** / **Bottone Pause**: permette di avviare o fermare la musica di sottofondo.
- **Indice linkato**: permette di accedere rapidamente alle varie sezioni del sito web.
- **Spiegazione Progetto**: una breve descrizione del progetto e degli obiettivi.
- **Manuale Utente**: spiega come giocare e le regole del gioco.
- **Migliori Tempi di Gioco**: mostra i migliori tempi di gioco degli utenti.
- **Sviluppatori**: elenca i membri del team di sviluppo e il link al repository GitHub.

Ovviamente ciascuna sezione contiene, al suo termine, un link che permette di tornare all'indice del sito web.

<h4><b>Come è stato implementato il socket?</b></h4>
All'interno del nostro codice, in particolare nel package "Database", sono presenti le classi che hanno permesso l'implementazione del socket. 

Andiamo ad osservare il codice nel dettaglio:
```java
// Codice per l'inizializzazione del RestServer
package org.it.uniba.minima.Database;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.grizzly.http.server.ServerConfiguration;

import java.io.IOException;

public class RESTServer {

  public void startServer() {
    HttpServer server = HttpServer.createSimpleServer("/", 8080);
    ServerConfiguration config = server.getServerConfiguration();
    String staticDir = RESTServer.class.getResource("/static").getPath();
    StaticHttpHandler staticHttpHandler = new StaticHttpHandler(staticDir);
    config.addHttpHandler(staticHttpHandler, "/");
    config.addHttpHandler(new DatabaseHandler(), "/api/data");
    try {
      server.start();
      System.out.println("Server started at http://localhost:8080/api/data");
      System.out.println("Press any key to stop the server...");
      System.in.read();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      server.shutdownNow();
    }
  }
}
``` 
</details>
</li>
<li>
    <h2>5) Utilizzo di Java Swing</h2>
    <details open>
        <summary>Visualizza dettagli</summary>
          <h3><b>Cosa è Java Swing?</b></h3>
        Java Swing è stato introdotto per la prima volta con Java 1.2 e ha sostituito il vecchio toolkit AWT (Abstract Window Toolkit) ed è il framework che permette la realizzazione di interfacce grafiche (GUI).

Essendo una libreria grafica, Java Swing permette di creare interfacce grafiche per applicazioni desktop, offrendo una vasta gamma di componenti grafici tra cui:
        
- Finestre, Form, Dialog
- Menu, Pulsanti, Check-box, Combo-box
- Alberi, Tabelle
- Layout, Look&Feel

<h3><b>Come abbiamo utilizzato Java Swing nel nostro progetto?</b></h3>
Il nostro progetto è stato sviluppato utilizzando interamente Java Swing per la realizzazione dell'interfaccia grafica del gioco, dal momento che volevamo creare un'esperienza di gioco coinvolgente e interattiva per l'utente. 

Esso vanta lo sviluppo di una GUI molto ricca e complessa utilizzando un unico JFrame, che contiene tutti i componenti grafici necessari per l'interazione con l'utente attraverso l'utilizzo di JPanel e CardLayout.

L'utilizzo delle  Cardlayout, contenenti al loro interno i vari JPanel, ci ha permesso di creare una GUI dinamica e flessibile, in grado di passare da una schermata all'altra in modo fluido e intuitivo e sopratutto di permettere sviluppi futuri senza dover modificare il codice esistente.

All'interno del nostro progetto, la classe principale che gestisce la GUI è la classe <b>GUIManager</b>, che estende la classe JFrame e si occupa di inizializzare e gestire tutti i componenti grafici del gioco, come mostrato di seguito:
```java
package org.it.uniba.minima.GUI;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import org.it.uniba.minima.Mixer;

public class GUIManager extends JFrame {

    public GUIManager() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        try {
            Image icon = ImageIO.read(new File("docs/img/gameIcon.jpg"));
            setIconImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel cards = new JPanel(new CardLayout());

        MenuGUI menu = new MenuGUI();
        RiconoscimentiGUI credits = new RiconoscimentiGUI();
        ProgressBarGUI progressBar = new ProgressBarGUI();
        GameGUI game = new GameGUI();

        cards.add(menu, "MenuGUI");
        cards.add(credits, "RiconoscimentiGUI");
        cards.add(progressBar, "ProgressBarGUI");
        cards.add(game, "GameGUI");

        add(cards);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        Mixer music = Mixer.getInstance();
        music.start();
    }
}
```
La classe <b>GUIManager</b> non contiene alcun metodo ma solo il costruttore, che si occupa di inizializzare e mostrare la finestra principale del gioco, impostando le seguenti proprietà:
- dimensione, impostata a 800x600 pixel e non ridimensionabile.
- l'icona, impostata con il metodo <b>setIconImage</b> per personalizzare l'icona della finestra.
- i pannelli, creati e aggiunti al <b>CardLayout</b> per gestire le diverse schermate del gioco.
- la musica di sottofondo, avviata tramite la classe <b>Mixer</b> per creare un'atmosfera coinvolgente per l'utente.

In ordine di esecuzione, la prima schermata che viene mostrata all'utente è quella del Menu principale, che appare come mostrato di seguito:

![img_GUI](img/immagine_GUI.png)

La classe che gestisce il Menu principale è la classe <b>MenuGUI</b>, che estende la classe JPanel e contiene tutti i componenti grafici del Menu, come mostrato di seguito:
```java
package org.it.uniba.minima.GUI;
import org.it.uniba.minima.Boundary.outputDisplayManager;
import org.it.uniba.minima.Control.Serializer;
import org.it.uniba.minima.Entity.Game;
import org.it.uniba.minima.Mixer;
import org.it.uniba.minima.TimerManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import javax.swing.ImageIcon;

public class MenuGUI extends javax.swing.JPanel{

  private javax.swing.JPanel backgroundPanel;
  private javax.swing.JButton newGame;
  private static javax.swing.JButton sound;
  private javax.swing.JButton help;
  private javax.swing.JButton loadGame;
  private javax.swing.JButton credits;

  /**
   * Creates new form MenuGUI
   */
  public MenuGUI() {
    initComponents();
  }


  private void initComponents() {
      backgroundPanel = new javax.swing.JPanel() {
          @Override
          protected void paintComponent(Graphics g) {
              super.paintComponent(g);
              ImageIcon img = new ImageIcon("docs/img/placeholder_immagine sfondo.jpeg");
              Image image = img.getImage();
              g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
          }
      };

      newGame.setText("Nuova Partita");
      newGame.setMaximumSize(new java.awt.Dimension(240, 60));
      newGame.setMinimumSize(new java.awt.Dimension(240, 60));
      newGame.setPreferredSize(new java.awt.Dimension(240, 60));
      newGame.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
              newGameActionPerformed(evt);
          }
      });

    private void newGameActionPerformed(java.awt.event.ActionEvent evt) {
      ProgressBarGUI progressBarGUI = (ProgressBarGUI) this.getParent().getComponent(2);
      CardLayout cl = (CardLayout) getParent().getLayout();
      cl.show(getParent(), "ProgressBarGUI");
      Game game = new Game();
      game.setNickname("Player");
      GameGUI.setGame(game);

      GameGUI gameGUI = (GameGUI) this.getParent().getComponent(3);

      progressBarGUI.addPropertyChangeListener(new PropertyChangeListener() {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
          if (evt.getPropertyName().equals("isFinished") && (boolean) evt.getNewValue()) {
            cl.show(getParent(), "GameGUI");
            TimerManager.getInstance().startTimer();
          }
        }
      });

      progressBarGUI.startProgressBar();
    }
  }
}
```
La classe MenuGUI rappresenta il pannello principale del gioco e contiene convari pulsanti per azioni diverse. I componenti principali includono:

backgroundPanel: un pannello che disegna un'immagine di sfondo.
Pulsanti: newGame, sound, help, loadGame, credits.
Nel costruttore, viene chiamato initComponents() per configurare i componenti della GUI.

Il metodo initComponents imposta backgroundPanel per disegnare un'immagine di sfondo e configura il pulsante newGame con testo e dimensioni specificate, aggiungendo un ActionListener per gestire i click.

Il metodo newGameActionPerformed viene chiamato quando il pulsante "Nuova Partita" viene cliccato. Questo metodo:

Passa al pannello ProgressBarGUI usando un CardLayout.
Crea un nuovo oggetto Game, imposta il nickname e lo assegna al GameGUI.
Aggiunge un PropertyChangeListener a ProgressBarGUI per rilevare il completamento della barra di progresso.
Passa al pannello GameGUI e avvia un timer quando la barra di progresso è completata.
In sintesi, MenuGUI gestisce il menu iniziale del gioco, inclusa la transizione a una nuova partita con una barra di progresso e l'avvio del gioco vero e proprio.

A questo punto, l'utente ha molteplici opzioni per interagire con il gioco, come ad esempio:
- **Iniziare una Nuova Partita**:

Dopo aver cliccato il pulsante "Nuova Partita", viene cambiata la card del CardLayout e viene mostrata la schermata di caricamento, che contiene una ProgressBar  utilizzata principalmente per rendere l'utente conscio del fatto che il gioco sta caricando e che è pronto per essere giocato.

La ProgressBar è stata implementata aggiungendo anche un'animazione GIF, che rende la schermata di caricamento più accattivante e coinvolgente.

![img_ProgressBar](img/immagine_ProgressBar.gif)

A seguito del completamento della ProgressBar, la schermata di caricamento viene chiusa e l'utente viene portato alla schermata di gioco, dove il gioco può finalmente iniziare.

![img_GameGUI](img/immagine_nuova_partita.png)




- **Carica Partita**

Dopo aver cliccato il pulsante "Carica Partita", l'effetto è simile a quello della "Nuova Partita", ma in questo caso il gioco carica una partita salvata in precedenza, permettendo all'utente di continuare da dove aveva lasciato.

-

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
            <h3><b>Cosa sono le Lambda expressions?</b></h3>
Le <b>lambda expressions</b> sono una caratteristica introdotta in Java 8 che permette di scrivere codice più conciso e leggibile, migliorando la leggibilità del codice.

Le espressioni lambda sono esempi di programmazione funzionale:
- il flusso di esecuzione del programma assume la forma di una serie di
  valutazioni di funzioni
- mancanza di side-effect
- la programmazione funzionale pone il focus sulla definizione di funzioni, infatti un tale programma è immutabile poichè i valori non vengono calcolati cambiando
  lo stato del programma, ma costruendo nuovi stati a partire dai precedenti
- la programmazione funzionale ha le sue radici nel lambda calcolo , basato sulle funzioni composto da un linguaggio formale utilizzato per
  esprimere le funzioni e un sistema di riscrittura per stabilire come i termini
  possano essere ridotti e semplificati

Il loro utilizzo è particolarmente utile quando si deve passare una o più funzioni come parametro ad un metodo, come ad esempio nei metodi di ordinamento o di filtraggio di una collezione, evitando scrittura di codici "poco eleganti" poichè fortemente connesse alle caratteristiche di classe e non alla logica, evitando riscritture di codice in caso di cambiamenti.

Un’espressione lambda è composta:
- da una lista di parametri separati da virgole racchiusi tra parentesi tonde (E' possibile omettere le parentesi tonde se il metodo ha un solo parametro o il tipo dei parametri).
- dal token -> che separa i parametri dal corpo della lambda.
- da un corpo che contiene una singola espressione o un blocco di istruzioni. Il
  blocco di istruzioni è racchiuso nelle parentesi graffe {}. Se il blocco di
  istruzioni contiene un’invocazione ad un metodo che non restituisce nessun
  valore (void) si possono omettere le parentesi

<h3><b>Come abbiamo utilizzato le Lambda expressions nel nostro progetto?</b></h3>
L'utilizzo migliore delle lambda expressions in un gioco testuale è stato per la gestione delle azioni possibili all'interno di una stanza, come ad esempio la possibilità di spostarsi in una direzione, di raccogliere un oggetto o di interagire con un personaggio.

Andiamo a vedere tutti gli utilizzi delle lambda expressions all'interno del nostro progetto:

- <b>ProgressBar:</b> per la gestione della barra di progresso del gioco, che indica il tempo rimanente per completare una determinata azione.
```java
// Codice per la gestione della ProgressBar
public void startProgressBar() {
  int imgWidth = 161;
  int panelWidth = runningGIFPanel.getWidth();
  counter = 0;

  Timer timer = new Timer(1000, e -> {
    if (counter < 100) {
      counter++;
      progressBar.setValue(counter);
      progressBar.setString("Loading... " + counter + "%");
      x = (int) ((double) counter / 100 * (panelWidth + imgWidth)) - imgWidth;
      runningGIFPanel.repaint();
    } else {
      ((Timer) e.getSource()).stop();
      progressBar.setString("Get Ready to Play!");

      Timer delayTimer = new Timer(1000, e1 -> {
        ((Timer) e1.getSource()).stop();
        setFinished(true);
      });
      delayTimer.start();
    }
  });
  timer.start();

}
``` 
Questo metodo <b>startProgressBar</b> è stato utilizzato per far partire la barra di progresso nella schermata di caricamento del gioco, in modo che l'utente possa capire quanto manca al completamento dell'azione.

Il metodo qui descritto, nonstante possa sembrare complesso a causa della presenza di due Timer, è in realtà molto semplice e leggibile grazie all'utilizzo delle lambda expressions, che permettono di scrivere codice più conciso e leggibile.

Infatti, nella prima lambda viene contiunamente aggiornato il valore della barra di progresso e la posizione dell'immagine, mentre nella seconda lambda, che viene eseguita al completamento della barra di progresso, viene settato il valore del booleano <b>finished</b> a <b>true</b>, in modo che il gioco possa iniziare.

- <b>InputUtente</b>: dopo il caricamento della progressBar, dunque nel momento in cui l'effettivo gioco inizia, l'utente dovrà poter inserire i comandi per interagire con il gioco.
```java
// Codice per la gestione dell'input utente
public static void startInputListener(javax.swing.JTextField userInputField) {
  new Thread(() -> {
    while (true) {
      if (!isCurrentInputEmpty()) {
        userInputFlow.GameFlow(getCurrentInput());
      }
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }).start();
}
```
Questo metodo <b>startInputListener</b> è stato utilizzato per far partire un thread che ascolta costantemente l'input dell'utente, in modo che l'utente possa inserire i comandi per interagire con il gioco.
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
