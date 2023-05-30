package it.unicam.tbdm.kafka;

import org.json.JSONObject;

import org.apache.kafka.streams.kstream.ValueMapper;

/**
    This is a Java class that implements the ValueMapper interface to extract

    the first part of an IoT message by removing the "m"(measures) property from the JSON object.
*/
public class IoTMessageMapper implements ValueMapper<String, String> {
		
	@Override
	public String apply(String value) {
		try{
			// Parse the message string into a JSON object
			JSONObject jsonObj = new JSONObject(value);
		
			// Remove the "measures" property from the JSON object
			jsonObj.remove("m");
		
			// Convert the modified JSON object back to a string
			String firstPart = jsonObj.toString();
		
			// Return the the message without the measures
			return firstPart;
		} catch (Exception e) {
            // Handle any exceptions that occur during processing
            e.printStackTrace();
            // Return the null value in case of an error so the table is not compromised
            return null;
        }
	}
}