package com.fashion.aid;

public class MinHeap{
	private int heapSize;
	private int itemCount;
	private Outfit[] heap;

	public MinHeap(){
		heapSize = 200;
		itemCount = 0;
		heap = new Outfit[heapSize];
	}

	public MinHeap(int p_heapSize){
		heapSize = p_heapSize;
		itemCount = 0;
		heap = new Outfit[heapSize];
	}

	public void enqueue(Outfit p_outfit){
		if(itemCount == heapSize){
			return;//heap is full
		}
		itemCount++;
		heap[itemCount - 1] = p_outfit;
		upHeap(itemCount - 1);
	}

	public boolean isEmpty(){
		if(itemCount == 0){
			return true;
		}
		return false;
	}

	public Outfit getTop(){
		return (heap[0]);
	}

	public Outfit[] getHeap(){
		return heap;
	}

	public boolean dequeue(){
		if(isEmpty()){
			return false;
		}
		//replace root with last element
		Outfit newRoot = heap[itemCount - 1];
		heap[0] = newRoot;
		heap[itemCount - 1] = null;
		itemCount--;

		if(itemCount > 0){
			downHeap(0);
		}
		return true;
	}
	
	public Outfit peek(){
		return heap[0];
	}
	
	public Outfit[] heapSort(){
		Outfit[] sortedHeap = new Outfit[itemCount];
		int i = 0;
		while(!isEmpty()){
			sortedHeap[i] = peek();
			dequeue();
			i++;	
		}
		return sortedHeap;
	}

	private void upHeap(int p_index){
		int parent;
		Outfit temp;
		
		if(p_index != 0){
			parent = getParent(p_index);
			if(isLessThan(heap[p_index], heap[parent])){//current index is less than its parent. must switch
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
		int minElement;
		Outfit temp;

		if(itemCount <= right){
			if(itemCount <= left){
				return;
			}
			minElement = left;
		}else{
			if(isLessOrEqual(heap[left], heap[right])){
				minElement = left;
			}else{
				minElement = right;
			}
		}

		if(isLessThan(heap[minElement], heap[p_index])){
			temp = heap[p_index];
			heap[p_index] = heap[minElement];
			heap[minElement] = temp;	
			downHeap(minElement);
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

	private boolean isLessThan(Outfit p_outfit, Outfit p_otherOutfit){
		int weight = p_outfit.getHistoryWeight();
		int otherWeight = p_otherOutfit.getHistoryWeight();

		if(weight < otherWeight){
			return true;
		}
		return false;
	}

	private boolean isLessOrEqual(Outfit p_outfit, Outfit p_otherOutfit){
		int weight = p_outfit.getHistoryWeight();
		int otherWeight = p_otherOutfit.getHistoryWeight();

		if(weight <= otherWeight){
			return true;
		}
		return false;
	}	
	
	public int getSize() {
		return heapSize;
	}

}