package com.fashion.aid;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.MotionEvent;
import android.content.Intent;

public class CustomHorizScroll extends HorizontalScrollView {
	private static final int MIN_SWIPE_DISTANCE = 5;
	private static final int SWIPE_THRESHOLD_VELOCITY = 300;
	Context context;
	int prevIndex = 0;
	private GestureDetector gestureDetector;
	private int activeFeature = 0; 
	private int numOutfits = 0;
	private HistoryNode[] historyOutfits;
	private DisplayMetrics dm = new DisplayMetrics();
	private ItemDataBase dataBase = new ItemDataBase(getContext());
	private int previousIndex = 0;
	private LinearLayout horizLayout =  null;
    
    public CustomHorizScroll(Context context, HistoryNode[] p_historyHeap, int p_numOutfits) {
        super(context);
        this.context = context;
        this.setSmoothScrollingEnabled(true);
    }
    
    
    public void setHistoryArray(HistoryNode[] p_historyHeap, int p_numOutfits){
    	numOutfits = p_numOutfits;
    	historyOutfits = new HistoryNode[numOutfits];
    	historyOutfits = p_historyHeap;
    }
    
    public void setImageLayout(){
    	//create parent linear layout inside horizontal scroll, this holds inner linear layouts created for each outfit
    	horizLayout = new LinearLayout(context);
    	
    	//create parameters for each layout and buttons
    	LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams outSideLayoutParam = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        buttonParams.weight = 1.0f;
        horizLayout.setOrientation(LinearLayout.HORIZONTAL);
        horizLayout.setLayoutParams(outSideLayoutParam);
    	this.addView(horizLayout);
    
    
    	dataBase.open();
        //get outits from array in reverse order to display most recent outfit first
    	
    	for(int i = (historyOutfits.length -1); i >=0; i--){
	        //for each outfit in heap, get individual items and add images to buttons
        	//eventually will have buttons shoes/outerwear etc.
    		Log.i("forloop", " "+ historyOutfits[i].getHistoryOutfitId());
        	Outfit outfit = dataBase.getOutfit(historyOutfits[i].getHistoryOutfitId());
        	
        	Log.i("forloop2", " "+ outfit.getId());
	        final Item top = outfit.getTop();
	    	final Item bottom = outfit.getBottom();
	    	final Item shoes = outfit.getShoes();
	    	Bitmap topImage = top.getImage();
	    	Bitmap bottomImage = bottom.getImage();
	    	ImageButton topButton = new ImageButton(context);
	      	ImageButton bottomButton = new ImageButton(context);
	      	TextView date = new TextView(context);//displays date outfit was worn
	      
	      	//create new vertical linear layout inside horizontal linear layout for each outfit, this will hold image buttons
	      	LinearLayout imageLayout = new LinearLayout(context);
	      	imageLayout.setGravity(Gravity.CENTER);
	    	imageLayout.setOrientation(LinearLayout.VERTICAL);   
	    	imageLayout.setLayoutParams(params);
	    	horizLayout.addView(imageLayout);
	    	horizLayout.setGravity(Gravity.CENTER);
	    	
	      	//sets up top button 	
	    	topButton.setLayoutParams(buttonParams);
	    	topButton.setImageBitmap(topImage);
	    	imageLayout.addView(topButton);
	    	
	    	//sets up bottom button
	    	bottomButton.setLayoutParams(buttonParams);
	    	bottomButton.setImageBitmap(bottomImage);
	    	imageLayout.addView(bottomButton);
	    	
	    	//set TextView to display date outfit was worn
	    	date.setText(historyOutfits[i].getDateWorn());
	    	date.setGravity(Gravity.CENTER|Gravity.BOTTOM);
	    	imageLayout.addView(date);
	    	
	    	//when item image button is clicked, jump to view item screen for that item
	    	topButton.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v) {
					Intent viewTopIntent = new Intent(context,ViewItem.class);
					viewTopIntent.putExtra("rowId", top.getId());
					context.startActivity(viewTopIntent);	
				}
	    	});
	 	   
	    	bottomButton.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v) {
					Intent viewBottomIntent = new Intent(context,ViewItem.class);
					viewBottomIntent.putExtra("rowId", bottom.getId());
					context.startActivity(viewBottomIntent);	
				}
	    	});
	    	
	    	
    	}
	  
    	dataBase.close();
    	
    	setOnTouchListener(new View.OnTouchListener(){
           	@Override
           	public boolean onTouch(View v, MotionEvent event){
           		Log.i("tag", "on touch");
           		if (gestureDetector.onTouchEvent(event)) {
                       return true;
                   }else if(event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL ){
                   	int scrollX = getScrollX();
                       int featureWidth = v.getMeasuredWidth();
                       activeFeature = ((scrollX + (featureWidth/2))/featureWidth);
                       int scrollTo = activeFeature*featureWidth;
                       smoothScrollTo(scrollTo, 0);
                       return true;
                   }else{
                       return false;
                   }
           	}	
	});
        gestureDetector = new GestureDetector(context, new CustomGestureDetector());  
    } 
    class CustomGestureDetector extends SimpleOnGestureListener{
		@Override
		public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
		    Log.i("tag", "here");
			boolean validFling = false;
			float point1 = 0;
		    float point2 = 0;
		    if (event1 == null || event2 == null){
		    	Log.i("tag", "in null");
		    	return false;
		    }
		    point1 = event1.getX();
		    point2 = event2.getX();
		    // right to left
	
		    if (point1 - point2  > MIN_SWIPE_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
		        if (activeFeature < numOutfits - 1){
		        	Log.i("tag", "first if");
		        	activeFeature = activeFeature + 1;
		        }
		        validFling = true;
		    }else if (point2 - point1 > MIN_SWIPE_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY){
		        if (activeFeature> 0){
		        	Log.i("tag", "second if");
		            activeFeature = activeFeature - 1;
		        }
		        validFling = true;
		    }
			    setCenterView(activeFeature);
			    Log.i("tag", "after center set");
			    return validFling;
			}
		 
		 @Override
		 public boolean onDown(MotionEvent e) {
			 Log.i("tag", "on down");
		     return true;
		 }
    }

    public void setCenterView(int p_index){
    	Log.i("tag", "in center");
    	int currentIndex = p_index;
    	int screenWidth = ((Activity) context).getWindowManager().getDefaultDisplay().getWidth();
    	Log.i("tag", "after width");
    	
    	ViewGroup parentView = (ViewGroup)horizLayout;
    	Log.i("tag", "after parent");
    	View currentImage = (View)getChildAt(currentIndex);
    	View previousImage = (View)getChildAt(previousIndex);
    	int scrollX = (currentImage.getLeft() - (screenWidth / 2) + (currentImage.getWidth() / 2));
    	this.smoothScrollTo(scrollX, 0);
    	
    	previousIndex = currentIndex;
    }
}
