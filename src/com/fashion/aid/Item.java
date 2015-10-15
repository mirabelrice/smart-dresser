package com.fashion.aid;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

public class Item {
	private long itemId;
	private String type;
	private String color;
	private String length;
	private String dateWorn;
	private Bitmap image;
	
	public Item() {
		dateWorn = "00/00/0000";
		itemId = 0;
		length = "";
		type = "";
		color = "";
		image = null;
		
	}
	
	public long getId() {
		return itemId;
	}
	
	public void setId(long p_id) {
		itemId = p_id;
	}
	
	public String getLength() {
		return length;
	}
	
	public void setLength(String p_length) {
		length = p_length;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String p_color) {
		color = p_color;
	}
	
	public void setDateWorn(String p_dateWorn){
		dateWorn = p_dateWorn;
	}
	
	public String getDateWorn(){
		return dateWorn;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String p_type) {
		type = p_type;
	}
	
	public Bitmap getImage() {
		return image;
	} 
	
	public void setImage(Bitmap p_bitmap) {
		image = p_bitmap;
	}
	
	public int getColorValue() {
		if(type.equals("Denim")) {
			return 0;
		}
		
		int pixelCount = 0;
		int r = 0;
		int g = 0;
		int b = 0;
 		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
			     int c = image.getPixel(i, j);

			        pixelCount++;
			        r += Color.red(c);
			        g += Color.green(c);
			        b += Color.blue(c);
			}
		}
 		int colorValue = Color.rgb((r/pixelCount), g/pixelCount, b/pixelCount);
		return colorValue;
	}
	
	public String toString() {
		return color+ " " +type;
	}
}
