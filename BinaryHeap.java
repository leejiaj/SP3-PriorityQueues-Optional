/**
 * @author Leejia James
 *
 * Optional Tasks: Priority Queues - Qns 2 and 3
 * 
 * Ver 1.0: 2018/09/18
 *  	@author Priyanka Awaraddi
 * 		@author Leejia James
 * 		Implemented bounded-size Binary Heap
 * Ver 2.0: 2018/09/22
 * 		@author Leejia James
 *		Implemented heap sort and its related methods in the binary heap class
 *		Implemented the problem of finding the kth largest element of a stream, 
 *		Compared its performance with code that uses Java's priority queue
 */

package lxj171130;

import java.sql.Array;

//Starter code for bounded-size Binary Heap implementation
//Changed signature of heapSort changing <T> to <T extends Comparable<? super T>>
//poll returns null if pq is empty (not false)

import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

public class BinaryHeap<T extends Comparable<? super T>> {
	T[] pq;
	Comparator<T> comp;
	int size;
	// Constructor for building an empty priority queue using natural ordering of T
	public BinaryHeap(T[] q) {
		// Use a lambda expression to create comparator from compareTo
		this(q, (T a, T b) -> a.compareTo(b));
	}
 
	// Constructor for building an empty priority queue with custom comparator
	public BinaryHeap(T[] q, Comparator<T> c) {
		pq = q;
		comp = c;
		size = 0;
	}
 
	/** Build a priority queue with a given array q, using q[0..n-1].
	*  It is not necessary that n == q.length.
	*  Extra space available can be used to add new elements.
	*  Implement this if solving optional problem.  To be called from heap sort.
	*/
	private BinaryHeap(T[] q, Comparator<T> c, int n) {
		pq = q;
		comp = c;
		size = n;
		// You need to add more code here to build queue
		buildHeap();
	}

    /**
     * Inserts a new element x to the priority queue
     *
     * @param x element to add
     * 
     * @exception throws exception if priority queue is full
     */
	public void add(T x) throws Exception {
		internalAdd(x);
	}
 
    /**
     * Internal method to add new element x to the priority queue
     * Used by both add() and offer() methods
     *
     * @param x element to add
     * 
     * @exception throws exception if priority queue is full
     */
	private void internalAdd(T x) throws Exception
	{
		if(size == pq.length)
			throw new Exception("Priority queue is full");
		move(size, x);
		percolateUp(size);
		size++;
	}
	
