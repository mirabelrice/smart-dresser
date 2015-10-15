package com.fashion.aid;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.util.Log;

public class MaxHeap{
	private int heapSize;
	private int itemCount;
	private HistoryNode heap[];

	public MaxHeap(){
		heapSize = 200;
		itemCount = 0;
		heap = new HistoryNode[heapSize];
	}

	public MaxHeap(int p_heapSize){
		heapSize = p_heapSize;
		itemCount = 0;
		heap = new HistoryNode[heapSize];
	}

	public void enqueue(HistoryNode newNode){
		if(itemCount == heapSize){
			return;//heap is full
		}
		itemCount++;
		heap[itemCount - 1] = newNode;
		upHeap(itemCount - 1);
	}
	
	public int getSize(){
		return itemCount;
	}

	public boolean isEmpty(){
		if(itemCount == 0){
			return true;
		}
		return false;
	}

	public HistoryNode getTop(){
		return (heap[0]);
	}

	public HistoryNode[] getHeap(){
		return heap;
	}

	public boolean dequeue(){
		if(isEmpty()){
			return false;
		}
		//replace root with last element
		HistoryNode newRoot = heap[itemCount - 1];
		heap[0] = newRoot;
		heap[itemCount - 1] = null;
		itemCount--;

		if(itemCount > 0){
			downHeap(0);
		}
		return true;
	}
	
	public HistoryNode peek(){
		return heap[0];
	}
	
	public HistoryNode[] heapSort(){
		HistoryNode[] sortedHeap = new HistoryNode[itemCount];
		
		int i = 0;
		while(!isEmpty()){
			
			sortedHeap[i] = peek();
			dequeue();
			//Log.i("sorting", " " + sortedHeap[i].getDateWorn());
			i++;	
		}
		return sortedHeap;
	}

	private void upHeap(int p_index){
		int parent;
		HistoryNode temp;
		
		if(p_index != 0){
			parent = getParent(p_index);
			if(isLessThan(heap[parent].getDateWorn(), heap[p_index].getDateWorn())){//current index is greater than its parent. must switch
				temp = heap[parent];
				heap[parent] = heap[p_index];
				heap[p_index] = temp;
				upHeap(parent);
			}
		}
	}

	private void downHeap(int p_index){
		int right = getRight(p_index);
		int left = getLeft(p_index);
		int maxElement;
		HistoryNode temp;

		if(itemCount <= right){
			if(itemCount <= left){
				return;
			}
			maxElement = left;
		}else{
			if(isLessOrEqual(heap[left].getDateWorn(), heap[right].getDateWorn())){
				maxElement = right;
			}else{
				maxElement = left;
			}
		}

		if(isLessThan(heap[p_index].getDateWorn(), heap[maxElement].getDateWorn())){
			temp = heap[p_index];
			heap[p_index] = heap[maxElement];
			heap[maxElement] = temp;	
			downHeap(maxElement);
		}	
	}

	private int getParent(int p_index){
		return ((p_index - 1) / 2);
	}

	private int getLeft(int p_index){
		return ((2 * p_index) + 1);
	}

	private int getRight(int p_index){
		return ((2 * p_index) + 2);
	}

	private boolean isLessThan(String p_date, String p_otherDate){
		int weight = getDaysBetween(p_date);
		int otherWeight = getDaysBetween(p_otherDate);

		if(weight < otherWeight){
			return true;
		}
		return false;
	}
	
	private boolean isLessOrEqual(String p_date, String p_otherDate){
		int weight = getDaysBetween(p_date);
		int otherWeight = getDaysBetween(p_otherDate);

		if(weight <= otherWeight){
			return true;
		}
		return false;
	}
	
	
	/*
	calculate the days between current date and date worn to determine history weight
	*/
	private int getDaysBetween(String p_dateWorn){  
		Calendar currentDate = Calendar.getInstance(); 
		Calendar temp = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		int daysBetween = 0;  
		
		try{
			temp.setTime(dateFormat.parse(p_dateWorn));
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

}