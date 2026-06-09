# Java-Bestellshop

Ein konsolenbasierter Bestellshop in Java. Der Nutzer kann sich den Produktkatalog
anzeigen lassen, als Kunde eine Bestellung mit mehreren Artikeln aufgeben und bekommt
am Ende eine Bestellübersicht. Der Katalog wird zusätzlich in eine Textdatei geschrieben.

Reines Lern-/Übungsprojekt, vollständig von Hand geschrieben (NetBeans-/Ant-Projekt).

## Features

- Produktkatalog mit drei Kategorien: **Bücher**, **Elektronik**, **Kleidung**
- Bestellung mit **mehreren Artikeln** in einem Durchgang
- Prüfung des Lagerbestands (`ZuVielBestelltException`) und der Produktnamen
  (`FalschesProduktException`)
- Bei mehreren Produkten mit gleichem Namen wird über **Artikelnummern** unterschieden
- Ausverkaufte Artikel (Lagerbestand 0) werden aus dem Katalog entfernt
- Ausgabepfad des Katalogs wird über `config.txt` + `PfadManager` verwaltet

## Voraussetzungen

- Java (JDK 8 oder neuer)
- Optional: Apache Ant (das Projekt enthält eine `build.xml`)

## Starten

> **Wichtig:** Aus dem Ordner `Java_Bestellshop_V4.0` starten, damit `config.txt`
> gefunden wird. Wird die Datei nicht gefunden, schreibt das Programm den Katalog
> ersatzweise als `katalog.txt` ins aktuelle Verzeichnis.

Kompilieren und ausführen mit `javac`/`java`:

```bash
cd Java_Bestellshop_V4.0
javac -d build/classes src/java_bestellshop/*.java
java -cp build/classes java_bestellshop.Java_Bestellshop
```

Oder über Ant:

```bash
cd Java_Bestellshop_V4.0
ant run
```

### Ablauf

1. Begrüßung – bei „ja" gibt es eine kurze Hilfe.
2. Auf die Kauf-Frage mit `k` (bzw. `kaufen`/`ja`) antworten, um zu bestellen.
   Mit `Pfad ändern` lässt sich der Ausgabepfad des Katalogs anpassen.
3. Kundendaten eingeben (Vorname, Nachname, PLZ, Telefon).
4. Produktname und gewünschte Menge angeben – beliebig oft wiederholbar.
5. Am Ende wird die Bestellübersicht ausgegeben.

## Konfiguration

Die Datei `config.txt` enthält **eine Zeile** mit dem Pfad, unter dem der Katalog
gespeichert wird, z. B.:

```
katalog.txt
```

## Projektstruktur

```
Java_Bestellshop_V4.0/
├── build.xml                 # Ant-Build
├── config.txt                # Ausgabepfad für den Katalog
├── manifest.mf
├── nbproject/                # NetBeans-Projektdateien
└── src/java_bestellshop/
    ├── Java_Bestellshop.java # Einstiegspunkt (main) + Ablaufsteuerung
    ├── Produkt.java          # abstrakte Basisklasse
    ├── Buch.java             # Produkt: Buch (ISBN, Autor)
    ├── Elektronik.java       # Produkt: Elektronik (Garantiezeit)
    ├── Kleidung.java         # Produkt: Kleidung (Größe, Material)
    ├── Kunde.java            # Kundendaten
    ├── Bestellung.java       # Bestellung + Mengenprüfung
    └── PfadManager.java      # Liest/ändert den Ausgabepfad (config.txt)
```

Eigene Exceptions (`AuftragsException`, `FalschesProduktException`,
`ZuVielBestelltException`) liegen in `Java_Bestellshop.java`.

## Bekannte Einschränkungen / ToDo (v4.1)

- Ankauffunktion fehlt noch
- Eingaben sind noch nicht vollständig gegen Fehleingaben abgesichert
  (z. B. Text statt Zahl bei Menge/PLZ/Telefon)
- Preis + Lieferkosten und Gewicht werden noch nicht berechnet
- Pfade mit Leerzeichen werden nicht zuverlässig angenommen