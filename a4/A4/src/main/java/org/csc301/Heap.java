package org.csc301;

public class Heap<T extends HeapItem> {
	
	// Note the T is a parameter representing a type that extends the HeapItem interface
	// This a new way to use inheritance!

	protected T[] items; // Array that is used to store heap items. items[0] is the highest priority element.
	protected int maxHeapSize; // The capacity of the heap
	protected int currentItemCount; // How many elements we have currently on the heap

	public Heap(int maxHeapSize) {
		this.maxHeapSize = maxHeapSize;
		items = (T[]) new HeapItem[maxHeapSize];
		currentItemCount = 0; // heap is empty!
	}

	public boolean isEmpty() {
		return currentItemCount == 0;
	}

	public boolean isFull() {
		return currentItemCount == maxHeapSize;
	}

	public void add(T item) throws HeapFullException
	// Adds item T to its correct position on the heap
	{
		if (isFull())
			throw new HeapFullException();
		else {
			item.setHeapIndex(currentItemCount);
			items[currentItemCount] = item;  // the element is added to the bottom
			sortUp(item); // Move the element up to its legitimate place. Check the diagram on the handout!
			currentItemCount++;
		}
	}

	public boolean contains(T item)
	// Returns true if item is on the heap
	// Otherwise returns false
	{
		return items[item.getHeapIndex()].equals(item);
	}

	public int count() {
		return currentItemCount;
	}

	public void updateItem(T item) {
		sortUp(item);
	}

	public T removeFirst() throws HeapEmptyException
	// Removes and returns the element sitting on top of the heap
	{
		if (isEmpty())
			throw new HeapEmptyException();
		else {
			T firstItem = items[0]; // element of top of the heap is stored in firstItem variable
			currentItemCount--;
			items[0] = items[currentItemCount]; //last element moves on top
			items[0].setHeapIndex(0);
			sortDown(items[0]); // move the element to its legitimate position. Please check the diagram on the handout.
			return firstItem;
		}
	}
	
	
	/**
	 * 
	 * Swap function, used to swap elements in heap
	 * 
	 * @param items
	 * @param index
	 * @param pindex
	 * @param eleToChange
	 */
	private void swap(T[] items, int index1, int index2,  T eleToChange){
		items[index1] = items[index2];
		items[index1].setHeapIndex(index1);
		items[index2] = eleToChange;
		items[index2].setHeapIndex(index2);
	}
	
	private void sortUp(T item) {
		// Implement this method according to the diagram on the handout.
		// Also: the indices of children and parent elements satisfy some relationships.
		// The formulas are on the handout.
		
		//check if the item is not in the heap
		if(!contains(item)){
			//do nothing
			return;
		}
		
		//give a index number
		int index = item.getHeapIndex();
		
		//check if index is 0
		if(index == 0){
			//do nothing
			return;
		}
		
		//assign the parent index
		int pindex = (index - 1) /2;
		
		//if the item is smaller than its parent, swap them, and then do recursion
		if(items[index].compareTo(items[pindex]) < 0){
			T elementToChange = items[index];
			
			//do swap
			swap(items,index, pindex, elementToChange);
			sortUp(elementToChange);
		}
		
		return;
		
	}
	
	private void sortDown(T item) {
		// Implement this method according to the diagram on the handout.
		// Also: the indices of children and parent elements satisfy some relationships.
		// The formulas are on the handout.
		
		// if item not in the heap, do nothing
				if (!contains(item)){
				   return;
				}
				
				//heap indices
				int index = item.getHeapIndex();
				int right = index * 2 + 2;
				int left = index * 2 + 1;
				
				//boolean variables used to keep track if a left or right node exists
				boolean leftNodeFound , rightNodeFound;
				
				//check for a left or right node
				leftNodeFound = (left + 1 <= currentItemCount);  
				rightNodeFound = right + 1 <= currentItemCount;
				
				// if no left node is found , do nothing
				if (leftNodeFound == false) {
				return;
				} 
				
				// only have left child, and this item is bigger than its left child
				// change the position with its left child
				if (leftNodeFound && rightNodeFound == false && items[index].compareTo(items[left]) > 0){
					
					T ElementToChange = items[index];
					swap(items, index,left, ElementToChange);
					sortDown(ElementToChange);
				}
				
				// have both children
				if (rightNodeFound && leftNodeFound ){
					boolean righIsSmaller = item.compareTo(items[right]) <= 0;
					boolean leftIsSmaller = item.compareTo(items[left]) <= 0;
					boolean left_less = items[left].compareTo(items[right]) <= 0;
					
					// if this item is less than both of its children, do nothing
					if (righIsSmaller && leftIsSmaller) {
						return;
					}
					
					// if one of the children is less than this item
					else{
						// if left child is less than right child, change the positiong of
						// this item and its left child
						if(left_less){
							T ElementToChange = items[index];
							swap(items, index, left, ElementToChange);
							sortDown(ElementToChange);
						}
						// if right child is less than left child, change the position of
						// this item and its right child
						else{
							T ElementToChange = items[index];
							swap(items, index, right, ElementToChange);
							sortDown(ElementToChange);
						}
					}
					
				}
		
		
	}
}
