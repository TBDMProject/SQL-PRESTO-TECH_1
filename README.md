# SQL-PRESTO-TECH

This project was developed as an assignment for the examination of Big Data Management Technologies, a course in the master's degree programme in computer science at the University of Camerino.

### Table of Contents:
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
1. [Results](#results)  
    1. [Line Chart](#linechart-1)
    1. [Bar Chart](#barchart-1)
    1. [Pie Chart](#piechart-1)
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
* `Python v.3`
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
<p align="right">(<a href="#table-of-contents">back to top ⬆️</a>)</p>

<h3 id="mqtt-dumper">MQTT Dumper</h3>

****Requirements****

Install also this package (JSON processor):

```bash
sudo apt-get install jq
```

****MQTT plugin installation****

Download source package from [https://www.confluent.io/hub/confluentinc/kafka-connect-mqtt](https://www.confluent.io/hub/confluentinc/kafka-connect-mqtt) and unpack in some folder MQTT_CONNECTOR.

```bash
apt install unzip
wget https://d1i4a15mxbxib1.cloudfront.net/api/plugins/confluentinc/kafka-connect-mqtt/versions/1.6.0/confluentinc-kafka-connect-mqtt-1.6.0.zip
unzip confluentinc-kafka-connect-mqtt-1.6.0.zip
```

Copy the content of  confluentinc-kafka-connect-mqtt-1.6.0/lib into /.../kafka

```bash
sudo mkdir -p /.../kafka/plugins/mqtt-connector
cd confluentinc-kafka-connect-mqtt-1.6.0/lib
sudo cp -R *  /.../kafka/plugins/mqtt-connector
```

Also set the plugin directory by editing the file `/.../kafka/config/connect-distributed.properties`

```bash
plugin.path=/.../kafka/plugins
```

Start/restart the distributed connector and check if the plugin is now available:

```bash
curl localhost:8083/connector-plugins | jq
```

If the output comprehends `io.confluent.connect.mqtt.MqttSinkConnector` then you are good to go.

****Start connector****

It is assumed that Kafka is already running and listening on port 9092. Set also in console the following variable.

```bash
KAFKA_HOME=/.../kafka
```

It is possible to start a standalone connector that takes in input a config file with main parameters or a distributed connector that will wait for incoming request via REST API calls.  
For using a distribute connector run the following:

```bash
$KAFKA_HOME/bin/connect-distributed.sh $KAFKA_HOME/config/connect-distributed.properties
```

Then open a new terminal to interact with the connector. For example type the following curls:

```bash
curl localhost:8083/ | jq
curl localhost:8083/connector-plugins | jq
curl localhost:8083/connectors
```

****Run MQTT connector****

To run the connector, define a configuration as JSON file to submit to the worker connector. Save it as `~/mqtt_connect.json`

```json
{
  "name": "mqtt-source",
  "config": {
    "connector.class": "io.confluent.connect.mqtt.MqttSourceConnector",
    "tasks.max": "1",
    "mqtt.server.uri": "tcp://localhost:1883",
    "mqtt.topics": "#",
    "kafka.topic": "mqtt.echo",
    "value.converter":"org.apache.kafka.connect.converters.ByteArrayConverter",
    "key.converter":"org.apache.kafka.connect.storage.StringConverter",
    "key.converter.schemas.enable" : "false",
    "value.converter.schemas.enable" : "false",
    "confluent.topic.bootstrap.servers": "localhost:9092",
    "confluent.topic.replication.factor": "1",
    "confluent.license": ""
  }
}
```

Then submit to the worker:

```bash
curl -s -X POST -H 'Content-Type: application/json' http://localhost:8083/connectors -d @/home/mqtt_connect.json
```

Verify that it is working:

```bash
curl -s "http://localhost:8083/connectors"
```

****Dump messages****

From the console if you subscribe with a simple consumer messages sent to the broker should be visualized.

```bash
$KAFKA_HOME/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic mqtt.echo --from-beginning
```
<p align="right">(<a href="#table-of-contents">back to top ⬆️</a>)</p>

<h3 id="mongodb-1">MongoDB</h3>

**Step 1 — Installing MongoDB**

To start, import the public GPG key for the latest stable version of MongoDB by running the following command.

```bash
curl -fsSL https://www.mongodb.org/static/pgp/server-4.4.asc | sudo apt-key add -
```

cURL is a command line tool available on many operating systems used to transfer data. It reads whatever data is stored at the URL passed to it and prints the content to the system’s output. In the following example, cURL prints the content of the GPG key file and then pipes it into the following `sudo apt-key add -` command, thereby adding the GPG key to your list of trusted keys.

Also, note that this `curl` command uses the options `-fsSL` which, together, essentially tell cURL to fail silently. This means that if for some reason cURL isn’t able to contact the GPG server or the GPG server is down, it won’t accidentally add the resulting error code to your list of trusted keys.

This command will return `OK` if the key was added successfully:

```bash
Output
OK
```

To double check that the key was added correctly, run the following command:

```bash
apt-key list
```

This will return the MongoDB key somewhere in the output:

```bash
Output
/etc/apt/trusted.gpg
--------------------
pub   rsa4096 2019-05-28 [SC] [expires: 2024-05-26]
      2069 1EEC 3521 6C63 CAF6  6CE1 6564 08E3 90CF B1F5
uid           [ unknown] MongoDB 4.4 Release Signing Key <packaging@mongodb.com>
. . .
```

At this point, the APT installation still doesn’t know where to find the `mongodb-org` package required to install the latest version of MongoDB.

```bash
echo "deb [ arch=amd64,arm64 ] https://repo.mongodb.org/apt/ubuntu focal/mongodb-org/4.4 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-4.4.list
```

This single line tells APT everything it needs to know about what the source is and where to find it:

- `deb`: This means that the source entry references a regular Debian architecture. In other cases, this part of the line might read `deb-src`, which means the source entry represents a Debian distribution’s source code.
- `[ arch=amd64,arm64 ]`: This specifies which architectures the APT data should be downloaded to. In this case, it specifies the `amd64` and `arm64` architectures.
- `https://repo.mongodb.org/apt/ubuntu`: This is a URI representing the location where the APT data can be found. In this case, the URI points to the HTTPS address where the official MongoDB repository is located.
- `focal/mongodb-org/4.4`: Ubuntu repositories can contain several different releases. This specifies that you only want version `4.4` of the `mongodb-org` package available for the `focal` release of Ubuntu (“Focal Fossa” being the code name of Ubuntu 20.04).
- `multiverse`: This part points APT to one of the four main Ubuntu repositories. In this case, it’s pointing to the `[multiverse` repository](https://help.ubuntu.com/community/Repositories#Multiverse).

After running this command, update your server’s local package index so APT knows where to find the `mongodb-org` package:

```bash
sudo apt update
```

Following that, install MongoDB running:

```bash
sudo apt install mongodb-org
```

**Step 2 — Starting the MongoDB Service and Testing the Database**

Run the following `systemctl` command to start the MongoDB service:

```bash
sudo systemctl start mongod.service
```

Then check the service’s status.

```bash
sudo systemctl status mongod
```

After confirming that the service is running as expected, enable the MongoDB service to start up at boot:

```bash
sudo systemctl enable mongod
```

Verify that the database is operational by connecting to the database server and executing a diagnostic command.

```bash
mongo --eval 'db.runCommand({ connectionStatus: 1 })'
```

`connectionStatus` will check and return the status of the database connection

```bash

Output
MongoDB shell version v4.4.0
connecting to: mongodb://127.0.0.1:27017/?compressors=disabled&gssapiServiceName=mongodb
Implicit session: session { "id" : UUID("1dc7d67a-0af5-4394-b9c4-8a6db3ff7e64") }
MongoDB server version: 4.4.0
{
	"authInfo" : {
		"authenticatedUsers" : [ ],
		"authenticatedUserRoles" : [ ]
	},
	"ok" : 1
}
```
<p align="right">(<a href="#table-of-contents">back to top ⬆️</a>)</p>

<h3 id="mongodb-sink-connector">MongoDB Sink Connector</h3>

****Mongo db Sink plugin installation****

Download source package from:

> [https://www.confluent.io/hub/mongodb/kafka-connect-mongodb](https://www.confluent.io/hub/mongodb/kafka-connect-mongodb)
> 

Now open the file and **copy** the **.jar** file from the **/lib folder** and move it inside the folder of the virtual machine `/.../kafka/plugins/mongodb-connector` using a file manager ssh(es. CyberDuck).

To run the connector, define a configuration as JSON file to submit to the worker connector. Save it as `~/mongodb_connect.json`

```bash
{
   "name":"mongodb-sink",
   "config":{
      "connector.class":"com.mongodb.kafka.connect.MongoSinkConnector",
      "tasks.max":1,
      "topics":"mqtt.echo",
      "connection.uri":"mongodb://localhost:27017/database?retryWrites=true&w=majority",
      "database":"tbdmproject",
      "collection":"iotsimulator",
      "key.converter":"org.apache.kafka.connect.storage.StringConverter",
      "value.converter":"org.apache.kafka.connect.json.JsonConverter",
      "value.converter.schemas.enable":"false"
   }
}
```

Then submit to the worker:

```bash
curl -s -X POST -H 'Content-Type: application/json' http://localhost:8083/connectors -d @/home/mongodb_connect.json
```

Verify that it is working:

```bash
curl -s "http://localhost:8083/connectors"
```

To delete a connector, you can run:

```bash
curl -X DELETE http://localhost:8083/connectors/<connector-name>
```
<p align="right">(<a href="#table-of-contents">back to top ⬆️</a>)</p>

<h3 id="presto">Presto</h3>

**Installing Presto**

Download the Presto server tarball

```bash
mkdir /.../presto && cd /.../presto
wget https://repo1.maven.org/maven2/com/facebook/presto/presto-server/0.279/presto-server-0.279.tar.gz
```

Unpack it. The tarball will contain a single top-level directory, `presto-server-0.278.1`, which we will call the *installation* directory.

```bash
tar -xzf presto-server-0.279.tar.gz
mv presto-server-0.279 presto-server
```

Presto needs a *data* directory for storing logs, etc. It is recommended to create a data directory outside of the installation directory, which allows it to be easily preserved when upgrading Presto.

```bash
mkdir /.../presto/presto-data
```

**Configuring Presto**

Create an `etc` directory inside the installation directory. This will hold the following configuration:

- Node Properties: environmental configuration specific to each node
- JVM Config: command line options for the Java Virtual Machine
- Config Properties: configuration for the Presto server. See the **[Properties Reference](https://prestodb.io/docs/current/admin/properties.html)** for available configuration properties.
- Catalog Properties: configuration for **[Connectors](https://prestodb.io/docs/current/connector.html)** (data sources). The available catalog configuration properties for a connector are described in the respective connector documentation.

**Node Properties**

The node properties file, `etc/node.properties`, contains configuration specific to each node.

```bash
sudo nano etc/node.properties
```

And paste:

```bash
node.environment=production
node.id=ffffffff-ffff-ffff-ffff-ffffffffffff				
node.data-dir=/home/presto/presto-data
```

Change the node.id parameter with the uuid taken from MongoDB visible using the command:

```bash
mongo --eval 'db.runCommand({ connectionStatus: 1 })'
```

**JVM Config**

The JVM config file, `etc/jvm.config`, contains a list of command line options used for launching the Java Virtual Machine.

The following provides a good starting point for creating `etc/jvm.config`

```bash
-server
-Djdk.attach.allowAttachSelf=true
-Xmx16G
-XX:+UseG1GC
-XX:G1HeapRegionSize=32M
-XX:+UseGCOverheadLimit
-XX:+ExplicitGCInvokesConcurrent
-XX:+HeapDumpOnOutOfMemoryError
-XX:+ExitOnOutOfMemoryError
```

**Config Properties**

The config properties file, `etc/config.properties`, contains the configuration for the Presto server. Every Presto server can function as both a coordinator and a worker, but dedicating a single machine to only perform coordination work provides the best performance on larger clusters. Use this configuration:

```bash
coordinator=true
node-scheduler.include-coordinator=true
http-server.http.port=8090
query.max-memory=5GB
query.max-memory-per-node=1GB
discovery-server.enabled=true
discovery.uri=http://127.0.0.1:8090
```

**Log Levels**

The optional log levels file, `etc/log.properties`, allows setting the minimum log level for named logger hierarchies. Every logger has a name, which is typically the fully qualified name of the class that uses the logger. Loggers have a hierarchy based on the dots in the name (like Java packages). For example, consider the following log levels file:

```bash
com.facebook.presto=INFO
```
**Catalog Properties**

Presto accesses data via *connectors*, which are mounted in catalogs. The connector provides all of the schemas and tables inside of the catalog.

Catalogs are registered by creating a catalog properties file in the `etc/catalog`directory. For example, create `etc/catalog/jmx.properties`

So **create the directory to attach later the mongo db connector**

****MongoDB Connector****

This connector allows the use of MongoDB collections as tables in Presto.

To configure the MongoDB connector, create a catalog properties file `etc/catalog/mongodb.properties` with the following contents, replacing the properties as appropriate:

```bash
connector.name=mongodb
mongodb.seeds=127.0.0.1:27017
```

****Command Line Interface****

Download [presto-cli-0.279-executable.jar](https://repo1.maven.org/maven2/com/facebook/presto/presto-cli/0.279/presto-cli-0.279-executable.jar), rename it to `presto`, make it executable with `chmod +x`, then run it:

```bash
wget https://repo1.maven.org/maven2/com/facebook/presto/presto-cli/0.279/presto-cli-0.279-executable.jar
mv presto-cli-0.279-executable.jar /.../presto/presto-server/presto
chmod +x presto
```
<p align="right">(<a href="#table-of-contents">back to top ⬆️</a>)</p>


## Usage

<h3 id="iot-simulator-1">IoT Simulator</h3>

For starting the IoTSimulator run the following command:

```bash
/.../iot-simulator/docker-compose docker-compose up -d
```
Typing **-d** allows to start the IoTSimulator in background, otherwise a log will be shown with the messages as they are being generated.  


While for stopping the IoTSimulator use:

```bash
/.../iot-simulator/docker-compose docker-compose down
```  
  <h3 id="mqtt-dumper-1">MQTT Dumper</h3>
  	
Assuming that Kafka is already running and listening on port 9092, it is necessary to set the following variable.

```bash
KAFKA_HOME=/.../yourkafkapath
```

Then start a standalone connector that takes in input a distributed connector that will wait for incoming request via REST API calls.

```bash
$KAFKA_HOME/bin/connect-distributed.sh $KAFKA_HOME/config/connect-distributed.properties
```  

<h3 id="presto-1">Presto</h3>
The installation directory contains the launcher script in `bin/launcher`. Presto can be started as a daemon by running the following:

```bash
bin/launcher start
```

Alternatively, it can be run in the foreground, with the output such as logs being shown:

```bash
bin/launcher run
```
In order to perform queries the Presto CLI can be used by running the following command:

```bash
./presto --server 127.0.0.1:8090 --catalog mongodb --schema tbdmproject
```

While for stopping Presto simply use:

```bash
bin/launcher stop
```  

<h3 id="jupyter-1">Jupyter Connection</h3>
In order to establish a connection towards Presto it is possible to use the *pyhive* library by running the following:
```python
from pyhive import presto

presto_conn = presto.connect(
    host='000.000.000.000', #ip address of your virtual machine
    port=8090, #port on which you installed presto
    catalog='mongodb',
    schema='yourschema' #specify the schema in which you are interested in
)
presto_cur = presto_conn.cursor()
```  
Then to execute a simple query:
```bash
presto_cur.execute("select * from yourtable")
records=presto_cur.fetchall()
``` 
<p align="right">(<a href="#table-of-contents">back to top ⬆️</a>)</p>

## Results

<h3 id="linechart-1">Line Chart</h3>

The following graph shows the temperature measured by a device over a set period of time:  
<p align="center">
  <img src="https://github.com/TBDMProject/SQL-PRESTO-TECH_1/blob/images/linechart.png?raw=true" alt="alt text" width="850"/>
</p>  

<h3 id="barchart-1">Bar Chart</h3>

This bar chart shows the average of the temperatures measured by each device:  
<p align="center">
  <img src="https://github.com/TBDMProject/SQL-PRESTO-TECH_1/blob/images/barchart.png?raw=true" alt="alt text" width="850"/>
</p>  

<h3 id="piechart-1">Pie Chart</h3>

While this graph shows the distribution of the measurements based on the device type:
<p align="center">
  <img src="https://github.com/TBDMProject/SQL-PRESTO-TECH_1/blob/images/piechart.png?raw=true" alt="alt text" width="850"/>
</p>

<p align="right">(<a href="#table-of-contents">back to top ⬆️</a>)</p>

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
