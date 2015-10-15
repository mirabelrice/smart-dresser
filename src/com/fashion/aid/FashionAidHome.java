package com.fashion.aid;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class FashionAidHome extends Activity {
	
	TextView pageTitleView;
	Button addItem, viewCloset, createOutfit, viewHistory, favorites;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        
        //add item
        addItem = (Button) findViewById(R.id.add_item_button);
        addItem.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent addItemIntent = new Intent(FashionAidHome.this, AddItem.class);
				startActivity(addItemIntent);
				
			}
		});
        
        //view items
        viewCloset = (Button) findViewById(R.id.view_item_button);
        viewCloset.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent viewClosetIntent = new Intent(FashionAidHome.this, ViewCloset.class);
				startActivity(viewClosetIntent);
				
			}
		});
        
        //create outfit
        createOutfit = (Button) findViewById(R.id.create_outfit_button);
        createOutfit.setOnClickListener(new View.OnClickListener() {
			
    			@Override
    			public void onClick(View v) {
    				Intent createOutfitIntent = new Intent(FashionAidHome.this, CreateOutfit.class);
    				createOutfitIntent.putExtra("caller", "Home");
    				startActivity(createOutfitIntent);
    			}
    		});
        
        //view history
        viewHistory = (Button) findViewById(R.id.view_history_button);
        viewHistory.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent historyIntent = new Intent(FashionAidHome.this, ViewHistory.class);
				startActivity(historyIntent);
			}
		});
        
        //
        favorites = (Button) findViewById(R.id.favorite_class_button);
        favorites.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent favoritesIntent = new Intent(FashionAidHome.this, Favorites.class);
				startActivity(favoritesIntent);
				
			}
		});
        
     
    }
	 
}