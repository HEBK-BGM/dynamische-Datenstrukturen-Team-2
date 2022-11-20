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

![Klassendiagramm](https://www.plantuml.com/plantuml/png/hLHHRvim47xdL_YHQjiVYAQgQKeJDWfBeCl3D5KkN58Mi9bjqaNH_llEDfPf6atgj1VBzts-x-w-NBYIYWhLjqqGb0sLaiplOt8CVhlm3bW_ZkUqXN6SyWe-5fS8VI0bPmf-gGafC63wvkZW7Tc2cba-lC0NY4YF5AGRhiZjULtPX7HuxLNa0BgodRuCERwff9RADRMfXVID841tgeUc0OsCfMiPjPqww7gFd3T06O9FL4xB4gHy2JFTWszLyLkiyX99g0FSDDdZ3oZzz0xGmzkRVqsx3bXrrfQQIH3d7KF_Iwf2DtsdBvuLqF8zXA_CoXKjTwTk_TsLhfTFhktTg4J7kx47DYfygMKFFRoj5B2VEkcSIHLOoYCRWPQSr5nGKPLSWAafUS-gEyhe5WIPa7mK8ejoKjMSIQIiAyi1kHmll6XfypOrkv1dn0hHGoPIhjwePuqy8-WWhOSkLWSzYQZP5iC6zj1CS84K1fJ9Pn3IqT--8uOs2NJOFZ4hUuqAuOLxPB6fpTImoxL4elKHzkapA-Wmlt8WzY3SDuD1QUOQR9erDJ1CTVYbTiOavBpSWSAoFjA5p1Sin0AuVq9T4BSEIFXzzCLFzQtrWxGLHK00hK1Co7M_sO20QcLY99v5hSR4siIwclx7MaT8AxU-3wCLwjYofwtyfmQSbQw7kdjFpfXIDbo2dpvqrBzDa2yJjx3kryHNNdxqShpSHzJi6demO6ZEuj01KNYy-i6-rXNmqqm3XkRqcSId0kjRueMFmV8-oeiuIuFrAald3zCqNqUhO1NF5uKBufia2l9YkYg2TPm6Ysbo-_0f-unC4INHbqMMHaCylR-9etJcGbVFjtCCuhHOPPgvGbVxjla3)

### 4.2 Objektdiagramm
*Auf Basis Ihres Klassendiagramms ist hier ein Objektdiagramm anzugeben.*

![Objektdiagramm](https://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/HEBK-BGM/dynamische-Datenstrukturen-Team-2/master/doku/lastenheft/Diagramme/Objektdiagramm.puml)

### 4.3 Sequenzdiagramm
*Hier wird ein Sequenzdiagramm zu einer Funktion aus 3.1 dargestellt.*

![Sequenzdiagramm](https://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/HEBK-BGM/dynamische-Datenstrukturen-Team-2/master/doku/lastenheft/Diagramme/Sequenzdiagramm.puml)