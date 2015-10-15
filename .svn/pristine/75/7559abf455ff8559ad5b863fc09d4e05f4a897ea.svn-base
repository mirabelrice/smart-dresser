package com.fashion.aid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ViewHistory extends Activity{
	//private Intent newIntent;
	//private ImageButton menuImgButton, lArrImgButton, rArrImgButton;
	private TextView textView;
	private ItemDataBase dataBase = new ItemDataBase(ViewHistory.this); 
	private Outfit selectedOutfit;
	private ArrayList<Outfit> outfitList;
	private HistoryNode[] historyNodes;
	private LinearLayout selectedOutfitLayout;
	private Context contex;
	private int numOfOutfits = 0;
	int activeFeature = 0;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	        setContentView(R.layout.history);
	        selectedOutfitLayout = (LinearLayout)findViewById(R.id.image_Layout);
	        textView = (TextView)findViewById(R.id.textView2);
	        LinearLayout.LayoutParams scrollParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	        outfitList = new ArrayList<Outfit>();
	        
	       
	       //get all history outfits in sorted order by date worn
	        dataBase.open();
	        historyNodes = dataBase.getAllHistoryOutfits();
	        numOfOutfits = historyNodes.length;

	        //add outfits to outfit list
	        for(int i = 0; i < (numOfOutfits); ++i){
	        	Outfit outfit = dataBase.getOutfit(historyNodes[i].getHistoryOutfitId());
	        	outfitList.add(outfit);
	        }
	        dataBase.close();
	        
	        if(numOfOutfits > 0){
	        	textView.setVisibility(View.INVISIBLE);//hide textview that says no recent outfits
		        setSelectedImage(0);//when activity opens, set image to first outfit in history
	        }        
	        //if there are outfits in history display in horizontal scroll
	        if(numOfOutfits >= 1){
	        	setHorizScroll();
	         }  
	}
	
	private void setSelectedImage(int p_numInList){
		ImageView topView = (ImageView)findViewById(R.id.topImage);
      	ImageView bottomView = (ImageView)findViewById(R.id.bottomImage);
      	Bitmap topImage;
      	Bitmap bottomImage;
      	
      //user has outfits in history, set main image buttons to outfit selected
      	if(selectedOutfit != null){
      		selectedOutfit = outfitList.get(p_numInList);
  			topImage = selectedOutfit.getTop().getImage();
	    	bottomImage = selectedOutfit.getBottom().getImage();
	    	topView.setScaleType(ImageView.ScaleType.FIT_CENTER);
			topView.setLayoutParams(new AbsListView.LayoutParams(650, 550)); 
			
			bottomView.setScaleType(ImageView.ScaleType.FIT_CENTER);
			bottomView.setLayoutParams(new AbsListView.LayoutParams(650, 550)); 
	    	//set image button to scaled outfit images
	    	topView.setImageBitmap(topImage);
	    	bottomView.setImageBitmap(bottomImage);
    	}
	}
	
	public void setHorizScroll(){
		LinearLayout scrollLayout = (LinearLayout)findViewById(R.id.scroll_Layout);
		HorizontalScrollView horizScrollView = (HorizontalScrollView)findViewById(R.id.history_Scroll);
		//LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		//LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		
		for(int i = 0; i < numOfOutfits; i++){
        	//Log.i("forloop2", " "+ outfit.getId());
			final int outfitNumber = i;
	        Item top = outfitList.get(outfitNumber).getTop();
	    	Item bottom = outfitList.get(outfitNumber).getTop();
	    	Bitmap topImage = top.getImage();
	    	Bitmap bottomImage = bottom.getImage();
	    	ImageView topView= new ImageView(this);
	    	ImageView bottomView= new ImageView(this);
	      	
	      	//create new vertical linear layout inside horizontal linear layout for each outfit, this will hold top and bottom images
	      	LinearLayout imageLayout = new LinearLayout(this);
	    	imageLayout.setOrientation(LinearLayout.VERTICAL);   
	    	//imageLayout.setLayoutParams(params);
	    	scrollLayout.addView(imageLayout);
	    	
	    	//sets up top button 	
	    	//topView.setLayoutParams(imageParams);
	    	topView.setScaleType(ImageView.ScaleType.FIT_CENTER);
			topView.setLayoutParams(new AbsListView.LayoutParams(400, 300));
	    	topView.setImageBitmap(topImage);
	    	imageLayout.addView(topView);
	    	
	    	//sets up bottom button
	    	//bottomView.setLayoutParams(imageParams);
	    	bottomView.setScaleType(ImageView.ScaleType.FIT_CENTER);
			bottomView.setLayoutParams(new AbsListView.LayoutParams(400, 300)); 
	    	bottomView.setImageBitmap(bottomImage);
	    	imageLayout.addView(bottomView);	

	    	//when user clicks on outfit, set main image buttons to outfit
	    	imageLayout.setClickable(true);
	    	imageLayout.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					setSelectedImage(outfitNumber);
				}	
	    	});	
		}
	}
}

