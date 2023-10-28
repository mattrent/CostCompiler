---
title: CostCompiler - Compilatore per la generazione di equazioni di costo
author:
  Simone Boldrini 
date: 2023
---

# CostCompiler
Il Compilatore é sviluppato in java e si appoggia a [ANTLR](https://www.antlr.org/) per la generazione del parser.
Prende in input un codice che segue le regole della grammatica definita in [HLCostLan.g4](https://github.com/minosse99/CostCompiler/blob/master/src/HLCostLan.g4) e genera un file .ces contenente le equazioni di costo.
Una volta generato il file equation.ces il programma si occuperá a sua volta di eseguire il programma [PUBS](http://costa.fdi.ucm.es/pubs/) per la risoluzione dell'upper buond della funzione data in input inizialmente.
Questo passaggio richiede che il programma venga eseguito su macchine linux oppure in alternativa su macchine windows con installato [WSL](https://docs.microsoft.com/it-it/windows/wsl/install-win10).

## Esecuzione del programma
Per eseguire il programma é necessario avere installato java e scaricare il lo zip CostCompiler.zip.
Una volta scaricato il file é necessario estrarre il contenuto e posizionarsi nella cartella CostCompiler, dove é presente il file CostCompiler.jar, pubs_static e una cartella di file di esempio.
A questo punto é possibile eseguire il programma con il comando:
```
java -jar CostCompiler.jar example/<file>
 ```

## Progetto 
Il programma include la libreria `ANTLR 4.13.1-complete.jar` e `ST-4.3.1.jar`, mentre il programma é stato implementato e testato con `Java 17`.
Il sorgente é diviso in 4 pacchetti:
- **ast**: che contiene le classi che implementano l'AST
- **com.company**: contiene il main 
- **gen**: contiene i file generati da ANTLR
- **utils**: contiene classi di utilitá

Inoltre é presente la cartella `test` che contiene il file di test.



