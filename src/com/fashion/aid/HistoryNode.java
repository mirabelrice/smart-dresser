package com.fashion.aid;

public class HistoryNode {
	private String dateWorn;
	private long historyOutfitId;
	private long historyTableId;
	
	public HistoryNode(){
		historyOutfitId = 0;
		historyTableId = 0;
		dateWorn = "";
	}
	
	public HistoryNode(long p_historyOutfitId, String p_dateWorn){
		historyOutfitId = p_historyOutfitId;
		historyTableId = 0;
		dateWorn = p_dateWorn;
	}
	
	public HistoryNode(long p_historyTableId, long p_historyOutfitId, String p_dateWorn){
		historyTableId = p_historyTableId;
		historyOutfitId = p_historyOutfitId;
		dateWorn = p_dateWorn;
	}
	
	public void setHistoryOutfitId(long p_historyOutfitId){
		historyOutfitId = p_historyOutfitId;
	}
	
	public void setHistoryTableId(long p_historyTableId){
		historyTableId = p_historyTableId;
	}
	
	public void setDateWorn(String p_dateWorn){
		dateWorn = p_dateWorn;
	}
	
	public long getHistoryOutfitId(){
		return historyOutfitId;
	}
	
	public long getHistoryTableId(){
		return historyTableId;
	}
	
	public String getDateWorn(){
		return dateWorn;
	}
}
