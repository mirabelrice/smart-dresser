package com.fashion.aid;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewItem extends Activity {
	
	private ImageView itemImage;
	private ImageButton editImgButton, deleteImgButton, menuImgButton;
	private Button wearImgButton;
	private TextView typeTV, colorTV, lengthTV;
	private Item item;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_item);
		
		//initializes item
		item = new Item();

		//gets Item from previous class
		Bundle data = getIntent().getExtras();
		if(data != null) {
			//gets item fields from database
			ItemDataBase info = new ItemDataBase(ViewItem.this);
			info.open();
			item = info.getItem(data.getLong("rowId"));
			info.close();
		}
		
		//sets item image
		itemImage = (ImageView) findViewById(R.id.view_item_imageView);
		itemImage.setImageBitmap(item.getImage());
				
		typeTV = (TextView) findViewById(R.id.type_viewItem_textView);
		typeTV.setText(item.getType());
		
		colorTV = (TextView) findViewById(R.id.color_viewItem_textView);
		colorTV.setText(item.getColor());
		
		lengthTV = (TextView) findViewById(R.id.length_viewItem_textView);
		lengthTV.setText(item.getLength());
		
		//sets up edit button
		editImgButton = (ImageButton) findViewById(R.id.edit_imgButton);
		editImgButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent editItemIntent = new Intent(ViewItem.this, AddItem.class);
				editItemIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
				editItemIntent.putExtra("action", "editItem");
				editItemIntent.putExtra("rowId", item.getId());
				startActivity(editItemIntent);
			}
		});
		
		//sets up delete button
		deleteImgButton = (ImageButton) findViewById(R.id.delete_imgButton);
		deleteImgButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
				    @Override
				    public void onClick(DialogInterface dialog, int which) {
				        switch (which){
				        case DialogInterface.BUTTON_POSITIVE:
				            //Yes button clicked
				        	
							//deletes Item
							ItemDataBase itemDB = new ItemDataBase(ViewItem.this);
							itemDB.open();
							itemDB.deleteItemEntry(item.getId());
							itemDB.close();
							
							//goes back to view closet
							Intent viewClosetIntent = new Intent(ViewItem.this, ViewCloset.class);
							viewClosetIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
							startActivity(viewClosetIntent);

				        case DialogInterface.BUTTON_NEGATIVE:
				            //No button clicked
				            break;
				        }
				    }
				};

				AlertDialog.Builder builder = new AlertDialog.Builder(ViewItem.this);
				builder.setMessage("Are you sure you want to delete this item?").setPositiveButton("Yes", dialogClickListener)
				    .setNegativeButton("No", dialogClickListener).show();

			}
		});
		
		 menuImgButton = (ImageButton) findViewById(R.id.menu_viewItem_button);
	     menuImgButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
					Intent menuIntent = new Intent(ViewItem.this, FashionAidHome.class);
					menuIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
					startActivity(menuIntent);
			}
		});
	     
	     wearImgButton = (Button) findViewById(R.id.wear_viewItem_button);
	     wearImgButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent createOutfitIntent = new Intent(ViewItem.this, CreateOutfit.class);
				createOutfitIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
				createOutfitIntent.putExtra("rowId", item.getId());
				createOutfitIntent.putExtra("caller", "Closet");
				startActivity(createOutfitIntent);
				
			}
		});
		
	}
	
}
