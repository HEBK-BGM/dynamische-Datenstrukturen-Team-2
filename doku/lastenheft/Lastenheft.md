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

![Klassendiagramm](https://www.plantuml.com/plantuml/png/hLJHRvi-47xdh_0ZrUytQIzxYQQgQSfIjXIMG9U7QQfSkAGiO7ksIHT5-zztDbYcATAKQL94VD_d--xkuyILqbJgjgc3eAYfKcH-7vDZyCk5Ty3QOJod3GpZX9VmFhz4w0qfEDFmKqz8ZW4p9qS7_v4jw5d7XnTu0n7baPo8_eZSdbTbbv3qjxsAwa5NLfX3d-ERMbHAkwOsbLI-4GJeJhTGrs2GeNIbeaReWwdto7aDb27uHDMqA42fbp0pFVXMDV-0LLuY2-e0Dqtw-1qAFxq3pF3ToT-cGW0hHssfc08vxXZwNr0Tkkc5ENXMGiFt4BuoAzEqs9suzGTNHAkUNDkxGGd1nT23BifzgMKBBPnN2jWFapHcKWaTvP6DH4jEQjvIMHPSWgsfUClAEyhe5YIPa6mG8ejoIbUSAQGwLvOzSZbSUDdG-Zmrkv1ZOhbi8PK9rsVgMIF711razHvvUJ2Jo8fjCQnX3_K45qXZG9bw1gaS_VKROcYJH8UxAtPbhr41_NGQ5fjscHx6N4iiZ3x2FdtM2JhChnp8FKZtpM1GsBbwcsPr1GnJ7NvfdB496IzseB6iZqmX-mKBiG0HNEeBujO12R-FllYfVcUzaxR_24YW9SW9kMut6v1GhcoCnBEiz91OszXK0rQ6dIEaKLlVXzKAJTonfwtyemQSbQw7IlnFHamfQgx0Jnywwkyco9OBjx3kRU8jBp-uELpk8sgAcXui6DfdVX10D3vUlR0lLGdyDDE2eNtwJE9JWRKDySB7O7aVPNcS9i5wbIRpXscIhQDLi8hdjxaBufj556JvT9K7wpW9thrzmD_7z1DIURI8Fj-cITJ7q_kRE4fcBdG5VJ_784xoLMgOAxIrRUhV)

### 4.2 Objektdiagramm
*Auf Basis Ihres Klassendiagramms ist hier ein Objektdiagramm anzugeben.*

![Objektdiagramm](https://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/HEBK-BGM/dynamische-Datenstrukturen-Team-2/master/doku/lastenheft/Diagramme/Objektdiagramm.puml)

### 4.3 Sequenzdiagramm
*Hier wird ein Sequenzdiagramm zu einer Funktion aus 3.1 dargestellt.*

![Sequenzdiagramm](https://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/HEBK-BGM/dynamische-Datenstrukturen-Team-2/master/doku/lastenheft/Diagramme/Sequenzdiagramm.puml)