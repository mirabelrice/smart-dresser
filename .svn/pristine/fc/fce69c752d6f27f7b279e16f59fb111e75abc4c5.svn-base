package com.fashion.aid;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.location.Location;
import android.util.Log;

public class WeatherGetter {

    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?";
    private Weather weather = new Weather();

    public String getWeatherData(double lat, double lon) {
    	
    	//grabs JSON data from web service api.openweathermap.org, returns it in a string
    	Log.v("WeatherGetter", "WG start.");
    	
        HttpURLConnection con = null ;
        InputStream is = null;
        StringBuffer buffer = new StringBuffer();
        
        try {
            con = (HttpURLConnection) ( new URL(BASE_URL + "lat=" + lat + "&lon=" + lon)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();
            
            Log.v("HTTPClient", "Url connection made!");

            // read response from web service
            
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while (  (line = br.readLine()) != null )
                buffer.append(line + "\r\n");

            is.close();
            con.disconnect();
            Log.v("Web Service", "JSON data read successfully.");
            
            
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }
        
        return buffer.toString();

    }    
    
    public Weather parseData(String data) throws JSONException{
    	
    	// create a JSONObject from the data
		JSONObject jObj = new JSONObject(data);

    	//start extracting the info    	 
    	//weather info is an array
    	JSONArray jArr = jObj.getJSONArray("weather");
    	JSONObject JSONWeather = jArr.getJSONObject(0);
    	
    	//fill in weather obj with info from JSON data
    	weather.setDescription(getString("description", JSONWeather));
    	weather.setIcon(getString("icon", JSONWeather));
    	JSONObject mainObj = getObject("main", jObj);
    	weather.setTemp(getFloat("temp", mainObj));
    	
    	Log.v("JSONParser","Weather Object created!" + "Weather: " + weather.getDescription() + " " + weather.getTemp());
    	
    	return weather;
    	
    }
    
    //helper methods to parse data
    private static JSONObject getObject(String tagName, JSONObject jObj)  throws JSONException {
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }
     
    private static String getString(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getString(tagName);
    }
     
    private static float  getFloat(String tagName, JSONObject jObj) throws JSONException {
        return (float) jObj.getDouble(tagName);
    }
    
}
