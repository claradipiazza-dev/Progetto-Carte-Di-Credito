# Progetto Gestione Carte di Credito
Questo repository contiene lo sviluppo di una piattaforma web completa per l'emissione, la compravendita e il monitoraggio di carte di credito. Il sistema è progettato per gestire flussi di lavoro differenti in base al profilo di accesso (Utente o Amministratore).

---

## Descrizione del Progetto
L'applicazione simula un sistema in cui gli utenti possono acquistare e gestire le proprie carte, mentre gli amministratori supervisionano l'intero circuito. Il cuore del progetto risiede nella gestione delle transazioni di acquisto carte e nel controllo dello stato di operatività delle stesse.

---

## Profili e Funzionalità:
### Modulo Amministratore
L'amministratore ha il controllo totale sulla piattaforma per garantire la sicurezza del circuito:

* **Gestione Operatività:** Potere di bloccare o sbloccare le carte di credito degli utenti registrati in tempo reale.

* **Gestione Catalogo:** Funzionalità per inserire nuove tipologie di carte di credito nel database, rendendole disponibili per l'acquisto sul sito.

## Modulo Utente
L'utente può gestire il proprio portafoglio finanziario in autonomia:

* **Marketplace:** Acquisto di nuove carte di credito tra quelle disponibili sulla piattaforma (gestione pagamenti).

* **Wallet e Saldo:** Aggiunta delle proprie carte al profilo e visualizzazione immediata del saldo aggiornato.

* **Area Personale:** Visualizzazione e gestione dei dati del profilo.

## Sicurezza e Account Management:
Oltre ai flussi standard, per garantire la protezione dei dati, il sistema include moduli dedicati alla gestione dell'identità:

* **Accesso Riservato:** Login e registrazione con reindirizzamento automatico alla dashboard corretta (Admin o Utente).

* **Recupero Password:** Procedura guidata per il ripristino delle credenziali via email.

* **Sicurezza Account:** Funzionalità interna per la modifica della password di accesso.

---

## Stack Tecnologico
* **Backend:** Java Core, Java Servlet API.

* **Pattern Architetturale:** MVC (Model-View-Controller).

* **Database:** MySQL.

* **Frontend:** JSP, JSTL, HTML5, CSS3, JavaScript (AJAX per controlli dinamici).

* **Servizi:** JavaMail API per il sistema di recupero password e notifiche.

---

## Architettura delle Classi
Il sistema riflette la complessità del dominio tramite una struttura organizzata:

* **controller:** Servlet dedicate ai due profili (es. AreaCarta per l'utente, GestisciCarte per l'admin).

* **query:** Logica SQL per il filtraggio delle carte per utente e l'aggiornamento degli stati (Active/Blocked).

* **servizi:** Classe Mail.java per l'integrazione del server SMTP.
