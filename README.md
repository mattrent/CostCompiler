---
title: CostCompiler - Compilatore per la generazione di equazioni di costo
author:
  Simone Boldrini 
date: 2023
---

# CostCompiler
Il Compilatore é sviluppato in java e si appoggia a [ANTLR](https://www.antlr.org/) per la generazione del parser.
Prende in input un programma che segue le regole della grammatica definita in [HLCostLan.g4](https://github.com/minosse99/CostCompiler/blob/master/src/HLCostLan.g4) e genera un file .ces contenente le equazioni di costo.
Una volta generato il file equation.ces il programma si occuperá a sua volta di eseguire il programma [PUBS](http://costa.fdi.ucm.es/pubs/) per la risoluzione dell upper buond della funzione data in input inizialmente.
Questo passaggio richiede che il programma venga eseguito su macchine linux oppure in alternativa su macchine windows con installato [WSL](https://docs.microsoft.com/it-it/windows/wsl/install-win10).

## Esecuzione del programma
Per eseguire il programma é necessario avere installato java e scaricare il lo zip CostCompiler.zip.
Una volta scaricato il file é necessario estrarre il contenuto e posizionarsi nella cartella CostCompiler, dove é presente il file CostCompiler.jar, pubs_static e una cartella di file di esempio.
A questo punto é possibile eseguire il programma con il comando:
```
java -jar CostCompiler.jar example/<file>
 ```

## Progetto 
Il programma include la libreria `ANTLR 4.13.1-complete.jar` e `ST-4.3.1.jar`, mentre il programma é stato implementato e testato con `Java 17`(compatibile anche con Java 11).
Il sorgente é diviso in 4 pacchetti:
- **ast**: che contiene le classi che implementano l AST
- **com.company**: contiene il main 
- **gen**: contiene i file generati da ANTLR
- **utils**: contiene classi di utilitá

Inoltre é presente la cartella `test` che contiene il file di test.

## Grammatica
La grammatica é definita nel file [HLCostLan.g4](https://github.com/minosse99/CostCompiler/blob/master/src/HLCostLan.g4) e contiene le seguenti regole:

```
prg : complexType* serviceDecl* functionDecl* init;

init: (formalParams?  )  =  >   {  stm  } ;


serviceDecl:  service ID :  ( (type( , type)*)? )  -> type ; ;

functionDecl :  fn ID (  formalParams?   )   ->  (type) { stm }  ;

stm :
     | serviceCall
     | if (  expOrCall  )   { stm  }   else   {  stm  } 
     | for  ( ID  in  (  NUMBER , exp  )  )   {  stm  }  
     | letIn
     | functionCall
     |  return  expPlus ;

serviceCall :  call ID ( (exp( , exp)*)? )  ( ;  stm)?;

functionCall : ID ( ( exp ( , exp)* )?  ) ;

letIn:  let  (ID  =  expPlus)+  in  stm;
```

La program entry point é la regola `prg` che definisce un programma come una sequenza di dichiarazioni di tipo complesso (che possono essere Struct oppure array), dichiarazioni di servizi, dichiarazioni di funzioni e un blocco di inizializzazione.
La regola `init` definisce il blocco di inizializzazione che puó contenere parametri formali, una sequenza di dichiarazioni di variabili e un blocco di istruzioni.
La regola `serviceDecl` definisce la dichiarazione di un servizio che puó avere parametri formali e restituisce un valore di tipo base.
La regola `functionDecl` definisce la dichiarazione di una funzione che puó avere parametri formali e restituisce un valore di tipo complesso.
La regola `stm` definisce un blocco di istruzioni che puó essere un servizio, un if, un for, un letIn, una chiamata di funzione o un return.

## Esempio di esecuzione
Nella cartella `example` sono presenti alcuni file di esempio che possono essere utilizzati per testare il programma.
Di seguito é riportato un esempio di un programma che rispecchia la grammatica definita in precedenza:
```
  service BasicService: (int) -> void;
  fn svc(i: int) -> void{
      for(m in (0,10)){
          call BasicService(i)
      }

  }
  (len : int) => {
    svc(len)
  }
```
Andando a eseguire questo file `example/Listing6` otterremo il seguente risultato salvato nel file `equation.ces`:
```
eq(svc(B),0,[for3(0, B)],[] ).
eq(for3(M, B) ,nat(B),[for3(M+1, B)], [10>= M]).
eq(for3(M, B) ,0,[],[M >= 10+ 1]).
eq(main(B),1,[svc(B)],[]).

```
l'equazione di costo associata al programma, e l'esecuzione del file pubs_static che restituirá il risultato:
```
This is PUBS, version 0.2.
Copyright (C) 2007, 2008, 2009 UPM, UCM.
This program comes with ABSOLUTELY NO WARRANTY.
This is free software, and you are welcome to
redistribute it under certain conditions.



CRS '$pubs_aux_entry$'(A) -- THE MAIN ENTRY

  * Non Asymptotic Upper Bound: 11*nat(A)

  * LOOPS '$pubs_aux_entry$'(B) -> '$pubs_aux_entry$'(C)

  * Ranking function: N/A

  * Invariants '$pubs_aux_entry$'(A) -> '$pubs_aux_entry$'(B)

     entry  : []
     non-rec: [A=B]
     rec    : [0=1]
     inv    : [1*A+ -1*B=0]

CRS svc(A)

  * Non Asymptotic Upper Bound: 11*nat(A)

  * LOOPS svc(B) -> svc(C)

  * Ranking function: N/A

  * Invariants svc(A) -> svc(B)

     entry  : []
     non-rec: [A=B]
     rec    : [0=1]
     inv    : [1*A+ -1*B=0]

CRS for3(A,B)

  * Non Asymptotic Upper Bound: nat(-A+11)*nat(B)

  * LOOPS for3(C,D) -> for3(E,F)
     - [-1*E>= -11,1*C+ -1*E= -1,1*D+ -1*F=0]

  * Ranking function: nat(-1*C+11)

  * Invariants for3(A,B) -> for3(C,D)

     entry  : [1*A=0]
     non-rec: [A=C,B=D,1*A=0]
     rec    : [1*B+ -1*D=0,1*A=0,1*C>=1]
     inv    : [1*C>=0,1*A=0,1*B+ -1*D=0]

