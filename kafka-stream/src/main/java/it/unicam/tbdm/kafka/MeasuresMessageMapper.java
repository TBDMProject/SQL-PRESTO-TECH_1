package it.unicam.tbdm.kafka;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import org.apache.kafka.streams.kstream.ValueMapper;

/**
    This is a Java class that implements the ValueMapper interface to extract

    the second part of a message and modify it to include a UUID value in each JSON object.
*/
public class MeasuresMessageMapper implements ValueMapper<String, Iterable<String>> {

	private String uuidValue;
	private String measures;
	
	@Override
	public Iterable<String> apply(String value) {
		try{
			// Parse the message string into a JSON object		
			JSONObject jsonObj = new JSONObject(value);
		
			// Extract the value of the "uuid" property from the original message
			this.uuidValue = jsonObj.optString("uuid", null);
		
			// Extract the measures part of the message
			this.measures = jsonObj.optString("m", null);
		
	 	   	// Perform the FlatMapValues logic
			// Convert the measures part into a JSONArray
	 		JSONArray jsonArray = new JSONArray(this.measures);

	 	    // Create a list to store the transformed JSON objects
			List<String> jsonObjects = new ArrayList<>();
		
			// Iterate over each JSON object in the JSONArray
			for (int i = 0; i < jsonArray.length(); i++) {
				// Get the current JSON object
				JSONObject jsonObject = jsonArray.getJSONObject(i);
			
				// Add the UUID value to the JSON object
				jsonObject.put("uuid", uuidValue);
			
				// Convert the JSON object back to a string and add it to the list
				jsonObjects.add(jsonObject.toString());
			}
		
			// Return the list of transformed JSON objects
			return jsonObjects;
		} catch (Exception e) {
            // Handle any exceptions that occur during processing
            e.printStackTrace();
            // Return the initial value in case of an error so the table is not compromised
            return null;
        }
		
	}
	
}