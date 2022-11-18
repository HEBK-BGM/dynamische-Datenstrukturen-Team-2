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

![Klassendiagramm](https://www.plantuml.com/plantuml/png/hLJVRzem47xtN-57zgFINlQ2fggKPYqR3Ir9nyCqLMvoj1d1Jcs73g7zxpixpYufI1FI91Jv-yxttVcpZpDZXRRrkcJiIkWyKnhusvCJVbk3iOMIn2fa4okoLNzZecgPNmif7a4p5Ykz5cLF8N0-VreOs-Bzv5JNCDEnilryebL3rq3S0Pa4z0OqrME-QxynLZamHnBoUWHPLWfZ-ENTXE_OxnPUWwoxs6tzb9uYTS8p9ItyiaEU8f1-5SBi7Ny4EsxYWpUu0nb3JCghiCJOHXLvan27tQHY0jcMhTmYv51JxbnjKyj26se42R6oDPGbEAOhNPXeNTcjg_UWL0b28laap2ZBm9W-BLqFrAfLdx5AdvaA8fhJp1v-GaQd3uG_VBEYtQAgGEO7RIca0NtOCVG_4tRGdhvo2ufgMAiD3FRCyc-YxzQ_NAbgyzIslUgKg5JLyy2Zb4hv5tPSAP3FBkcGIJaq8MAM6YrvLRCTKL_JTCQ33tlJWj7dC6V6XRmhxUITabwOsUFK_9KS4AFXFKxF6tb8iAMyteFAj-uakf2F24lOG3daXRG8X3Gle4qR_lx3pw_Mw72pnJ_PSvNx-mWQdXircKHZhWzCdJxIb3vk10D7NOR_fV2V8I7pvmesZVyowPC6aRkPDJuXKTaAB9Rj81SCT21mjApGM9Itxm89UfZKnUj278S9P6vuWBizRIHS1FIGdzVB9MZ8vnvZu4KNjXjOUEoAesQt6LJmk3ML-YaBaFP_bGt63feglAZUkpl04TMsqqnMAWDa1FLvHD_9xRHvfNIZk8j4Epkx3to7cbKfjfuS-6-whO1R_EpTrRSY1_KwqvC3_wLCJgaWwpKduHsxlOkIT3ABsM8-Yo_lHt6oYEPiFhcyIbimkPX6B4b7yvGj9Z7x-E4UVrzcNp6KHjFevceMHm6FxYucKJnkOLkG-jcnIPpEPtIA6-6kCyIGFKEBwtNv1m00)

### 4.2 Objektdiagramm
*Auf Basis Ihres Klassendiagramms ist hier ein Objektdiagramm anzugeben.*

![Objektdiagramm](https://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/HEBK-BGM/dynamische-Datenstrukturen-Team-2/master/doku/lastenheft/Diagramme/Objektdiagramm.puml)

### 4.3 Sequenzdiagramm
*Hier wird ein Sequenzdiagramm zu einer Funktion aus 3.1 dargestellt.*

![Sequenzdiagramm](https://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/HEBK-BGM/dynamische-Datenstrukturen-Team-2/master/doku/lastenheft/Diagramme/Sequenzdiagramm.puml)