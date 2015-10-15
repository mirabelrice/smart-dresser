package com.fashion.aid;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class ItemDataBase {
	
	//database definition
	private static final String DATABASE_NAME = "ItemDB";
	private static final int DATABASE_VERSION = 1;
	
	//item table
	private static final String DATABASE_ITEM_TABLE = "itemTable";

	//item attributes
	public static final String KEY_ROWID = "_id";			//also used by outfit and history table
	public static final String KEY_COLOR = "itemColor";
	public static final String KEY_TYPE = "itemType";
	public static final String KEY_LENGTH = "itemLength";	//values: "Long" and "Short"
	public static final String KEY_IMAGE = "image";
	public static final String KEY_DATE = "date";			//also used by history table
	
	//outfit table
	private static final String DATABASE_OUTFIT_TABLE = "outfitTable";
	
	//outfit attributes
	public static final String KEY_TOPID = "topID";
	public static final String KEY_BOTTOMID = "bottomID";
	public static final String KEY_DRESSID = "dressID";
	public static final String KEY_CWEIGHT = "colorWeight";
	public static final String KEY_WWEIGHT = "weatherWeight";	//values 2 (warm weather), 0 (normal weather), 1 (cool weather)
	public static final String KEY_HWEIGHT = "historyWeight"; //values 1-14; default: 14
	
	//history table
	private static final String DATABASE_HISTORY_TABLE = "historyTable";
	
	//history attributes
	public static final String KEY_OUTFITID = "outfitId";
	
	//defining database realted android classes
	private DbHelper itemDBHelper;
	private Context context;
	private SQLiteDatabase itemDB;

	
	private static class DbHelper extends SQLiteOpenHelper{
		
		//creates the database
		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		
		//creates the tables
		@Override
		public void onCreate(SQLiteDatabase db) {
				//creates the top table
				db.execSQL("CREATE TABLE " + DATABASE_ITEM_TABLE + " (" +
						KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
						KEY_COLOR + " TEXT NOT NULL, " + 
						KEY_TYPE + " TEXT NOT NULL, " +
						KEY_LENGTH + " TEXT NOT NULL, " +
						KEY_IMAGE + " BLOB, "+
						KEY_DATE + " TEXT NOT NULL);"					
				);
			
			//creates the outfit table
			db.execSQL("CREATE TABLE " + DATABASE_OUTFIT_TABLE + " (" +
					KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
					KEY_TOPID + " REAL NOT NULL, " +
					KEY_BOTTOMID + " REAL NOT NULL, " +
					KEY_DRESSID + " REAL NOT NULL, " +
					KEY_CWEIGHT + " REAL NOT NULL, " +
					KEY_WWEIGHT + " INTEGER NOT NULL, " +
					KEY_HWEIGHT + " INTEGER NOT NULL);"
			);
			
			//creates the history table
			db.execSQL("CREATE TABLE " + DATABASE_HISTORY_TABLE + " (" + 
					KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
					KEY_OUTFITID + " REAL NOT NULL, " + 
					KEY_DATE + " TEXT NOT NULL);"		
			);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_ITEM_TABLE);
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_OUTFIT_TABLE);
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_HISTORY_TABLE);
			onCreate(db);
		}
	}
		
		public ItemDataBase(Context p_context) {
			context = p_context;
		}
		
		public ItemDataBase open() {
			itemDBHelper = new DbHelper(context);
			itemDB = itemDBHelper.getWritableDatabase();
			return this;
		}
		
		public void close() {
			itemDBHelper.close();
		}
		
		//adds item to database
		public long createItemEntry(String p_color, String p_length, String p_type, byte[] p_image, String p_date) {
			ContentValues cv = new ContentValues();
			cv.put(KEY_COLOR, p_color);
			cv.put(KEY_TYPE, p_type);
			cv.put(KEY_LENGTH, p_length);
			cv.put(KEY_IMAGE, p_image);
			cv.put(KEY_DATE, p_date);
			
			return itemDB.insert(DATABASE_ITEM_TABLE, null, cv);
		}
		
		//adds outfit to database
		public long createOutfitEntry(long p_topId, long p_bottomId, long p_dressId, double p_cweight, int p_wweight, int p_hweight) {
			ContentValues cv = new ContentValues();
			cv.put(KEY_TOPID, p_topId);
			cv.put(KEY_BOTTOMID, p_bottomId);
			cv.put(KEY_DRESSID, p_dressId);
			cv.put(KEY_CWEIGHT, p_cweight);
			cv.put(KEY_WWEIGHT, p_wweight);
			cv.put(KEY_HWEIGHT, p_hweight);

			return itemDB.insert(DATABASE_OUTFIT_TABLE, null, cv);
		}
		
		//adds item to database
		public long createHistoryEntry(long p_outfitId, String p_date){
			ContentValues cv = new ContentValues();
			cv.put(KEY_OUTFITID, p_outfitId);
			cv.put(KEY_DATE, p_date);
			
			return itemDB.insert(DATABASE_HISTORY_TABLE, null, cv);
		}
		
		public long updateItemEntry(long p_rowId, String p_color, String p_length, String p_type, byte[] p_image, String p_date) {
			ContentValues cv = new ContentValues();
			cv.put(KEY_ROWID, p_rowId);
			cv.put(KEY_COLOR, p_color);
			cv.put(KEY_TYPE, p_type);
			cv.put(KEY_LENGTH, p_length);
			cv.put(KEY_IMAGE, p_image);
			cv.put(KEY_DATE, p_date);
			
			return itemDB.update(DATABASE_ITEM_TABLE, cv, KEY_ROWID +"=" + p_rowId, null);
		}
		
		public void updateOutfitEntry(long p_rowId, long p_topId, long p_bottomId, long p_dressId, double p_cweight, int p_wweight, int p_hweight) {
			ContentValues cv = new ContentValues();
			cv.put(KEY_ROWID, p_rowId);
			cv.put(KEY_TOPID, p_topId);
			cv.put(KEY_BOTTOMID, p_bottomId);
			cv.put(KEY_DRESSID, p_dressId);
			cv.put(KEY_CWEIGHT, p_cweight);
			cv.put(KEY_WWEIGHT, p_wweight);
			cv.put(KEY_HWEIGHT, p_hweight);

			itemDB.update(DATABASE_OUTFIT_TABLE, cv, KEY_ROWID +"=" + p_rowId, null);
		}
	
		public void deleteItemEntry(long p_rowId) {
			itemDB.delete(DATABASE_ITEM_TABLE, KEY_ROWID + "=" + p_rowId, null);
			deleteOutfitsWithItem(p_rowId);
		}
		
		private void deleteOutfitsWithItem(long p_rowId) {
			ArrayList<Long> outfitsToDelete = getAllOccurances(p_rowId);
			for (int i = 0; i < outfitsToDelete.size(); i++) {
				deleteOutfitEntry(outfitsToDelete.get(i));
			}
		}
		
		public void deleteOutfitEntry(long p_rowId) {
			itemDB.delete(DATABASE_OUTFIT_TABLE, KEY_ROWID + "=" + p_rowId, null);
		}
		
		public void deleteHistoryEntry(long p_rowId) {
			itemDB.delete(DATABASE_HISTORY_TABLE, KEY_ROWID + "=" + p_rowId, null);
		}
		
		public Item[] getAllItems() {
			String[] columns = new String[]{KEY_ROWID, KEY_COLOR, KEY_TYPE, KEY_IMAGE, KEY_DATE};
			Cursor c = itemDB.query(DATABASE_ITEM_TABLE, columns, null, null, null, null, null, null);
			
			Item[] itemArray = new Item[c.getCount()];
			int iRow = c.getColumnIndex(KEY_ROWID);
			int index = 0;
			
			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
				Item newItem = new Item();
				newItem = getItem(c.getLong(iRow));
				itemArray[index] = newItem;
				index++;
			}
			
			
			
			return itemArray;
		}
		
		public Item getItem(long p_itemId) {
			//item id does not exist
			if(p_itemId == 0) {
				return null;
			}
			
			String[] columns = new String[]{KEY_ROWID, KEY_COLOR, KEY_TYPE, KEY_LENGTH, KEY_IMAGE, KEY_DATE};
			Cursor c = itemDB.query(DATABASE_ITEM_TABLE, columns, null, null, null, null, null, null);
			
			int iRow = c.getColumnIndex(KEY_ROWID);
			int iImage = c.getColumnIndex(KEY_IMAGE);
			int iColor = c.getColumnIndex(KEY_COLOR);
			int iType = c.getColumnIndex(KEY_TYPE);
			int iLength = c.getColumnIndex(KEY_LENGTH);
			int iDate = c.getColumnIndex(KEY_DATE);
			
			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
				if(c.getLong(iRow) == p_itemId) {
					break;
				}
			}
			
			Item item = new Item();
			byte[] images = c.getBlob(iImage);
			Bitmap imgBitmap = BitmapFactory.decodeByteArray(images, 0, images.length);
			item.setImage(imgBitmap);
			item.setLength(c.getString(iLength));
			item.setColor(c.getString(iColor));
			item.setType(c.getString(iType));
			item.setId(c.getLong(iRow));
			item.setDateWorn(c.getString(iDate));
			
			return item;
		}
		
		public ArrayList<Outfit> getAllOutfits(int p_weather){
			Cursor c = itemDB.rawQuery("SELECT * FROM " + DATABASE_OUTFIT_TABLE + 
										" WHERE " + KEY_WWEIGHT + " = " + p_weather + " ORDER BY " + KEY_HWEIGHT + " DESC, " + KEY_CWEIGHT + " DESC", null);
			int iRowId = c.getColumnIndex(KEY_ROWID);
			
			ArrayList<Outfit> outfits = new ArrayList<Outfit>();
			
			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
				Outfit outfit = getOutfit(c.getLong(iRowId));
				outfits.add(outfit);
			}
			
			return outfits;
		}
		
		public ArrayList<Outfit> getOutfitsWithKey(String column, long p_itemId, int p_weather) {
			Cursor c = itemDB.rawQuery("SELECT * FROM " + DATABASE_OUTFIT_TABLE + " WHERE " + column + "=" + p_itemId + 
						" WHERE " + KEY_WWEIGHT + " = " + p_weather + " ORDER BY " + KEY_HWEIGHT + " DESC, " + KEY_CWEIGHT + " DESC", null);
			int iRowId = c.getColumnIndex(KEY_ROWID);
			
			ArrayList<Outfit> outfits = new ArrayList<Outfit>();
						
			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
				Outfit outfit = getOutfit(c.getLong(iRowId));
				outfits.add(outfit);
			}

			return outfits;
		}
		
		/*
		put all outfits from history table in MinHeap and return sorted array
		*/
		public HistoryNode[] getAllHistoryOutfits(){
			String[] columns = new String[] {KEY_ROWID, KEY_OUTFITID, KEY_DATE};
			Cursor c = itemDB.query(DATABASE_HISTORY_TABLE, columns, null, null, null, null, null, null);
			MaxHeap maxHeap = new MaxHeap(c.getCount());
			
			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
				int iRowId = c.getColumnIndex(KEY_ROWID);
				int iOutfitId = c.getColumnIndex(KEY_OUTFITID);
				int iDate = c.getColumnIndex(KEY_DATE);
				HistoryNode newNode = new HistoryNode(c.getLong(iRowId), c.getLong(iOutfitId), c.getString(iDate));
				Log.i("node", " " + newNode.getHistoryOutfitId());

				//only display last 14 outfits worn
				if(maxHeap.getSize() < 14){
	        		maxHeap.enqueue(newNode);
				}
	        	else{
	        		HistoryNode oldestOutfit = maxHeap.getTop();
	        		deleteHistoryEntry(oldestOutfit.getHistoryTableId());
	        		maxHeap.dequeue();
	        		maxHeap.enqueue(newNode);
	        	}
			}
			HistoryNode[] historyOutfits = maxHeap.heapSort();
			return historyOutfits;
		}
		
		public Outfit getOutfit (long p_rowId) {
			String[] columns = new String[] {KEY_ROWID, KEY_TOPID, KEY_BOTTOMID, KEY_DRESSID, KEY_CWEIGHT, KEY_HWEIGHT, KEY_WWEIGHT};
			Cursor c = itemDB.query(DATABASE_OUTFIT_TABLE, columns, null, null, null, null, null, null);
			Outfit outfit = new Outfit();
			int iRowId = c.getColumnIndex(KEY_ROWID);		
			int iTopId = c.getColumnIndex(KEY_TOPID);
			int iBottomId = c.getColumnIndex(KEY_BOTTOMID);
			int iDressId = c.getColumnIndex(KEY_DRESSID);
			int iCWeight = c.getColumnIndex(KEY_CWEIGHT);
			int iHWeight = c.getColumnIndex(KEY_HWEIGHT);
			int iWWeight = c.getColumnIndex(KEY_WWEIGHT);
			
			Log.i("getOutfit", "" + p_rowId);
			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
				if(c.getLong(iRowId) == p_rowId) {
					//gets items in outfit
					Item top = getItem(c.getLong(iTopId));
					Item bottom = getItem(c.getLong(iBottomId));
					Item dress = getItem(c.getLong(iDressId));
					
					//sets outfit
					outfit.setId(c.getLong(iRowId));
					outfit.setColorWeight(c.getDouble(iCWeight));
					outfit.setWeatherWeight(c.getInt(iWWeight));
					outfit.setHistoryWeight(c.getInt(iHWeight));
					
					if(top != null) {
						outfit.setTop(top);
					}
					
					if (bottom != null) {
						outfit.setBottom(bottom);
					}
					
					if (dress != null) {
						outfit.setDress(dress);
					}
					break;			
				}
			}
			return outfit;
		}
	
		public ArrayList<Long> getAllOccurances(long p_itemId) {
			String[] columns = new String[] {KEY_ROWID, KEY_TOPID, KEY_BOTTOMID, KEY_DRESSID, KEY_CWEIGHT, KEY_HWEIGHT, KEY_WWEIGHT};
			Cursor c = itemDB.query(DATABASE_OUTFIT_TABLE, columns, null, null, null, null, null, null);
			int iRowId = c.getColumnIndex(KEY_ROWID);		
			int iTopId = c.getColumnIndex(KEY_TOPID);
			int iBottomId = c.getColumnIndex(KEY_BOTTOMID);
			int iDressId = c.getColumnIndex(KEY_DRESSID);
			ArrayList<Long> itemOccurances = new ArrayList<Long>();
			
			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
				if((c.getLong(iTopId) == p_itemId) || (c.getLong(iBottomId) == p_itemId) || (c.getLong(iDressId) == p_itemId)) {
					itemOccurances.add(c.getLong(iRowId));
				}
			}
			return itemOccurances;
		}
}

