# Lastenheft Team 2

**Entwicklung von "Wer Wird Millionär"**

| Stand        | 16.11.2022                                                  |
|--------------|-------------------------------------------------------------|
| Auftraggeber | Hermann-Emanuel-Berufskolleg Informatik LK Abschlussprojekt |

# Inhaltsverzeichnis
1. [Einleitung](#1-einleitung)
2. [Soll-Konzept](#2-soll-konzept)
3. [Anforderungen](#3-anforderungen)
4. [Entwürfe](#4-entwrfe)


## 1 Einleitung

Wir haben uns Gedacht, dass jeder kennt die Serie aus dem Fernsehen und daher haben wir die Idee, dass wir eine verbesserte
Version des "Wer Wird Millionärs" Spieles Implementieren.

## 2 Soll-Konzept

### 2.1 Anwendungsbreiche

Wenn man sein Wissen unter Beweis stellen oder sich einfach nur die Zeit verteiben möchte.
Wenn man das Spiel staret hat man verschiedene Modies (Normal, Hardcore und True or Not) zur Auswahl, auch einen Multiplayer und eine Highscore Liste.

### 2.2 Systemidee

Eine Java Anwendung mit GUI die sich an die Fernsehsendung "Wer Wird Millionär" orientiert.

### 2.3 Ziele

Anwendung von Dynamischen Datenstrukturen, Datenbanken, GUI, Networking und allgemeine Java Kenntnisse.

### 2.4 Zielgruppe

Um den Schülern dynamische Datenstrukturen näher zu bringen und für die Nutzer ein interessantes, anprechendes Spielerlebniss zu bieten, lassen sich folgende Beteiligte den grob erfassten Anforderungen zuordnen:

| **Anforderungsbeitragende** | **Anforderung**                          |
|-----------------------------|------------------------------------------|
| Lernende                    | Lernen Dynamische Datenstrukturen kennen |
| Nutzer                      | Haben Spaß                               |
| Lehrkräfte                  | Nehmen Pullrequests an (°v°)             |
 
## 3 Anforderungen

### 3.1 Funktionale Anforderungen

| Kürzel | Funktionsname                  | Funktionsbeschreibung                   |
|--------|--------------------------------|-----------------------------------------|
| F1     | Anzeige des Hello-world-Textes | Der Text "hello world" wird ausgegeben. |
| F2     | Anzeigen des Hilfe-Textes      | Ein Hilfetext wird ausgegeben.          |

### 3.2 Nicht-funktionale Anforderungen

| Kürzel | Funktionsname         | Funktionsbeschreibung                                                              |
|--------|-----------------------|------------------------------------------------------------------------------------|
| NF1    | Lock and feel         | Der Text "hello world" wird ausgegeben.                                            |
| NF2    | Lock and feel         | Die Ausgabe der Texte soll unmittelbar erfolgen.                                   |
| NF3    | Internationalisierung | Es ist langfristig vorgesehen, die Texte in verschiedenen Übersetzungen anzuzeigen |
| NF4    | Sicherheit            | Diesbezüglich liegen keine Anforderungen vor.                                      |
| NF5    | Normen                | EDie Anwendung braucht keine besonderen Normen zu erfüllen.                        |

## 4 Entwürfe

### 4.1 Klassendiagramm

![Klassendiagramm](https://www.plantuml.com/plantuml/png/hLH1Rziy3BtxL-YO3zy6xB9BC1HDKw_rbjfBx2w7OIXKcqczE98coUc2OFzzb6n2RXfW232WKCL7yJtocSYbiLpRTjj4KTbmOzZDVS8EqMyAxq2qmpYL5NmeBX1woqefBFooOrPW8DoDqj7_R0rsskL7_-4BH4n02gRwA-PsigwwWhH_7LXC3vAiSfU-9ZGrgusbfbQrDg4H1FZ6jj0qu90XTMtYhR9xf_SePGDS8FZ4pQGimPYNi70zX5Qj_8WgBv4P9u2coHv_G1cc9y0Dtzt2QwuKYEgaBRKme4yxXlwNt8veUkKk8QjXAtSmUcLMRdcvENBhBwwerZnHsvk1X99gw44N5K5gta8Bvqc1-EcAJfbKGPSAo4gZ9KUQjrnNfTJWDOriHNN71L-3PcEM3qBC8fsnjHG6Kvqhynwv62wytlBcF3Q_aAV82jr2fbDfp-JpHfuY9CZpFSfgxoRHjLXZsC0EcZ6hXSM02_CCsb3wstV4q2QD3lidtSfU8GFwIHmUctIL0SPQdvWvVeH3-RGZ92oi7EWTQFhD453wkNgRfaqD0ai9lt3E-89SbXkmA1iY9sGirwZ1Q0qG27iOH8zvEyk9qlz7G0ElG8_PLRjQWOPguMDCFElQ3XDB7pir4DLG7HbyJAOTDV2FPCcuKQV2QlN6USoKr7RGJDb80s7cqESPVUVp6IqbVJ5y5KW7BuSBV41ErV2z1qV-BFOAcCNZrSVvMbSWZoizEF9dg6IX54IxPO7u4Ctluxn8iZHQBhBqvc6Iviju4IsIczk2WkHw5aTvCLaKqJ99e_Vl7l3lK_OPKqKyYx_SPcdSnvFxwoHEfnIIOEZd42LfiSXSvXBDRBVD7m00)

### 4.2 Objektdiagramm
*Auf Basis Ihres Klassendiagramms ist hier ein Objektdiagramm anzugeben.*

![Objektdiagramm](https://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/HEBK-BGM/dynamische-Datenstrukturen-Team-2/master/doku/lastenheft/Diagramme/Objektdiagramm.puml)

### 4.3 Sequenzdiagramm
*Hier wird ein Sequenzdiagramm zu einer Funktion aus 3.1 dargestellt.*

![Sequenzdiagramm](https://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/HEBK-BGM/dynamische-Datenstrukturen-Team-2/master/doku/lastenheft/Diagramme/Sequenzdiagramm.puml)