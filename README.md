# SQL-PRESTO-TECH

This project was developed as an assignment for the examination of Big Data Management Technologies, a course in the master's degree programme in computer science at the University of Camerino.

### Table of Contents
[Introduction](#introduction)
<br>
[Technologies](#technologies)  
[Prerequisites](#prerequisites)
<br>
[Installation & Configuration](#installation-and-configuration)  
[Usage](#usage)  
[Demo](#demo)  
[License](#license)
<br>
[Contact Information](#contact-information) 

## Introduction

The objective of the project is to establish a prototype tool for real-time data analysis. The overall system architecture is depicted in the figure below.

![alt text](https://github.com/TBDMProject/SQL-PRESTO-TECH_1/blob/daniele-pelosi-images/Architecture.png?raw=true)

Let us examine how such tools were utilized within the project:

- **IoT Message Generator:** is an IoT message simulator. The generator in question was therefore used to simulate the creation of messages from IoT devices. These messages are all addressed under one topic
- **Apache Kafka:** was used to read messages from the IoT Simulator in real time and at the same time store these messages within a database, which as we can see is MongoDB.
- **MongoDB:** non-relational document oriented database chosen to do the storing of messages streamed by kafka in real time
- **Presto:** is the tool to express the analytics via standard SQL queries on a NoSQL database (MongoDB in this case)
- **Jupyter:** used to create a notebook to produce analysis on collected data and generate graphs for better visualisation and interpretation of data

According on the aforementioned descriptions, it can be concluded that the project **objectives** consist of the following areas: publishing messages to Kafka on a predetermined topic, storing data in a NoSQL database, configuring Presto to establish a connection with the database, and utilizing Jupyter to generate a notebook with standard visualization and analysis of the messages

## Technologies

### [Apache Kafka](https://kafka.apache.org/)
<p align="center">
  <img src="https://github.com/TBDMProject/SQL-PRESTO-TECH_1/blob/daniele-pelosi-images/kafkalog.png" alt="Alt text" title="Optional title" width="350" height="300">
</p>

Apache Kafka is an open-source distributed event streaming platform that operates as a publish-subscribe messaging solution. It was specifically designed for real-time data streaming, distributed pipelining, and replaying of data feeds to enable fast and scalable operations. Kafka is utilized by thousands of companies for high-performance data pipelines, streaming analytics, data integration, and mission-critical applications.

This broker-based solution stores data streams as records within a cluster of servers. Kafka servers can extend across multiple data processing centers and provide data persistence by storing streams of records (messages) across multiple server instances in topics.


### [MongoDB](https://www.mongodb.com/)

<p align="center">
  <img src="https://github.com/TBDMProject/SQL-PRESTO-TECH_1/blob/daniele-pelosi-images/MongoDB-Logo.png" alt="Alt text" title="Optional title" width="390" height="300">
</p>

MongoDB is an open source NoSQL database that uses a non-relational, document-oriented data model. It stores data objects in collections and documents, rather than in the tables and rows used in relational databases. MongoDB uses the document storage format BSON, which is a binary form of JSON. Documents consist of key-value pairs, which can include various data types such as other documents, arrays and document arrays. The structure of a document can be changed simply by adding or deleting fields, and documents can define a primary key as a unique identifier.

### [Presto](https://prestodb.io/)

<p align="center">
  <img src="https://github.com/TBDMProject/SQL-PRESTO-TECH_1/blob/daniele-pelosi-images/Presto_logo.png" alt="Alt text" title="Optional title" width="550" height="300">
</p>

Presto (o PrestoDB) is a distributed SQL query engine that is open-source and optimized for high-speed analytic queries of data of any size. It supports relational and non-relational data sources, such as Amazon S3, Hadoop Distributed File System (HDFS), and MongoDB. With Presto, data can be queried directly without the need to transfer it to a separate analytics system. It is widely used by major companies like Facebook and Netflix, executing parallel queries over a pure memory-based architecture for fast results.

### [Jupyter Notebook](https://jupyter.org/)

<p align="center">
  <img src="https://github.com/TBDMProject/SQL-PRESTO-TECH_1/blob/daniele-pelosi-images/jupyter-logo.svg" alt="Alt text" title="Optional title" width="800" height="240">
</p>

The Jupyter Notebook App is a server-client application that allows editing and running notebook documents via a web browser. The Jupyter Notebook App can be executed on a local desktop
requiring no internet access (as described in this document) or can be installed on a remote server and accessed through the internet.

## Prerequisites
* `Ubuntu O.S. version 20.04`
* `Java Runtime Environment(JRE), recommended OpenJDK 11`
* `Docker installation`
* `git clone https://github.com/massimocallisto/iot-simulator.git`

## Installation and Configuration

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
