# SQL-PRESTO-TECH

This project was developed as an assignment for the examination of Big Data Management Technologies, a course in the master's degree programme in computer science at the University of Camerino.

**Table of Contents:**
1. [Introduction](#introduction)
1. [Technologies](#technologies)  
1. [Prerequisites](#prerequisites)
1. [Installation & Configuration](#installation-and-configuration)  
    1. [Kafka](#kafka)
    1. [MQTT Dumper](#mqtt-dumper)
    1. [MongoDB](#mongodb-1)
    1. [MongoDB Sink Connector](#mongodb-sink-connector)
    1. [Presto](#presto)
1. [Usage](#usage)  
    1. [IoT Simulator](#iot-simulator-1)
    1. [MQTT Dumper](#mqtt-dumper-1)
    1. [Presto](#presto-1)
    1. [Jupyter Connection](#jupyter-1)
1. [Demo](#demo)  
1. [License](#license)
1. [Contact Information](#contact-information) 

<br />

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
  <img src="https://github.com/TBDMProject/SQL-PRESTO-TECH_1/blob/daniele-pelosi-images/kafkalog.png" alt="Alt text" title="Kafka" width="150" height="150">
</p>

Apache Kafka is an open-source distributed event streaming platform that operates as a publish-subscribe messaging solution. It was specifically designed for real-time data streaming, distributed pipelining, and replaying of data feeds to enable fast and scalable operations. Kafka is utilized by thousands of companies for high-performance data pipelines, streaming analytics, data integration, and mission-critical applications.

This broker-based solution stores data streams as records within a cluster of servers. Kafka servers can extend across multiple data processing centers and provide data persistence by storing streams of records (messages) across multiple server instances in topics.


### [MongoDB](https://www.mongodb.com/)

<p align="center">
  <img src="https://github.com/TBDMProject/SQL-PRESTO-TECH_1/blob/daniele-pelosi-images/MongoDB-Logo.png" alt="Alt text" title="MongoDB" width="190" height="120">
</p>

MongoDB is an open source NoSQL database that uses a non-relational, document-oriented data model. It stores data objects in collections and documents, rather than in the tables and rows used in relational databases. MongoDB uses the document storage format BSON, which is a binary form of JSON. Documents consist of key-value pairs, which can include various data types such as other documents, arrays and document arrays. The structure of a document can be changed simply by adding or deleting fields, and documents can define a primary key as a unique identifier.

### [PrestoDB](https://prestodb.io/)

<p align="center">
  <img src="https://github.com/TBDMProject/SQL-PRESTO-TECH_1/blob/daniele-pelosi-images/Presto_logo.png" alt="Alt text" title="Optional title" width="280" height="110">
</p>

Presto (o PrestoDB) is a distributed SQL query engine that is open-source and optimized for high-speed analytic queries of data of any size. It supports relational and non-relational data sources, such as Amazon S3, Hadoop Distributed File System (HDFS), and MongoDB. With Presto, data can be queried directly without the need to transfer it to a separate analytics system. It is widely used by major companies like Facebook and Netflix, executing parallel queries over a pure memory-based architecture for fast results.

### [Jupyter Notebook](https://jupyter.org/)

<p align="center">
  <img src="https://github.com/TBDMProject/SQL-PRESTO-TECH_1/blob/daniele-pelosi-images/jupyter_logo.png" alt="Alt text" title="Optional title" width="180" height="150">
</p>

The Jupyter Notebook App is a server-client application that allows editing and running notebook documents via a web browser. The Jupyter Notebook App can be executed on a local desktop
requiring no internet access (as described in this document) or can be installed on a remote server and accessed through the internet.

## Prerequisites
* `Ubuntu O.S. version 20.04`
* `Java Runtime Environment(JRE), recommended OpenJDK 11`
* `Docker installation`
* `git clone https://github.com/massimocallisto/iot-simulator.git`

## Installation and Configuration

  <h3 id="kafka">Kafka</h3>

**Step 1 — Creating a User for Kafka**

Since Kafka can handle requests over a network, the first needed step is to create a dedicated user for the service.  
This minimizes damage to your Ubuntu machine in case that someone compromises the Kafka server.  
Now let's create a dedicated *`kafka`* user.

Logged in as your non-root sudo user, create a user called *`kafka`*:

```bash
sudo adduser kafka
```

Follow the prompts to set a password and create the *`kafka`* user.

Next, add the `kafka` user to the `sudo` group with the `adduser` command. These privileges are necessary to install Kafka’s dependencies:

```bash
sudo adduser kafka sudo
```

The `kafka` user is now ready. Log into the account using `su`:

```bash
su -l kafka
```

Now that the Kafka-specific user is created, it's time to download and extract the Kafka binaries.

**Step 2 — Downloading and Extracting the Kafka Binaries**

To start, create a directory in `/home/kafka` called `Downloads` to store your downloads:

```bash
mkdir ~/Downloads
```

Use `curl` to download the Kafka binaries:

```bash
curl "https://archive.apache.org/dist/kafka/2.5.0/kafka_2.12-2.5.0.tgz" -o ~/Downloads/kafka.tgz
```

Create a directory called `kafka` (this will be the base directory of the Kafka installation) and move to this directory:

```bash
mkdir ~/kafka && cd ~/kafka
```

Extract the downloaded archive using the `tar` command:

```bash
tar -xvzf ~/Downloads/kafka.tgz --strip 1
```

**Step 3 — Configuring the Kafka Server**

Kafka’s configuration options are specified in `server.properties`.  
Open this file with `nano` or a generic editor:

```bash
nano ~/kafka/config/server.properties
```

First, add a setting that allows to delete Kafka topics.  
Add the following to the bottom of the file:

```bash
delete.topic.enable = true
```

Then, change the directory where the Kafka logs are stored by modifying the `logs.dir` property:

```bash
log.dirs=/home/kafka/logs
```

Save and close the file.  
Now that kafka is configured Kafka, the next step is to create systemd unit files for running and enabling the Kafka server on startup.

**Step 4 — Creating Systemd Unit Files and Starting the Kafka Server**

In this section, [systemd unit files](https://www.digitalocean.com/community/tutorials/understanding-systemd-units-and-unit-files) for the Kafka service will be created. This will help in performing common service actions such as starting, stopping, and restarting Kafka.

Zookeeper is a service that Kafka uses to manage its cluster state and configurations. It is used in many distributed systems.

Create the unit file for `zookeeper` with the following command:

```python
sudo nano /etc/systemd/system/zookeeper.service
```

Enter the following unit definition into the file:

```bash
[Unit]
Requires=network.target remote-fs.target
After=network.target remote-fs.target

[Service]
Type=simple
User=kafka
ExecStart=/home/kafka/kafka/bin/zookeeper-server-start.sh /home/kafka/kafka/config/zookeeper.properties
ExecStop=/home/kafka/kafka/bin/zookeeper-server-stop.sh
Restart=on-abnormal

[Install]
WantedBy=multi-user.target
```

The `[Unit]` section specifies that Zookeeper requires networking and the filesystem to be ready before it can start.  

The `[Service]` section specifies that systemd should use the `zookeeper-server-start.sh`and `zookeeper-server-stop.sh` shell files for starting and stopping the service. It also specifies that Zookeeper should be restarted if it exits abnormally.  

After adding this content, save and close the file.  

Next, create the systemd service file for `kafka` with following command:

```python
sudo nano /etc/systemd/system/kafka.service
```

Enter the following unit definition into the file:

```bash
[Unit]
Requires=zookeeper.service
After=zookeeper.service

[Service]
Type=simple
User=kafka
ExecStart=/bin/sh -c '/home/kafka/kafka/bin/kafka-server-start.sh /home/kafka/kafka/config/server.properties > /home/kafka/kafka/kafka.log 2>&1'
ExecStop=/home/kafka/kafka/bin/kafka-server-stop.sh
Restart=on-abnormal

[Install]
WantedBy=multi-user.target
```

The `[Unit]` section specifies that this unit file depends on `zookeeper.service`. This will ensure that `zookeeper` gets started automatically when the `kafka` service starts.

The `[Service]` section specifies that systemd should use the `kafka-server-start.sh` and `kafka-server-stop.sh` shell files for starting and stopping the service. It also specifies that Kafka should be restarted if it exits abnormally.

Once the units are defined, start Kafka with the following command:

```bash
sudo systemctl start kafka
```

To ensure that the server has started successfully, check the journal logs for the `kafka` unit:

```bash
sudo systemctl status kafka
```

Now the `kafka` service is active and running.  
But if when rebooted the server, Kafka will not restart automatically.  
To enable the `kafka` service on server boot, run the following commands:

```bash
sudo systemctl enable zookeeper
sudo systemctl enable kafka
```

In this step, the `kafka` and `zookeeper` services have been started and enabled.  
Now let's test if the kafka installation has been successfull.

**Step 5 — Testing the Kafka Installation**

In this step, the Kafka installation will be tested by publishing and consuming a **“Hello World”** message to make sure the Kafka server is behaving correctly.

Publishing messages in Kafka requires:

- A **producer**, who enables the publication of records and data to topics.
- A **consumer**, who reads messages and data from topics.

To begin, create a topic named `TutorialTopic`:

```bash
~/kafka/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic TutorialTopic
```

Now publish the string `"Hello, World"` to the `TutorialTopic` topic:

```bash
echo "Hello, World" | ~/kafka/bin/kafka-console-producer.sh --broker-list localhost:9092 --topic TutorialTopic > /dev/null
```

Next, create a Kafka consumer using the `kafka-console-consumer.sh` script. It expects the ZooKeeper server’s hostname and port, along with a topic name as arguments. The following command consumes messages from `TutorialTopic`. Note the use of the `--from-beginning` flag, which allows the consumption of messages that were published before the consumer was started:

```bash
~/kafka/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic TutorialTopic --from-beginning
```

If there are no configuration issues, `Hello, World` will appear in your terminal:

```bash
Output
Hello, World
```

**Step 6 — Hardening the Kafka Server**

After the installation is complete, it is possible to remove the `kafka` user’s admin privileges. Before doing so, log out and log back in as any other non-root sudo user. If running on the same shell session that you started this tutorial with, type `exit`

Remove the `kafka` user from the sudo group:

```bash
sudo deluser kafka sudo
```

To further improve your Kafka server’s security, lock the `kafka` user’s password using the `passwd` command.  
This makes sure that nobody can directly log into the server using this account:

```bash
sudo passwd kafka -l
```

At this point, only root or a sudo user can log in as `kafka` by typing in the following command:

```bash
sudo su - kafka
```

<br />  
  <h3 id="mqtt-dumper">MQTT Dumper</h3>
  <h3 id="mongodb-1">MongoDB</h3>
  <h3 id="mongodb-sink-connector">MongoDB Sink Connector</h3>
  <h3 id="presto">Presto</h3>
  
## Usage

  <h3 id="iot-simulator-1">IoT Simulator</h3>
  <h3 id="mqtt-dumper-1">MQTT Dumper</h3>
  <h3 id="presto-1">Presto</h3>
  <h3 id="jupyter-1">Jupyter Connection</h3>

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
