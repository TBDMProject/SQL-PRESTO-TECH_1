# SQL-PRESTO-TECH

This project was developed as an assignment for the examination of Big Data Management Technologies, a course in the master's degree programme in computer science at the University of Camerino.

### Table of Contents

## Introduction

The objective of the project is to establish a prototype tool for real-time data analysis. The overall system architecture is depicted in the figure below.

![alt text](https://github.com/TBDMProject/SQL-PRESTO-TECH_1/blob/daniele-pelosi-images/Architecture.png?raw=true =50x50)

Let us examine how such tools were utilized within the project:

- **IoT Message Generator:** è un simulatore di messaggi IoT. Il generatore in questione quindi è stato utilizzato per simulare la creazione dei messaggi da dispositivi IoT. Questi messaggi vengono tutti indirizzati sotto un unico topic
- **Apache Kafka:** è stato utilizzato per leggere in real time i messaggi provenienti dall’IoT Simulator e fare allo stesso tempo lo storage di questi messaggi all’interno di un database, che come possiamo vedere è MongoDB.
- **MongoDB:** database non relazionale document oriented scelto per fare lo storaging dei messaggi streammati da kafka in real time
- **Presto:** è lo strumento che permette di express the analytics via standard SQL queries su di un database NoSQL(MongoDB in questo caso)
- **Jupyter:** utilizzato per creare un notebook per produrre analisi sui dati raccolti e generare grafici per una migliore visualizzazione e interpretazione dei dati

According on the aforementioned descriptions, it can be concluded that the project **objectives** consist of the following areas: publishing messages to Kafka on a predetermined topic, storing data in a NoSQL database, configuring Presto to establish a connection with the database, and utilizing Jupyter to generate a notebook with standard visualization and analysis of the messages

## Technologies

### [Apache Kafka](https://kafka.apache.org/)

![alt text](https://github.com/TBDMProject/SQL-PRESTO-TECH_1/blob/daniele-pelosi-images/kafkalogo.jpg?raw=true)

pache Kafka is an open-source distributed event streaming platform that operates as a publish-subscribe messaging solution. It was specifically designed for real-time data streaming, distributed pipelining, and replaying of data feeds to enable fast and scalable operations. Kafka is utilized by thousands of companies for high-performance data pipelines, streaming analytics, data integration, and mission-critical applications.

This broker-based solution stores data streams as records within a cluster of servers. Kafka servers can extend across multiple data processing centers and provide data persistence by storing streams of records (messages) across multiple server instances in topics.


### [MongoDB](https://www.mongodb.com/)

![alt text](https://github.com/TBDMProject/SQL-PRESTO-TECH_1/blob/daniele-pelosi-images/MongoDB-Logo.jpg?raw=true)

MongoDB is an open source NoSQL database that uses a non-relational, document-oriented data model. It stores data objects in collections and documents, rather than in the tables and rows used in relational databases. MongoDB uses the document storage format BSON, which is a binary form of JSON. Documents consist of key-value pairs, which can include various data types such as other documents, arrays and document arrays. The structure of a document can be changed simply by adding or deleting fields, and documents can define a primary key as a unique identifier.

### [Presto](https://prestodb.io/)

![alt text](https://github.com/TBDMProject/SQL-PRESTO-TECH_1/blob/daniele-pelosi-images/logo-presto-color.svg?raw=true)

Presto (o PrestoDB) is a distributed SQL query engine that is open-source and optimized for high-speed analytic queries of data of any size. It supports relational and non-relational data sources, such as Amazon S3, Hadoop Distributed File System (HDFS), and MongoDB. With Presto, data can be queried directly without the need to transfer it to a separate analytics system. It is widely used by major companies like Facebook and Netflix, executing parallel queries over a pure memory-based architecture for fast results.

### [Jupyter Notebook](https://jupyter.org/)

![alt text](https://github.com/TBDMProject/SQL-PRESTO-TECH_1/blob/daniele-pelosi-images/jupyter-logo.svg?raw=true)

The Jupyter Notebook App is a server-client application that allows editing and running notebook documents via a web browser. The Jupyter Notebook App can be executed on a local desktop
requiring no internet access (as described in this document) or can be installed on a remote server and accessed through the internet.

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
