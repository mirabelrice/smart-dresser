package com.fashion.aid;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddItem extends Activity {
	
	private Spinner typeSpinner, colorSpinner, lengthSpinner;
	private ArrayAdapter<CharSequence> typeAdapter, colorAdapter, lengthAdapter;
	private ItemDataBase itemDB = new ItemDataBase(AddItem.this);
	private Button saveButton;
	private ImageButton cameraImageButton, menuImgButton;
	private Item item;
	private TextView titleTV;
	private String action = "";
	static final int REQUEST_IMAGE_CAPTURE = 1;
	protected Uri picUri;
	final int PIC_CROP = 2;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_screen);
        
     //initializes item object
     item = new Item();
        
     //title
     titleTV = (TextView) findViewById(R.id.add_item_title);
            
     //type drop down menu     
     typeSpinner = (Spinner) findViewById(R.id.type_spinner);
     typeAdapter = ArrayAdapter.createFromResource(this,
    		 R.array.type_array, android.R.layout.simple_spinner_item);
     typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     typeSpinner.setAdapter(typeAdapter);
     
     //color drop down menu   
     colorSpinner = (Spinner) findViewById(R.id.color_spinner);
     colorAdapter = ArrayAdapter.createFromResource(this,
    		 R.array.color_array, android.R.layout.simple_spinner_item);
     colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     colorSpinner.setAdapter(colorAdapter);
     
     //length drop down menu   
     lengthSpinner = (Spinner) findViewById(R.id.length_spinner);
     lengthAdapter = ArrayAdapter.createFromResource(this,
    		 R.array.length_array, android.R.layout.simple_spinner_item);
     lengthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     lengthSpinner.setAdapter(lengthAdapter);
     
     //taking a picture
     item.setImage(BitmapFactory.decodeResource(this.getResources(), R.drawable.camera_icon));
     cameraImageButton = (ImageButton) findViewById(R.id.take_picture);
     cameraImageButton.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			   Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

			    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
			        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
			    }
		}
			
	});
     
     //determines whether to add to edit item
     Bundle data = getIntent().getExtras();
		if(data != null) {
			action = data.getString("action");			
		} 
		
	 //edit item option; fills in item information to be edited
	 if(action.equals("editItem")) {
		 titleTV.setText("Edit Item");
		 
		 //sets Item fields
		 itemDB.open();
		 item = itemDB.getItem(data.getLong("rowId"));
		 itemDB.close();
		 
		 //sets the views
		 typeSpinner.setSelection(typeAdapter.getPosition(item.getType()));
		 colorSpinner.setSelection(colorAdapter.getPosition(item.getColor()));
		 lengthSpinner.setSelection(lengthAdapter.getPosition(item.getLength()));
		 cameraImageButton.setImageBitmap(item.getImage());
		 
		 //deletes any pairings containing the item
		 deletePairings();
		 
	 }
     
     //saving an item
     saveButton = (Button) findViewById(R.id.save_item_button);
     saveButton.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			//gets user data
			item.setType(typeSpinner.getSelectedItem().toString());
			item.setColor(colorSpinner.getSelectedItem().toString());
			item.setLength(lengthSpinner.getSelectedItem().toString());
			
			//adds or edits entry to database
			itemDB.open();
			
			if(action.equals("editItem")) {
		    	itemDB.updateItemEntry(item.getId(), item.getColor(), item.getLength(), item.getType(), convertToByteArr(item.getImage()), item.getDateWorn());
			}
			else {
				long itemId = itemDB.createItemEntry(item.getColor(), item.getLength(), item.getType(), convertToByteArr(item.getImage()), item.getDateWorn());
				item.setId(itemId);
			}
			
			itemDB.close();
			
			//creates pairings for the new item
			pairAndAddOufit();
			
			//opens View Item class
			Intent viewItemsIntent = new Intent(AddItem.this, ViewItem.class);
			viewItemsIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			viewItemsIntent.putExtra("rowId", item.getId());
			startActivity(viewItemsIntent);

		}
	});
     
     menuImgButton = (ImageButton) findViewById(R.id.menu_addItem_button);
     menuImgButton.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
				Intent menuIntent = new Intent(AddItem.this, FashionAidHome.class);
				menuIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(menuIntent);
		}
	});
     
    }

	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
        	picUri = data.getData();
            cameraImageButton.setImageBitmap(item.getImage());
            performCrop();
        }
        
      //user is returning from cropping the image
        else if(requestCode == PIC_CROP){
        	//get the returned data
        	Bundle extras = data.getExtras();
        	//get the cropped bitmap
        	Bitmap thePic = extras.getParcelable("data");
        	item.setImage(thePic);
        	cameraImageButton.setImageBitmap(item.getImage());
        }
    }
    
    
    private byte[] convertToByteArr(Bitmap p_imgBitmap) {
    	ByteArrayOutputStream stream = new ByteArrayOutputStream();
    	p_imgBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
    	byte[] imgByteArr = stream.toByteArray();
    	return imgByteArr;
    }
    
    private void deletePairings() {
    	itemDB.open();
    	ArrayList<Long> outfitsToDelete = itemDB.getAllOccurances(item.getId());
    	
    	for (int i = 0; i < outfitsToDelete.size(); i++) {
    		itemDB.deleteOutfitEntry(outfitsToDelete.get(i));
    	}
    	
    	itemDB.close();
    }
    
    private void pairAndAddOufit() {
    	boolean typeMatch;
    	boolean colorMatch;
    	
    	itemDB.open();
    	Item [] items = itemDB.getAllItems();
    	itemDB.close();
    	
    	for (int i = 0; i < items.length; i++) {
    		if(items[i].getId() != item.getId()) {
    			
    			typeMatch = isMathcingType(items[i].getType());
    			colorMatch = isMatchingColor(items[i].getColor());
    			
    			//creates an empty outfit object and adds the items to it
    			if (typeMatch && colorMatch) {
    				itemDB.open();
    				long outfitId = itemDB.createOutfitEntry(0, 0, 0, 0.0, 0, 0);
    		    	itemDB.close();
    				addItemToOutfit(outfitId, items[i]);
    				addItemToOutfit(outfitId, item);
    				
    				//update outfit weights
    				itemDB.open();
    				Outfit outfit = itemDB.getOutfit(outfitId);
    				itemDB.updateOutfitEntry(outfitId, outfit.getTop().getId(), outfit.getBottom().getId(), outfit.getDress().getId(), 
    						outfit.calcColorWeight(), outfit.calcWeatherWeight(), outfit.calcHistoryWeight());
    		    	itemDB.close();
    				
    		    	Log.i("Actual Object Id", outfitId+"");
    		    	Log.i("Outfit Object Id", outfit.getId()+"");
    				Log.i("Top ID", outfit.getTop().getId()+"");
    				Log.i("Bottom ID", outfit.getBottom().getId()+"");
    				Log.i("Color Weight", outfit.calcColorWeight()+"");
    				Log.i("Weather Weight", outfit.calcWeatherWeight()+"");
    				Log.i("History Weight", outfit.calcHistoryWeight()+"");
    			}
    		}
    	}
    	
    }

	private boolean isMathcingType(String p_type) {
		boolean matching = true;
		
		if (item.getType().equals(p_type)) {
			return false;
		}
		else if (item.getType().equals("Top") && p_type.equals("Dress")) {
			return false;
		}
		else if (item.getType().equals("Dress") && (p_type.equals("Top") || p_type.equals("Bottom"))) {
			return false;
		}
		
		return matching;
	}

	private boolean isMatchingColor(String p_color) { 
		boolean matching = true;
		
		if(item.getColor().equals(p_color)) {
			return false;
		}
		
	
		return matching;
	}
	
	private void performCrop(){
		try {
			//call the standard crop action intent (the user device may not support it)
			Intent cropIntent = new Intent("com.android.camera.action.CROP"); 
			    //indicate image type and Uri
			cropIntent.setDataAndType(picUri, "image/*");
			    //set crop properties
			cropIntent.putExtra("crop", "true");
			    //indicate aspect of desired crop
			cropIntent.putExtra("aspectX", 1);
			cropIntent.putExtra("aspectY", 1);
			    //indicate output X and Y
			cropIntent.putExtra("outputX", 256);
			cropIntent.putExtra("outputY", 256);
			    //retrieve data on return
			cropIntent.putExtra("return-data", true);
			    //start the activity - we handle returning in onActivityResult
			startActivityForResult(cropIntent, PIC_CROP);
		}
		catch(ActivityNotFoundException anfe){
		    //display an error message
		    String errorMessage = "Whoops - your device doesn't support the crop action!";
		    Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
		    toast.show();
		}
	}
	
	private void addItemToOutfit(long p_rowId, Item p_item) {
		itemDB.open();
		Outfit outfit = itemDB.getOutfit(p_rowId);
		
		if(p_item.getType().equals("Top")){
			outfit.setTop(p_item);
		}
		else if (p_item.getType().equals("Bottom")) {
			outfit.setBottom(p_item);
		}
		else if (p_item.getType().equals("Dress")) {
			outfit.setDress(p_item);
		}
		
		itemDB.updateOutfitEntry(outfit.getId(), outfit.getTop().getId(), outfit.getBottom().getId(), outfit.getDress().getId(), 
				0.0, 0, 0);
		
		itemDB.close();

	}

}
