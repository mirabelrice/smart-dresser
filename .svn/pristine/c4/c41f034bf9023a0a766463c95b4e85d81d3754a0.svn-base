package com.fashion.aid;

import java.util.LinkedList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.GridView;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;


public class ViewCloset extends Activity {
	private Intent newIntent;
	private ItemDataBase itemDataBase = new ItemDataBase(ViewCloset.this);
	private String filteredStyle = "";
	private String filteredColor = "";
	int numOfItems = 0;
	private Item[] allItemsArray;
	private GridAdapter gridAdapter = new GridAdapter(ViewCloset.this);
	private Spinner styleSpinner;
    private Spinner colorSpinner;
	private GridView gridView;
	private ImageButton menuImgButton;
	private ArrayAdapter<CharSequence> styleAdapter, colorAdapter;
	LinkedList<Item> filteredList = new LinkedList<Item>();
	private String action = "View Item";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view);
		styleSpinner = (Spinner)findViewById(R.id.spinner1);
		colorSpinner = (Spinner)findViewById(R.id.spinner2);
		gridView = (GridView)findViewById(R.id.gridview);
		filteredStyle = "All Styles";
		filteredColor = "All Colors";
		
		 //determines whether to add to edit item
	     Bundle data = getIntent().getExtras();
			if(data != null) {
				action = data.getString("action");			
			} 
		
		//get item objects from database
		itemDataBase.open();
		allItemsArray = itemDataBase.getAllItems();
		numOfItems = allItemsArray.length;
		itemDataBase.close();
		
		//if there are items in database, display all items
		if(numOfItems >= 1){
			displayAllImages();
		}
		
		//Center spinner text
		styleSpinner = (Spinner) findViewById(R.id.spinner1);
	    styleAdapter = ArrayAdapter.createFromResource(this,
	    		 R.array.styleArray, R.layout.my_spinner_text_view);
	    styleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    styleSpinner.setAdapter(styleAdapter);
		
	    colorSpinner = (Spinner) findViewById(R.id.spinner2);
	    colorAdapter = ArrayAdapter.createFromResource(this,
	    		 R.array.colorArray, R.layout.my_spinner_text_view);
	    colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    colorSpinner.setAdapter(colorAdapter);
	
		//jumps to view item screen when image is clicked
		gridView.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> parent, View view, int position, long id){
				
				if(action.equals("Create Outfit") && (position == 1)) {
					DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
					    @Override
					    public void onClick(DialogInterface dialog, int which) {
					        switch (which){
					        case DialogInterface.BUTTON_POSITIVE:
					            //pick item button clicked
								
								//goes back to view closet
								Intent viewClosetIntent = new Intent(ViewCloset.this, CreateOutfit.class);
								viewClosetIntent.putExtra("caller", "Closet");
								startActivity(viewClosetIntent);

					        case DialogInterface.BUTTON_NEGATIVE:
					            //No button clicked
					            break;
					        }
					    }
					};

					AlertDialog.Builder builder = new AlertDialog.Builder(ViewCloset.this);
					builder.setMessage("It might be too hot for a sweater. Would you like to pick a new item or continue?").setPositiveButton("Continue", dialogClickListener)
					    .setNegativeButton("Pick New Item", dialogClickListener).show();

				}
				
				//determins whether to go to view item or create outfit
				if(action.equals("View Item")) {
					newIntent = new Intent(ViewCloset.this, ViewItem.class);
				}
				else {
					newIntent = new Intent(ViewCloset.this, CreateOutfit.class);
					newIntent.putExtra("caller", "Closet");
				}
				
			    long row = 0;
			    if(filteredStyle.equals("All Styles") && filteredColor.equals("All Colors")) {
			    	row = allItemsArray[position].getId();
			    }
			    else {
			    	row = filteredList.get(position).getId();
			    }
			    newIntent.putExtra("rowId", row);
			    startActivity(newIntent);
			}
		});
	
		//filters clothes by style when spinner selection is changed
		styleSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,int position, long id){
				Log.i("tag", "style Spinner");
				int selectedStylePosition = styleSpinner.getSelectedItemPosition();
				String tempString = (String) styleSpinner.getItemAtPosition(selectedStylePosition);
				if(!tempString.equals("All Styles")){
					filteredStyle = tempString.substring(0, tempString.length()-1);
				}
				else{
					filteredStyle = tempString;
				}
				filter();			
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}	
		});
		
		//filters clothes by color when color spinner selection is changed
		colorSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,int position, long id){
				Log.i("tag", "color Spinner");
				int selectedColorPosition = colorSpinner.getSelectedItemPosition();
				filteredColor = (String) colorSpinner.getItemAtPosition(selectedColorPosition);
				filter();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub	
			}	
		});	
		
		menuImgButton = (ImageButton) findViewById(R.id.menu_viewCloset_button);
	       menuImgButton.setOnClickListener(new View.OnClickListener() {
	  		
	  		@Override
	  		public void onClick(View v) {
	  				Intent menuIntent = new Intent(ViewCloset.this, FashionAidHome.class);
	  				menuIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
	  				startActivity(menuIntent);
	  		}
	  	});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.view, menu);
		return true;
	}
	
	private void displayAllImages(){
		gridAdapter.setArray(allItemsArray);
		gridView.setAdapter(gridAdapter);	
	}
	
	private void displayFilteredImages(LinkedList<Item> p_itemList){
		Item[] filteredArray = new Item[p_itemList.size()];
		p_itemList.toArray(filteredArray);
		
		gridAdapter.setArray(filteredArray);
		gridView.setAdapter(gridAdapter);	
	}
	
	private void filter(){
		String style = "";
		String color = "";
		while(filteredList.size() != 0) {
			filteredList.remove();
		}
		
		if(filteredStyle.equalsIgnoreCase("All Styles")){
			if(filteredColor.equalsIgnoreCase("All Colors")){
				displayAllImages();
			}else{
				for(int i = 0; i < numOfItems; ++i){
					color = allItemsArray[i].getColor();
					if(color.equalsIgnoreCase(filteredColor)){
						filteredList.add(allItemsArray[i]);
					}
				}
				displayFilteredImages(filteredList);
			}
		}else if(filteredColor.equalsIgnoreCase("All Colors")){
			if(filteredStyle.equalsIgnoreCase("All Styles")){
				displayAllImages();
			}else{
				for(int i = 0; i < numOfItems; ++i){
					style = allItemsArray[i].getType();
					if(style.equalsIgnoreCase(filteredStyle)){
						filteredList.add(allItemsArray[i]);
					}
				}
				displayFilteredImages(filteredList);
			}		
		}else{
			for(int i = 0; i < numOfItems; ++i){
				style = allItemsArray[i].getType();
				color = allItemsArray[i].getColor();
				if((style.equalsIgnoreCase(filteredStyle)) && (color.equalsIgnoreCase(filteredColor))){
					filteredList.add(allItemsArray[i]);
				}
			}
			displayFilteredImages(filteredList);
		}
	}	
}
	

	

	


