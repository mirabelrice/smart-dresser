package com.fashion.aid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class CreateOutfitFeedback extends Activity {
	
	private long outfitId = 0;
	private ImageView top, bottom;
	
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.outfit_created);
	        
			Bundle data = getIntent().getExtras();
	    	if(data != null) {
				outfitId = data.getLong("rowId");
			}
	    	
	    	ItemDataBase itemDB = new ItemDataBase(CreateOutfitFeedback.this);
	    	itemDB.open();
	    	Outfit outfit = itemDB.getOutfit(outfitId);
	    	itemDB.close();
	    	
	    	
	    	top = (ImageView) findViewById(R.id.feedbackTop_imgView);
	    	bottom = (ImageView) findViewById(R.id.feedBackBottom_imgView);
	    	
	    	
	    	//top.setImageBitmap(outfit.getTop().getImage());
	    	//bottom.setImageBitmap(outfit.getBottom().getImage());
	    	
	 }
}
