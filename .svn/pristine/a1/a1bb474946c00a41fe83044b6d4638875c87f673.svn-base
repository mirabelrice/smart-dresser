package com.fashion.aid;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.json.JSONException;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CreateOutfit extends Activity {

	private ArrayList<Outfit> possibleOutfits = new ArrayList<Outfit>();
	private ArrayList<Outfit> generatedOutfits = new ArrayList<Outfit>();
	private int outfitTracker = 0; // keeps track of outfit position
	private ListView itemListView;
	private ItemImageAdapter itemImgAdptr;
	private ImageButton leftArrImgButton, rightArrImgButton, menuImgButton;
	private Button pickNewButton, wearButton;
	private long keyItemId = 0;
	private Toast toast;
	private String caller = "Closet";
	private LocationManager locationManager;
	private String provider;
	public double latitude;
	public double longitude;
	public WeatherGetter WG;
	public Weather weather;
	public Location location;
	public MyLocationListener locationListener;
	public String jsonStr;
	public Singleton mySingleton;
	public float tempFromResult;

	@Override
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.create_outfit_screen);
	        
	        mySingleton = Singleton.getSingletonObject();
	        
	      //------Weather Widget--------
	        
	     // Get the location manager
	        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	        // Define the criteria how to select the location provider 
	        // default
	        Criteria criteria = new Criteria();
	        
	        //location listener
	        LocationListener locationListener = new MyLocationListener();
	        
	        //check if providers are enabled
	        boolean enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
	        
	        Log.v("Location Manager", "Provider enabled: " + enabled);
	        
	        
	        provider = locationManager.getBestProvider(criteria, true);
	        Log.v("Location Manager","Provider " + provider + " has been selected.");
	        
	       locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,10000,0, locationListener);
	 
	        location = new Location(provider);
	        
	        if (provider != null){
	        try {
	        	
				location = locationManager.getLastKnownLocation(provider);
				Log.v("Location Manager", "Location: " + location);
				
			} catch (Exception e) {
				location.setLatitude(90.0000);
	        	location.setLongitude(0.0000); //<< Antarctica
	        	Log.v("Location","is Antarctica");
			}
	        }
	        else {
	        	location.setLatitude(90.0000);
	        	location.setLongitude(0.0000); //<< Antarctica
	        	Log.v("Location","is Antarctica. No provider.");
	        }
	        
              WG = new WeatherGetter();
	          weather = new Weather();
	          
	        // get users current location
	        if (location != null) {
	          latitude = location.getLatitude();
	          longitude = location.getLongitude();
	          Log.v("Location Manager","Latitude: " + latitude + ", Longitude: " + longitude);
	          
	          //find weather for location 
	          
	         try {
				new asynch().get(1000, TimeUnit.MILLISECONDS);
			} catch (Exception e) {
	          e.printStackTrace();
	        }
	        }
	         else {
	          Log.v("Location Manager", "Location not available");
	        }
	      //---------End Widget---------  
	        
	      //gets Item from previous class
		  Bundle data = getIntent().getExtras();
			if(data != null) {
				keyItemId = data.getLong("rowId");
				caller = data.getString("caller");
			}
			
			if(caller.equals("Home")) {
				DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
				    @Override
				    public void onClick(DialogInterface dialog, int which) {
				        switch (which){
				        case DialogInterface.BUTTON_POSITIVE:
				            //pick item button clicked
							
							//goes back to view closet
							Intent viewClosetIntent = new Intent(CreateOutfit.this, ViewCloset.class);
							viewClosetIntent.putExtra("action", "Create Outfit");
							viewClosetIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
							startActivity(viewClosetIntent);

				        case DialogInterface.BUTTON_NEGATIVE:
				            //No button clicked
				            break;
				        }
				    }
				};

				AlertDialog.Builder builder = new AlertDialog.Builder(CreateOutfit.this);
				builder.setMessage("Pick an Item to build from or Randomly Generate an Outfit").setPositiveButton("Pick an Item", dialogClickListener)
				    .setNegativeButton("Random Outfit", dialogClickListener).show();

			}
				        
	        //gets all outfits with given item 
	    	ItemDataBase itemDB = new ItemDataBase(CreateOutfit.this);
	    	itemDB.open();
	    	Item selectedItem = itemDB.getItem(keyItemId);
	    	
	    	
	    	int weatherWeight = 0;
	    	
	    	Log.i("Weahter", tempFromResult+"");
	    	
	    	if(tempFromResult < 65.0) {
	    		weatherWeight = 1;
	    	}
	    	else if(tempFromResult > 80.0) {
	    		weatherWeight = 2;
	    	}
	    	
	    	//if no item was specified
	    	if(keyItemId == 0) {
	    		possibleOutfits = itemDB.getAllOutfits(weatherWeight);
	    	}
	    	else {
	    		possibleOutfits = itemDB.getOutfitsWithKey(selectedItem.getType()+"ID", keyItemId, weatherWeight);
	    	}
	    	itemDB.close();
	        
	       //display images in ListView
	    	if(possibleOutfits != null) {
	    		generatedOutfits.addAll(possibleOutfits);
	    		possibleOutfits.clear();
	    		displayOutfit();
	    	}
	    	
	       //next outfit
	       rightArrImgButton = (ImageButton) findViewById(R.id.right_arrow_imgButton);
	       rightArrImgButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				outfitTracker++;
				Log.i("Outfit Tracker", outfitTracker+"");
				Log.i("Generated Outfits Size", generatedOutfits.size()+"");
				if(outfitTracker < generatedOutfits.size()) {
					displayOutfit();
				}
				else {
					outfitTracker--;
					toast = Toast.makeText(CreateOutfit.this, "No more outfit suggestions available", Toast.LENGTH_SHORT);
					toast.show();
				}

			}
				
		});
	       
	       //previous outfit
	       leftArrImgButton = (ImageButton) findViewById(R.id.left_arrow_imgButton);
	       leftArrImgButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				outfitTracker--;
				if(outfitTracker >= 0) {
					displayOutfit();
				}
				else {
					outfitTracker++;
					toast = Toast.makeText(CreateOutfit.this, "No more outfit suggestions available", Toast.LENGTH_LONG);
					toast.show();
				}
			}
		});
	       
	       pickNewButton = (Button) findViewById(R.id.pickNewItem_button);
	       pickNewButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent viewClosetIntent = new Intent(CreateOutfit.this, ViewCloset.class);
				startActivity(viewClosetIntent);
				
			}
		});
	       
	       wearButton = (Button) findViewById(R.id.wear_button);
	       wearButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//sets outfit date
				Calendar currentDate = Calendar.getInstance();
				SimpleDateFormat fullDateFormat= new SimpleDateFormat("MM/dd/yyyy");
				final String fullDate = fullDateFormat.format(currentDate.getTime());
				final Outfit outfit = generatedOutfits.get(outfitTracker);
				outfit.setDateWorn(fullDate);

				//update db on separate thread
				new Runnable() {
					public void run() {
						ItemDataBase itemDB = new ItemDataBase(
								CreateOutfit.this);
						itemDB.open();
						itemDB.createHistoryEntry(outfit.getId(),
								outfit.getDateWorn());
						itemDB.updateOutfitEntry(outfit.getId(), outfit
								.getTop().getId(), outfit.getBottom().getId(),
								outfit.getDress().getId(),outfit.getColorWeight(), outfit.getWeatherWeight(), outfit.getHistoryWeight());
						itemDB.close();
					}
				};
				
				//starts view history class
				Intent feedbackIntent = new Intent(CreateOutfit.this, CreateOutfitFeedback.class);
				feedbackIntent.putExtra("outfitId", outfit.getId());
				startActivity(feedbackIntent);
			}
		});
	       
	       menuImgButton = (ImageButton) findViewById(R.id.menu_createOutfit_button);
	       menuImgButton.setOnClickListener(new View.OnClickListener() {
	  		
	  		@Override
	  		public void onClick(View v) {
	  				Intent menuIntent = new Intent(CreateOutfit.this, FashionAidHome.class);
	  				menuIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
	  				startActivity(menuIntent);
	  		}
	  	}); 
	       
	        
	 }

	// displays item in current outfit
	private void displayOutfit() {
		itemListView = (ListView) findViewById(R.id.create_outfit_list);
		itemImgAdptr = new ItemImageAdapter(CreateOutfit.this);
		ArrayList<Item> items = new ArrayList<Item>();
		if (!generatedOutfits.isEmpty()) {
			items = generatedOutfits.get(outfitTracker).getItemsInOutfit();

		}
		itemImgAdptr.setItemList(items);
		itemListView.setAdapter(itemImgAdptr);

	}
	
	 
	private class asynch extends AsyncTask<Void, Void, Weather> {

		//do http request, get json data, make weather obj
	    protected Weather doInBackground(Void...arg0) {
	    	Log.v("WebServiceThread", "Thread started.");
       	    jsonStr = WG.getWeatherData(latitude, longitude);
       	    Log.v("WebServiceThread","JSON data: " + jsonStr);
       	 try {
       		    tempFromResult = WG.parseData(jsonStr).getTempFloat();
				return WG.parseData(jsonStr); //parse data
				
			} catch (JSONException e) {
				Log.e("JSONParser", "Unable to receive JSON data from web service");
			}
		return null; 
	    }
	    

	    protected void onPostExecute(Weather result) {
	    	 //update textviews and icon with weather info
	    	  
	          TextView weatherText = (TextView) findViewById(R.id.textView2);
	          TextView tempText = (TextView) findViewById(R.id.textView3);
	          ImageView icon = (ImageView)findViewById(R.id.imageView1);
	          
	          weatherText.setText(result.getDescription());
	          tempText.setText(result.getTemp());
	          
	          
	          //get custom icon for weather!
	          String iconId = result.getIcon();
	          
	          if (iconId == "01d"){
	          Drawable d = getResources().getDrawable(R.drawable.sun);
	          icon.setImageDrawable(d);}
	          
	          else if (iconId == "02d"){
		          Drawable d = getResources().getDrawable(R.drawable.partlycloudy);
		          icon.setImageDrawable(d);}
	          
	          else if (iconId == "03d"){
		          Drawable d = getResources().getDrawable(R.drawable.cloudy);
		          icon.setImageDrawable(d);}
	          
	          else if (iconId == "04d"){
		          Drawable d = getResources().getDrawable(R.drawable.stormscomin);
		          icon.setImageDrawable(d);}
	          
	          else if (iconId == "09d"){
		          Drawable d = getResources().getDrawable(R.drawable.rain_rain);
		          icon.setImageDrawable(d);}
	          
	          else if (iconId == "10d"){
		          Drawable d = getResources().getDrawable(R.drawable.sun_rain);
		          icon.setImageDrawable(d);}
	          
	          else if (iconId == "11d"){
		          Drawable d = getResources().getDrawable(R.drawable.thunder);
		          icon.setImageDrawable(d);}
	          
	    }
	}

}
