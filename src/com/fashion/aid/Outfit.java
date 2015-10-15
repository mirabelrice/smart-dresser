package com.fashion.aid;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Outfit implements Comparable<Outfit> {
	private ArrayList<Item> items;
	private Item top;
	private Item bottom;
	private Item dress;
	private Item shoes;
	private Item outerwear;
	private String dateWorn;
	private int historyWeight;
	private int weatherWeight;
	private double colorWeight;
	private long id;
	
	
	public Outfit(){
		items = new ArrayList<Item>();
		top = new Item();
		bottom = new Item();
		dress = new Item();
		shoes = new Item();
		outerwear = new Item();
		id = 0;
		colorWeight = 0.0;
		weatherWeight = 0;
		historyWeight = 0;
		dateWorn = "";
	}
	
	public void setId(long p_id) {
		id = p_id;
	}
	
	public long getId() {
		return id;
	}
	
	public void setTop(Item p_item) {
		top = p_item;
	}
	
	public Item getTop() {
		return top;
	}
	
	public void setBottom(Item p_item) {
		bottom = p_item;
	}
	
	public Item getBottom() {
		return bottom;
	}
	
	public void setDress(Item p_item) {
		dress = p_item;
	}
	
	public Item getDress() {
		return dress;
	}
	
	public void setShoes(Item p_item) {
		shoes = p_item;
	}
	
	public Item getShoes() {
		return shoes;
	}
	
	public void setOuterwear(Item p_item) {
		outerwear = p_item;
	}
	
	public Item getOuterwear() {
		return outerwear;
	}
	
	public void setDateWorn(String p_date) {
		dateWorn = p_date;
		//historyWeight = (int)getDaysBetween();
		
	}
	
	public String getDateWorn() {
		return dateWorn;
	}
	
	public int calcHistoryWeight(){
		return (int)getDaysBetween();
	}
	
	public int getHistoryWeight(){
		return historyWeight;
	}
	
	public void setHistoryWeight(int p_historyWeight) {
		historyWeight = p_historyWeight;
	}
	
	public double calcColorWeight() {
		//top and bottom pair
		if((top.getId() != 0)&&(bottom.getId() != 0)) {
			return Math.abs(top.getColorValue()-bottom.getColorValue());
		}
		else {
			return 0;		//TODO: Change value for dresses
		}
	}
	
	public double getColorWeight(){
		return colorWeight;
	}
	
	public void setColorWeight(double p_colorWeight){
		 colorWeight = p_colorWeight;
	}
	
	public int calcWeatherWeight(){
		if ((top.getId() != 0) && (bottom.getId() !=0)) {
			String topLength = top.getLength();
			String bottomLength = bottom.getLength();

			if(topLength.equals("Long") && bottomLength.equals("Long")) {
				return 1;
			}
			else if (topLength.equals("Short") && bottomLength.equals("Short")) {
				return 2;
			}
			else {
				return 0;
			}
		}
		return -1;			//TODO: Change value for dresses
	}
	
	public int getWeatherWeight(){
		return weatherWeight;
	}
	
	public void setWeatherWeight(int p_weatherWeight){
		weatherWeight = p_weatherWeight;
	}
	
	public ArrayList<Item> getItemsInOutfit() {
        items.removeAll(items);
		if(top != null) {
			items.add(top);
		}
		if (bottom != null) {
			items.add(bottom);
		}
		
		return items;
	}
	
	
	public boolean equals(Outfit p_other) {
		Item otherTop = p_other.getTop();
		Item otherBottom = p_other.getBottom();
		
		if((top.equals(otherTop)) && (bottom.equals(otherBottom))) {
			return true;
		}
		else {
			return false;
		}	
	}
	
	/*
	calculate the days between current date and date worn to determine history weight
	*/
	private long getDaysBetween(){  
		Calendar currentDate = Calendar.getInstance(); 
		Calendar temp = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		long daysBetween = 0;  
		
		try{
			temp.setTime(dateFormat.parse(dateWorn));
		}
		catch(Exception e){
			return 0;
		}
 		
 		//while todays date is after date outfit worn, calculate number of days since outfit was last worn
 		while(temp.before(currentDate)){  
 			temp.add(Calendar.DAY_OF_MONTH, 1);  
  			daysBetween++;  
  		} 
  		return daysBetween;  
	}

	@Override
	public int compareTo(Outfit arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}

