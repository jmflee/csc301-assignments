package org.csc301;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class TreasureTest {
	
	/*
	 * TreasureHunt Tests
	 */
	
	@Test
	public void treasureConstructorTest() {
		TreasureHunt h = new TreasureHunt();
		assertEquals(h.getHeight(), 15);
		assertEquals(h.getWidth(), 60);
		assertEquals(h.getLandPercent(), 20);
		assertEquals(h.getSonars(), 3);
		assertEquals(h.getRange(), 200);
		assertEquals(h.getState(), "STARTED");
	}
	
	@Test
	public void treasureParamaterConstructorTest() {
		TreasureHunt h = new TreasureHunt(10, 20, 30, 40, 50);
		assertEquals(h.getHeight(), 10);
		assertEquals(h.getWidth(), 20);
		assertEquals(h.getLandPercent(), 30);
		assertEquals(h.getSonars(), 40);
		assertEquals(h.getRange(), 50);
		assertEquals(h.getState(), "STARTED");
	}
	
	@Test
	public void pathLengthTest1() {
		TreasureHunt h = new TreasureHunt(10, 20, 30, 40, 50);
		assertEquals(h.pathLength(), 0);
	}
	
	@Test
	public void pathLengthTest2() {
		TreasureHunt h = new TreasureHunt(10, 20, 1, 40, 100);
		try {
			h.play("game.txt");
		} catch (FileNotFoundException | HeapFullException | HeapEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(h.pathLength() > 0);
	}
	
	@Test
	public void getMapTest() {
		TreasureHunt h = new TreasureHunt(10, 20, 70, 40, 50);
		assertEquals(h.getMap().contains("B"), true);
		assertEquals(h.getMap().contains("+"), true);
		assertEquals(h.getMap().contains("."), true);
		assertEquals(h.getMap().contains("1"), true);
		assertEquals(h.getMap().contains("2"), true);
		assertEquals(h.getMap().contains("3"), true);
		assertEquals(h.getMap().contains("4"), true);
		assertEquals(h.getMap().contains("5"), true);
		assertEquals(h.getMap().contains("6"), true);
		assertEquals(h.getMap().contains("7"), true);
		assertEquals(h.getMap().contains("8"), true);
		assertEquals(h.getMap().contains("9"), true);
		assertEquals(h.getMap().contains("0"), true);
		assertEquals(h.getState(), "STARTED");
	}
	
	@Test
	public void playTest1() {
		TreasureHunt h = new TreasureHunt(10, 20, 1, 40, 100);
		try {
			h.play("game.txt");
			assertEquals(h.getState(), "OVER");
			assertTrue(h.pathLength() > 0);
		} catch (FileNotFoundException | HeapFullException | HeapEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void playTest2() {
		TreasureHunt h = new TreasureHunt(1000, 1000, 99, 1, 1);
		try {
			h.play("game.txt");
			assertEquals(h.getState(), "OVER");
			assertTrue(h.pathLength() == 0);
		} catch (FileNotFoundException | HeapFullException | HeapEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
