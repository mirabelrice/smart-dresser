package com.fashion.aid;

import java.util.LinkedList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class Favorites extends Activity {
	
	private Intent newIntent;
	private ItemDataBase itemDataBase = new ItemDataBase(Favorites.this);
	private String filteredStyle = "";
	private String filteredColor = "";
	int numOfItems = 0;
	private Item[] allItemsArray;
	private GridAdapter gridAdapter = new GridAdapter(Favorites.this);
	private Spinner styleSpinner;
    private Spinner colorSpinner;
	private GridView gridView;
	private ImageButton menuImgButton;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.favorites_screen);
		styleSpinner = (Spinner)findViewById(R.id.spinner1);
		colorSpinner = (Spinner)findViewById(R.id.spinner2);
		gridView = (GridView)findViewById(R.id.gridview);
		filteredStyle = "All Styles";
		filteredColor = "All Colors";
		
		//get item objects from database
		itemDataBase.open();
		allItemsArray = itemDataBase.getAllItems();
		numOfItems = allItemsArray.length;
		itemDataBase.close();
		displayAllImages();
		
	
		//jumps to view item screen when image is clicked
		gridView.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> parent, View view, int position, long id){
			    newIntent = new Intent(Favorites.this, ViewItem.class);
			    long row = allItemsArray[position].getId();
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
				filteredStyle = (String) styleSpinner.getItemAtPosition(selectedStylePosition);
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
		
		menuImgButton = (ImageButton) findViewById(R.id.menu_favorites_button);
	       menuImgButton.setOnClickListener(new View.OnClickListener() {
	  		
	  		@Override
	  		public void onClick(View v) {
	  				Intent menuIntent = new Intent(Favorites.this, FashionAidHome.class);
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
		LinkedList<Item> filteredList = new LinkedList<Item>();
		String style = "";
		String color = "";
		
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
