package org.csc301;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class GridTest {

	/* 
	 * Grid Tests
	 */
	
	@Test
	public void gridConstructorTest() {
		Grid grid = new Grid();
		assertEquals(grid.getHeight(), 15);
		assertEquals(grid.getWidth(), 60);
		assertEquals(grid.getPercent(), 20);
	}
	
	@Test
	public void gridParameterConstructorTest() {
		Grid grid = new Grid(10, 20, 30);
		assertEquals(grid.getHeight(), 20);
		assertEquals(grid.getWidth(), 10);
		assertEquals(grid.getPercent(), 30);
	}
	
	@Test
	public void findPathTest1() {
		TreasureHunt h = new TreasureHunt(10, 20, 1, 40, 500);
		try {
			h.play("game.txt"); // play method uses the processCommand method which uses the findPath method
			assertEquals(h.getState(), "OVER");
			assertTrue(h.pathLength() > 0);
		} catch (FileNotFoundException | HeapFullException | HeapEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void findPathTest2() {
		TreasureHunt h = new TreasureHunt(1000, 1000, 99, 1, 1);
		try {
			h.play("game.txt");  // play method uses the processCommand method which uses the findPath method
			assertEquals(h.getState(), "OVER");
			assertTrue(h.pathLength() == 0);
		} catch (FileNotFoundException | HeapFullException | HeapEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void retracePathTest1() {
		TreasureHunt h = new TreasureHunt(10, 20, 1, 40, 500);
		try {
			h.play("game.txt"); // play method uses the processCommand method which uses the retracePath method
			assertEquals(h.getState(), "OVER");
			assertTrue(h.pathLength() > 0);
		} catch (FileNotFoundException | HeapFullException | HeapEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	
	@Test
	public void retracePathTest2() {
		TreasureHunt h = new TreasureHunt(1000, 1000, 99, 1, 1);
		try {
			h.play("game.txt");  // play method uses the processCommand method which uses the retracePath method
			assertEquals(h.getState(), "OVER");
			assertTrue(h.pathLength() == 0);
		} catch (FileNotFoundException | HeapFullException | HeapEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getTreasureTest1() {
		TreasureHunt h = new TreasureHunt(10, 20, 1, 40, 500);
		try {
			h.play("game.txt"); // play method uses the processCommand method which uses the retracePath method
			assertEquals(h.getState(), "OVER");
			assertTrue(h.pathLength() > 0);
		} catch (FileNotFoundException | HeapFullException | HeapEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	
	@Test
	public void getTreasureTest2() {
		TreasureHunt h = new TreasureHunt(1000, 1000, 99, 1, 1);
		try {
			h.play("game.txt");  // play method uses the processCommand method which uses the getTreasure method
			assertEquals(h.getState(), "OVER");
			assertTrue(h.pathLength() == 0);
		} catch (FileNotFoundException | HeapFullException | HeapEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void moveTest1() {
		TreasureHunt h = new TreasureHunt(10, 20, 1, 40, 500);
		try {
			h.play("game.txt"); // play method uses the processCommand method which uses the move method
			assertEquals(h.getState(), "OVER");
			assertTrue(h.pathLength() > 0);
		} catch (FileNotFoundException | HeapFullException | HeapEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	
	@Test
	public void moveTest2() {
		TreasureHunt h = new TreasureHunt(1000, 1000, 99, 1, 1);
		try {
			h.play("game.txt");  // play method uses the processCommand method which uses the move method
			assertEquals(h.getState(), "OVER");
			assertTrue(h.pathLength() == 0);
		} catch (FileNotFoundException | HeapFullException | HeapEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
