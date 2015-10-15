package com.fashion.aid;

import android.widget.GridView;
import android.widget.ImageView;
import android.view.ViewGroup;
import android.view.View;
import android.content.Context;
import android.widget.BaseAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class GridAdapter extends BaseAdapter{
	
	private Item[] itemArray;
	
	private Context context;
	
	public GridAdapter(Context c) {
		context = c;
	}
	@Override
	public int getCount() {
		return itemArray.length;
	}
	
	@Override
	public Object getItem(int position) {
		return itemArray[position];
	}
	@Override	
	public long getItemId(int position) {
		return 0;
	}
	public void setArray(Item[] p_array){
		itemArray = p_array;
	} 
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ImageView imageView;
		if (view == null) {
			imageView = new ImageView(context);
			imageView.setLayoutParams(new GridView.LayoutParams(300,300));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(5, 5, 5, 5);
		} else {
			imageView = (ImageView)view;	
		}
		imageView.setImageBitmap(itemArray[position].getImage());
		return imageView;
	}
}
