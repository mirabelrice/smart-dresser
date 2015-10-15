package com.fashion.aid;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ItemImageAdapter extends BaseAdapter {

	private ArrayList<Item> items;
	
	private Context context;
	
	public ItemImageAdapter(Context c) {
		context = c;
		items = new ArrayList<Item>();
	}
	@Override
	public int getCount() {
		return items.size();
	}
	
	@Override
	public Item getItem(int position) {
		return items.get(position);
	}
	@Override	
	public long getItemId(int position) {
		return position;
	}
	public void setItemList(ArrayList<Item> p_items){
		items = p_items;
	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ImageView imageView;
		if (view == null) {
			imageView = new ImageView(context);
			imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
			imageView.setLayoutParams(new AbsListView.LayoutParams(650, 550)); 

		} else {
			imageView = (ImageView)view;
		}
		imageView.setImageBitmap(items.get(position).getImage());
		return imageView;
	}
}