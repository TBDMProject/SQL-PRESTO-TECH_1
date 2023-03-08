# SQL-PRESTO-TECH

This project was developed as an assignment for the examination of Big Data Management Technologies, a course in the master's degree programme in computer science at the University of Camerino.

### Table of Contents

## Introduction

The objective of the project is to establish a prototype tool for real-time data analysis. The overall system architecture is depicted in the figure below.

![alt text](https://github.com/TBDMProject/SQL-PRESTO-TECH_1/blob/daniele-pelosi-images/Architecture.png?raw=true)

Let us examine how such tools were utilized within the project:

- **IoT Message Generator:** è un simulatore di messaggi IoT. Il generatore in questione quindi è stato utilizzato per simulare la creazione dei messaggi da dispositivi IoT. Questi messaggi vengono tutti indirizzati sotto un unico topic
- **Apache Kafka:** è stato utilizzato per leggere in real time i messaggi provenienti dall’IoT Simulator e fare allo stesso tempo lo storage di questi messaggi all’interno di un database, che come possiamo vedere è MongoDB.
- **MongoDB:** database non relazionale document oriented scelto per fare lo storaging dei messaggi streammati da kafka in real time
- **Presto:** è lo strumento che permette di express the analytics via standard SQL queries su di un database NoSQL(MongoDB in questo caso)
- **Jupyter:** utilizzato per creare un notebook per produrre analisi sui dati raccolti e generare grafici per una migliore visualizzazione e interpretazione dei dati

According on the aforementioned descriptions, it can be concluded that the project **objectives** consist of the following areas: publishing messages to Kafka on a predetermined topic, storing data in a NoSQL database, configuring Presto to establish a connection with the database, and utilizing Jupyter to generate a notebook with standard visualization and analysis of the messages

## Tecnologie

### [Apache Kafka](https://kafka.apache.org/)

### [MongoDB](https://www.mongodb.com/)

### [Presto](https://prestodb.io/)

### [Jupyter Notebook](https://jupyter.org/)

## Prerequisites

sta sul wiki

## Installation & Configuration

sta sul wiki

## Usage

sta nella wiki

## Demo

mettere screen

## License

SQL-TECH-PRESTO is available under the MIT [license](https://raw.githubusercontent.com/TBDMProject/SQL-PRESTO-TECH_1/main/LICENSE.md)

```markdown
Copyright (c) 2017 Wolox
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
```

## Contact Information

| Contact | Mail | Contact Number |
| --- | --- | --- |
| Avdil Mehmeti | avdil.mehmeti@studenti.unicam.it | 3333333 |
| Daniele Pelosi | daniele.pelosi@studenti.unicam.it | 33333333 |
| Kiran Jose Puthussery | kiran.puthussery@studenti.unicam.it | 33333333 |
