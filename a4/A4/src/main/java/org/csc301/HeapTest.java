package org.csc301;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

import org.junit.Test;

public class HeapTest {

	/*
	 * Heap Tests
	 */
	
	@Test
	public void sortUpTest() {
		Heap<Node> heap = new Heap<> (3);
		Node a = new Node(true,1,1);
		Node b = new Node(false, 2 ,2);
		Node c = new Node(false, 3, 4);
		try {
			heap.add(a);
			heap.add(b);
			heap.add(c);
			assertEquals(heap.contains(a), true); // Access the sortup method
		} catch (HeapFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void sortDownTest() {
		Heap<Node> heap = new Heap<> (3);
		Node a = new Node(true,1,1);
		Node b = new Node(false, 2 ,2);
		Node c = new Node(false, 3, 4);
		try {
			heap.add(a);
			heap.add(b);
			heap.add(c);
			heap.removeFirst();
			assertEquals(heap.contains(a), false); // Access the sortup method
			assertEquals(heap.contains(b), true); // Access the sortup method
			assertEquals(heap.contains(c), true); // Access the sortup method
		} catch (HeapFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeapEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