    /**
     * Inserts a new element x to the priority queue
     *
     * @param x element to add
     * 
     * @return true if the element is added, false if the element 
     * is not added because the priority queue is full
     */
	public boolean offer(T x) {
		try
		{
			internalAdd(x);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

    /**
     * Removes and returns the element with maximum priority
     * 
     * @exception throws exception if priority queue is empty
     * 
     * @return the element with maximum priority (min value)
     */
	public T remove() throws Exception {
		return internalRemove();
	}
 
    /**
     * Internal method to remove and return the element with maximum priority
     * Used by both remove() and poll() methods
     * 
     * @exception throws exception if priority queue is empty
     * 
     * @return the element with maximum priority (min value)
     */
	private T internalRemove() throws Exception
	{
		if(size == 0)
			throw new Exception("Priority queue is empty");
		T min = pq[0];
		move(0, pq[size-1]);
		size--;
		percolateDown(0);
		return min;
	}

    /**
     * Removes and returns the element with maximum priority
     * 
     * @return returns the element with maximum priority (min value), 
     * returns null if priority queue is empty
     */
	public T poll() {
		try
		{
			T x = internalRemove();
			return x;
		}
		catch(Exception e)
		{
			return null;
		}
	}

    /**
     * Returns the element with maximum priority without removing it
     * 
     * @return the element with maximum priority (min value), 
     * returns null if priority queue is empty
     */
	public T peek() {
		if(size == 0)
			return null;
		return pq[0];
	}

	/** pq[i] may violate heap order with parent */
	void percolateUp(int i) { 
		T x = pq[i];
		while(comp.compare(pq[parent(i)],x) > 0 && i > 0)
		{
			move(i,pq[parent(i)]);
			i = parent(i);
		}
		move(i,x);
	}

	/** pq[i] may violate heap order with children */
	void percolateDown(int i) { 
		T x = pq[i];
		int c = leftChild(i);
		while(c <= size-1)
		{
			if(c < size-1 && comp.compare(pq[c], pq[c+1]) > 0)
			{
				c++;
			}			
			if(comp.compare(x, pq[c]) <= 0)
				break;
			pq[i] = pq[c];
			i = c;
			c = leftChild(i);
		}
		move(i,x);
	}

	void printBinaryHeap()
	{
		for(int i=0; i<size; i++)
		{
			System.out.print(pq[i] + " ");
		}
	
		System.out.println();
	}
	
	
	// Assign x to pq[i].  Indexed heap will override this method
	void move(int i, T x) {
		pq[i] = x;
	}

	int parent(int i) {
		return (i-1)/2;
	}

	int leftChild(int i) {
		return 2*i + 1;
	}

//end of functions for team project




//start of optional problem: heap sort (Q2)

 /** Create a heap.  Precondition: none. 
  *  Implement this ifsolving optional problem
  */
 void buildHeap() {
	 for (int i = parent(size - 1); i>=0; i--) {
		 percolateDown(i);
	 }
	 
 }

 /* sort array arr[].
    Sorted order depends on comparator used to buid heap.
    min heap ==> descending order
    max heap ==> ascending order
    Implement this for optional problem
  */
 public static<T extends Comparable<? super T>> void heapSort(T[] arr, Comparator<T> c) throws Exception { /* to be implemented */
	 int n = 0;
	 for( int i=0; i < arr.length && arr[i]!= null ; i++) {
		 n++;
	 }
	 BinaryHeap<T> bh1 = new BinaryHeap<T>(arr, c, n);
	 for(int i=n-1; i>=0; i--) {
		bh1.pq[i] = bh1.remove();
	 }	 
 }

 // Sort array using natural ordering
 public static<T extends Comparable<? super T>> void heapSort(T[] arr) throws Exception {
	heapSort(arr, (T a, T b) -> a.compareTo(b));
 }
//end of optional problem: heap sort (Q2)



//start of optional problem: kth largest element (Q3)

 public void replace(T x) throws Exception {
	/* TO DO.  Replaces root of binary heap by x if x has higher priority
	     (smaller) than root, and restore heap order.  Otherwise do nothing. 
	   This operation is used in finding largest k elements in a stream.
	   Implement this if solving optional problem.
	 */
	 remove();
	 add(x);
 }

 /** Return the kth largest element of stream using custom comparator.
  *  Assume that k is small enough to fit in memory, but the stream is arbitrarily large.
  *  If stream has less than k elements return null.
 * @throws Exception 
  */
 public static<T extends Comparable<? super T>> T kthLargest(Iterator<T> stream, int k, Comparator<T> c) throws Exception {
	 T[] arr = (T[]) new Comparable[k];
	 BinaryHeap<T> bh = new BinaryHeap<T>(arr, c);
	 while (stream.hasNext()) {
		 if (bh.size < k) {
			bh.add(stream.next()); 
		 }
		 else {
			 T x = stream.next();
			 if (c.compare(x, (T) bh.peek()) > 0) {
				 bh.replace(x);
				 bh.percolateDown(0);
			 }
		 }
	 }
	 if (bh.size < k) {
		 return null;
	 }
	 else {
		 return bh.peek();
	 }
 }

 /** Return the kth largest element of stream using natural ordering.
  *  Assume that k is small enough to fit in memory, but the stream is arbitrarily large.   
  *  If stream has less than k elements return null.
 * @throws Exception 
  */
 public static<T extends Comparable<? super T>> T kthLargest(Iterator<T> stream, int k) throws Exception {
	return kthLargest(stream, k, (T a, T b) -> a.compareTo(b));
 }
//end of optional problem: kth largest element (Q3)
 
}
