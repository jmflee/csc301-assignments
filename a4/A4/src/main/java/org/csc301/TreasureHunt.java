package org.csc301;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TreasureHunt {

	private final int DEFAULT_SONARS = 3; // default number of available sonars
	private final int DEFAULT_RANGE = 200; // default range of a sonar
	private Grid islands; // the world where the action happens!
	private int height, width, landPercent;
	private int sonars, range; // user defined number of sonars and range
	private String state; // state of the game (STARTED, OVER)
	private ArrayList<Node> path; // the path to the treasure

	/**
	 * Create the frame.
	 */
	public TreasureHunt() {
		// The default constructor
		this.sonars = DEFAULT_SONARS;
		this.range = DEFAULT_RANGE;
		this.islands = new Grid();
		this.height = islands.getHeight();
		this.width = islands.getWidth();
		this.landPercent = islands.getPercent();
		this.state = "STARTED";
	}

	public TreasureHunt(int height, int width, int landPercent, int sonars,
			int range) {
		
		this.islands = new Grid(width, height, landPercent);
		this.height = height;
		this.width = width;
		this.landPercent = landPercent;
		this.sonars = sonars;
		this.range = range;
		this.state = "STARTED";
	}

	public void processCommand(String command) throws HeapFullException,
			HeapEmptyException {
		// The allowed commands are: 
		// SONAR to drop the sonar in hope to detect treasure
		// GO direction to move the boat in some direction
		// For example, GO NW means move the boat one cell up left (if the cell is navigable; if not simply ignore the command)
		String [] move = command.split(" ");
		// Player wants to drop a sonar
		if (move.length == 1 && move[0].equals("SONAR")) {
			if (sonars == 0) {
				state = "OVER";
				return;
			}
			sonars--;
			Node scan = islands.getTreasure(range);
			if (scan == null && sonars == 0) {
				state = "OVER";
			}
			if (scan != null) {
				islands.findPath(islands.getBoat(), scan);
				path = islands.retracePath(islands.getBoat(), scan);
				state = "OVER";
				System.out.println(this.getMap());
		        System.out.println(this.getState());
		        System.out.println("COMPLETED WITH " + this.pathLength() + " UNITS TO TRAVEL");
			}
			return;
		} 
		// Player wants to move the ship somewhere
		else if (move.length == 2 && move[0].equals("GO")) {
			islands.move(move[1]);
		}
		
	}

	public int pathLength() {
		if (path == null)
			return 0;
		else return path.size();
	}

	public String getMap() {
		return islands.drawMap();
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getLandPercent() {
		return landPercent;
	}
	
	public int getSonars() {
		return sonars;
	}
	
	public int getRange() {
		return range;
	}
	
	public String getState() {
		return state;
	}


	public void play(String pathName) throws FileNotFoundException,
			HeapFullException, HeapEmptyException {
		// Read a batch of commands from a text file and process them.
		Scanner readAndgetData = new Scanner(new File(pathName));
		while (readAndgetData.hasNextLine() && this.getState() == "STARTED"){
			String content = readAndgetData.nextLine();
			processCommand(content);
		}
		
		//close the file
		readAndgetData.close();

	}
	
}
