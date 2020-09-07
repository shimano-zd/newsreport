# News Report
Java projekt iz NOOP-a.

## O aplikaciji
News Report je jednostavna aplikacija koja omogućuje korisniku "scraping" tj dohvat teksta s određenih novinskih portala i kvantitativnu analizu podataka.
Aplikacija se sastoji od nekoliko djelova koje imaju svoju funkcionalnost:
1. "scraping" - panel tj dio ekrana na kojem je moguće dohvatiti podatke s novinskih stranica i spremiti ih u bazu podataka za odgovarajući datum
2. analiza - panel na kojem je moguće pretraživati prethodno spremljene podatke o vijestima po datumu na koji su objavljene ili po temi
3. postavke - sekcija u kojoj je moguće promijeniti jezik aplikacije. Promjena jezika utječe na korisničko sučelje, analizu vijesti i dohvat podataka iz baze.
Ako se, recimo, odabere hrvatski jezik, aplikacija dohvaća hrvatske portale i pretražuje zapise u bazi koji se odnose na teme iz hrvatskih portala.

## Struktura i design pattern
### MVC
Polazna struktura aplikacije je MVC. Modeli definiraju klase i sučelja za vijesti, jezik i formatiranje datuma.
Controller sadrži klase odgovorne za stanje aplikacije, komunikaciju s bazom podataka i sl.
View se koristi za prikaz grafičkog sučelja.

### Singleton
S obzirom da je potrebno držati neke podatke u aplikaciji kao perzistentne i zajedničke, za stanje (state) se koristi singleton pattern. 
Stanje uključuje podatke o trenutno aktivnom jeziku aplikacije i aktivnom panelu.

### Observer
Stanje aplikacije je ujedno i "observable", odnosno bilo koji dio aplikacije koji treba neku informaciju o stanju se može pretplatiti na promjene u stanju i na to reagirati.

### Factory
Koristi se za potrebe generiranja različitih jezika (hrvatski i engleski) i UI naziva koji o njima ovise.

### Multithreading
S obzirom da dohvaćanje i analiza podataka znaju potrajati par sekundi, potrebno je osloboditi UI thread od takvih "težih" radnji kako bi ostao responzivan i dao korisniku do znanja da se nešto događa u međuvremenu.
Scraping i komunikacija s bazom se odvijaju u zasebnim threadovima, za vrijeme čega UI prikazuje animaciju "loadinga".

## Vanjski dependecy
jFreeChart - prikaz podataka na grafu
jCommon - potreban za graf
jDatePicker - komponenta za odabir datuma
jsoup - potreban za scraping
mssql-jdbc - driver potreban za komunikaciju s SQL serverom
