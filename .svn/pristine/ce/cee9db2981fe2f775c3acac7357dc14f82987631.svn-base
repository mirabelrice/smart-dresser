package com.fashion.aid;

import android.util.Log;

public class Singleton {
	
	private static Singleton ref;
	private Weather weatherObj;
	
	private Singleton()
    {  
    }

    public static Singleton getSingletonObject()
    {
      if (ref == null)
          // it's ok, we can call this constructor
          ref = new Singleton();
      return ref;
    }

    public void setWeatherObj(Weather w){
    	weatherObj = w;
    	Log.v("Singleton", "WeatherObj set to: " + weatherObj.getDescription() + " " + weatherObj.getTemp());
    }
    
    public Weather getWeatherObj(){
    	return weatherObj;
    }

}
