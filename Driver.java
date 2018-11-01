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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Driver {
	 public static void main(String[] args) throws Exception
	 {	
		Scanner in = new Scanner(System.in);
		
		Integer[] arr1 = new Integer[10];
		arr1[0] = Integer.valueOf(4);
		arr1[1] = Integer.valueOf(7);
		arr1[2] = Integer.valueOf(0);
		arr1[3] = Integer.valueOf(44);
		arr1[4] = Integer.valueOf(7);
		arr1[5] = Integer.valueOf(0);
		arr1[6] = Integer.valueOf(7);
		arr1[7] = Integer.valueOf(0);
		arr1[8] = Integer.valueOf(4);
		
		int k;
		double t;
		
		whileloop:
		while(in.hasNext()) {
			int com = in.nextInt();
			switch(com) {
			case 1:  // heap sort, sort array in descending order
				System.out.print("Initial array: ");
				for(int i=0; i<arr1.length && arr1[i] != null; i++) {
					System.out.print(arr1[i]+" ");
				}
				System.out.println();
				BinaryHeap.heapSort(arr1);	
				System.out.print("Array sorted in descending order: ");
				for(int i=0; i<arr1.length && arr1[i] != null; i++) {
					System.out.print(arr1[i]+" ");
				}
				System.out.println();
				break;	
			case 2:  // heap sort, sort array in ascending order
				System.out.print("Initial array: ");
				for(int i=0; i<arr1.length && arr1[i] != null; i++) {
					System.out.print(arr1[i]+" ");
				}
				System.out.println();
				BinaryHeap.heapSort(arr1, (Integer a, Integer b) -> b.compareTo(a));
				System.out.print("Array sorted in descending order: ");
				for(int i=0; i<arr1.length && arr1[i] != null; i++) {
					System.out.print(arr1[i]+" ");
				}
				System.out.println();
				break;	
			case 3:  // return kth largest element of stream using natural ordering
				System.out.print("Stream: ");
				List<Integer> stream =  new Random().ints(0,100).limit(100).boxed().collect(Collectors.toList());
				for (Integer num: stream) {
					System.out.print(num + " ");
				}
				System.out.println();
				System.out.print("Enter k: ");
				k = in.nextInt();
				System.out.print("kth largest element: ");
				System.out.println(BinaryHeap.kthLargest(stream.iterator(), k));	
				break;	
			case 4:  // return kth largest element of stream using custom comparator
				System.out.print("Stream: ");
				List<Integer> stream1 =  new Random().ints(0,100).limit(100).boxed().collect(Collectors.toList());
				for (Integer num: stream1) {
					System.out.print(num + " ");
				}
				System.out.println();
				System.out.print("Enter k: ");
				k = in.nextInt();
				System.out.print("kth largest element using custom comparator: ");
				System.out.println(BinaryHeap.kthLargest(stream1.iterator(), k,  (Integer a, Integer b) -> b.compareTo(a)));
				break;	
			case 5:  // kth largest element of stream using the priority queue created 
					 // and Java's priority queue (comparison)
				System.out.println("Size of stream: (enter large values eg: 1000000)");
				int n = in.nextInt();
				List<Integer> numbers =  new Random().ints(0,n).limit(n).boxed().collect(Collectors.toList());
				
//				Print the stream
//				System.out.println("Stream: ");
//				for(Integer num: numbers) {
//					System.out.print(num + " ");
//				}
//				System.out.println();
				
				System.out.println("Enter k: (enter small values eg: 100)");
				k = in.nextInt();
				
				System.out.println("kth largest element using our Priority Queue: ");     
				t = System.nanoTime();
				System.out.println(BinaryHeap.kthLargest(numbers.iterator(), k));
		        System.out.printf("(%.3f seconds)%n", (System.nanoTime()-t) / 1000000000);
		        
		        System.out.println("kth largest element using Java's Priority Queue: ");  
		        t = System.nanoTime();
		        PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>();
		        Iterator<Integer> it = numbers.iterator();		        
		        while (it.hasNext()) {
		        	if (pQueue.size() < k) {
		        		pQueue.add(it.next()); 
		        	}
		        	else {
		        		Integer x = it.next();
		        		if (x.compareTo( pQueue.peek()) > 0) {
		        			pQueue.remove();
		        			pQueue.add(x);
		        		}
		        	}
		        }
		        String str = null;
		        if (pQueue.size() < k) {
		        	System.out.println(str);
		        }
		        else {
		        	System.out.println(pQueue.peek());
		        }		
		        System.out.printf("(%.3f seconds)%n", (System.nanoTime()-t) / 1000000000);
				break;
			default:  // Exit loop
				break whileloop;
			}
		}
	 }

}
