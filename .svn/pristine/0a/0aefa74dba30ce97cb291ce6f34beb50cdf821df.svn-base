package com.fashion.aid;

public class Weather {
	
	private String description;
	private float temp;
	private String iconId; //three char strings that the web service returns to determine icon to use
	//we will substitute our own bitmaps instead of using theirs
	
	public Weather(){
		
		description = "default";
		temp = 0;
		iconId = "01d"; //icon for sunny
		
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String d){
		description = d;
	}
	
	public String getTemp(){
		return temp + "�";
	}
	
	public float getTempFloat() {
		return temp;
	}
	
	public void setTemp(float t){
		temp = (t - 273 * (9/5) + 32)+20;
		temp = (float)Math.round(temp);
	}
	
	public String getIcon(){
		return iconId;
	}
	
	public void setIcon(String id){
		iconId = id;
	}

}
