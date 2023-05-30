package it.unicam.tbdm.kafka;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.ValueMapper;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;

import java.util.Properties;

/**

    This is a Java class that represents a Kafka streaming application.

    It reads messages from the "mqtt.echo" topic, splits the messages into two parts,

    and sends each part to different output topics ("mqtt.main" and "mqtt.measures").
    
*/
public class KafkaStreamApp {

    public static void main(String[] args) {
		
		// Configure the Kafka Streams application
		Properties config = new Properties();
		config.put(StreamsConfig.APPLICATION_ID_CONFIG, "message-splitter");
		config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		
		// Create a StreamsBuilder instance
		StreamsBuilder builder = new StreamsBuilder();
		
		// Create a KStream that reads from the "mqtt.echo" topic
		KStream<String, String> sourceStream = builder.stream("mqtt.echo");

 	   	// Create a ValueMapper to extract the first part of the message
        ValueMapper<String, String> iotmessageMapper = new IoTMessageMapper();
		
		// Create a ValueMapper to extract measures from the message as an Iterable
		ValueMapper<String, Iterable<String>> measuresMapper = new MeasuresMessageMapper();

 	   	// Apply the iotmessageMapper to the sourceStream and send the iot message, excluded of the measures, to the "mqtt.main" topic	
        sourceStream
            .mapValues(iotmessageMapper)
            .to("mqtt.main", Produced.with(Serdes.String(), Serdes.String()));

 	   	// Apply the secondPartMapper to the sourceStream and send each measures included in a single iot message to the "mqtt.measures" topic
        sourceStream
			.flatMapValues(measuresMapper)
            .to("mqtt.measures", Produced.with(Serdes.String(), Serdes.String()));

 	   	// Create a KafkaStreams instance with the built StreamsBuilder and configuration
		KafkaStreams streams = new KafkaStreams(builder.build(), config);
		
		// Start the Kafka Streams application
		streams.start();

		// Add shutdown hook to close the streams application gracefully
		Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }
}