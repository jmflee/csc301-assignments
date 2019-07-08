package org.csc301;

import static org.junit.Assert.*;

import org.junit.Test;

public class NodeTest {
	
	/*
	 * Node Tests
	 */
	@Test
	public void compareToTest1() {
		Node a = new Node(true,1,1);
		Node b = new Node(false, 2 ,2);
		assertEquals(a.compareTo(b), 0);
	}
	
	@Test
	public void compareToTest2() {
		Node a = new Node(true,1,1);
		Node b = new Node(true, 1 ,1);
		assertEquals(a.compareTo(b), 0);
	}
	
	@Test
	public void equalsTest1() {
		Node a = new Node(true,1,1);
		assertEquals(a.walkable, true);
	}
	
	@Test
	public void equalsTest2() {
		Node a = new Node(false,1,1);
		assertEquals(a.walkable, false);
	}
	
	
	@Test
	public void equalsTest3() {
		Node a = new Node(false,1,1);
		assertEquals(a.getFCost(), 0);
	}
	
	@Test
	public void equalsTest4() {
		Node a = new Node(false,1,1);
		Node b = new Node(false,1,1);
		assertEquals(a.equals(b), true);
	}
	
	@Test
	public void equalsTest5() {
		Node a = new Node(false,1,1);
		Node b = new Node(true,2,3);
		assertEquals(a.equals(b), false);
	}
	
}
