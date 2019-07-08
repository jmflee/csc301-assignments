package org.csc301;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Grid {

	private final int DEFAULT_WIDTH = 60; // default width of the world map - gridX runs from 0 to 59
	private final int DEFAULT_HEIGHT = 15; // default height of the map - gridY runs from 0 to 14
	private final int DEFAULT_PERCENT = 20; // this is the percentageof the map occupied by islands
	private int width, height; // user defined width and height, if one is not using defaults
	private int percent; // user defined percentage of islands on the map
	private Node treasure; // points to the map node where the Redbeard treasure is sunken
	private Node boat; // points to the current location of our boat on the map
	private boolean found;
	
	protected Node[][] map; // the map

	public Grid() {
		width = DEFAULT_WIDTH;
		height = DEFAULT_HEIGHT;
		percent = DEFAULT_PERCENT;
		buildMap();
	}

	public Grid(int width, int height, int percent) {
		this.width = width;
		this.height = height;
		if (percent <= 0 || percent >= 100)
			this.percent = DEFAULT_PERCENT;
		else
			this.percent = percent;
		buildMap();
	}

	private void buildMap() {
		// Your implementation goes here
		// For each map position (i,j) you need to generate a Node with can be navigable or it may belong to an island
		// You may use ideas from Lab3 here.
		// Don't forget to generate the location of the boat and of the treasure; they must be on navigable waters, not on the land!
		
		map = new Node[height][width];
		
		Random rand = new Random();
		
		// place the boat randomly on the map
		int a, b;
		
		//a is height, b is width
		
		a = rand.nextInt(height);
		b = rand.nextInt(width);
		
		boat = new Node(true, b, a);
		map[a][b] = boat;
		
		// put the treasure randomly, make sure the position is not same as positon of the boat
		boolean placeTreasure = false;
		
		while (placeTreasure == false){
			a = rand.nextInt(height);
			b = rand.nextInt(width);
			if (map[a][b] == null) {
				treasure = new Node(true, b, a);
				map[a][b] = treasure;
				placeTreasure = true;
			}
		}
		
		// make and fill the map/grid with the appropriate items
		for(int i=0; i < height; i++)
			for(int j= 0 ; j <width; j++){
				if (map[i][j] == null){
					boolean obstacle = (rand.nextInt(99) < percent);
					// place island on map
					if (obstacle){
						map[i][j] = new Node(false,j,i);
					}
					else{
						map[i][j] = new Node(true,j,i);
					}
				}
			}
	
	}

	public String drawMap() {
		// provided for your convenience
		String result = "";
		String hline = "       ";
		String extraSpace;
		for (int i = 0; i < width / 10; i++)
			hline += "         " + (i + 1);
		result += hline + "\n";
		hline = "       ";
		for (int i = 0; i < width; i++)
			hline += (i % 10);
		result += hline + "\n";
		for (int i = 0; i < height; i++) {
			if (i < 10)
				extraSpace = "      ";
			else
				extraSpace = "     ";
			hline = extraSpace + i;
			for (int j = 0; j < width; j++) {
				if (i == boat.gridY && j == boat.gridX)
					hline += "B";
				else if (i == treasure.gridY && j == treasure.gridX)
					// Render treasure if it has been found by sonar
					if (found == true) {
						hline += "T";
					} else {
						hline += ".";
					}
				else if (map[i][j].inPath)
					hline += "*";
				else if (map[i][j].walkable)
					hline += ".";
				else
					hline += "+";
			}
			result += hline + i + "\n";
		}
		hline = "       ";
		for (int i = 0; i < width; i++)
			hline += (i % 10);
		result += hline + "\n";
		return result;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getPercent() {
		return percent;
	}
	
	public Node getBoat() {
		return boat;
	}
	
	private ArrayList<Node> getNeighbours(Node node) {
		// each node has at most 8 neighbours
		// Lab3 may be useful here as well
		ArrayList<Node> output = new ArrayList<Node>();
		
		boolean North,West,South,East;
		
		int boatHeight,boatWidth;
		
		//Assign the appropriate axis for height and width
		boatHeight = node.gridY;
		boatWidth = node.gridX;
		
		//Assign appropriate boolean values to each direction
		North = (boatHeight - 1) >= 0;
		South = (boatHeight + 1) < height;
		East = (boatWidth  + 1) < width;
		West = (boatWidth - 1) >=0;
		
		
		if(North){
			output.add(map[boatHeight - 1][boatWidth]);
		}
		
		if(North && East){
			output.add(map[boatHeight -1 ][boatHeight + 1]);
		}
		
		if(East){
			output.add(map[boatHeight][boatWidth + 1]);
		}
		
		if(South && East){
			output.add(map[boatHeight + 1][ boatWidth + 1]);
		}
		
		if(South){
			output.add(map[boatHeight+1][boatWidth]);
		}
		
		if(South && West){
			output.add(map[boatHeight + 1][boatWidth - 1]);
		}
		
		if(West){
			output.add(map[boatHeight][boatWidth - 1]);
		}
		
		if(North && West){
			output.add(map[boatHeight - 1][boatWidth - 1]);
		}
		
		return output;
	
	}

	public int getDistance(Node nodeA, Node nodeB) {
		// helper method. Provided for your convenience.
		int dstX = Math.abs(nodeA.gridX - nodeB.gridX);
		int dstY = Math.abs(nodeA.gridY - nodeB.gridY);
		if (dstX > dstY)
			return 14 * dstY + 10 * (dstX - dstY);
		return 14 * dstX + 10 * (dstY - dstX);
	}

	public void findPath(Node startNode, Node targetNode)
			throws HeapFullException, HeapEmptyException {
		Heap<Node> openSet = new Heap<>(width * height); // this where we make use of our heaps
		// The rest of your implementation goes here.
		// This method implements A-star path search algorithm.
		// The pseudocode is provided in the appropriate web links.
		// Make sure to use the helper method getNeighbours
		// add a starting node to the openset
		startNode.hCost = getDistance(startNode, targetNode);
		startNode.gCost = 0;
		openSet.add(startNode);
		
		//make a new set to hold close nodes
		Set <Node> closeNodes = new HashSet<Node>();
		
		//check if the openSet is empty, else start repeating the procedure
		while(!openSet.isEmpty()){
			Node current_node = openSet.removeFirst();
			
			//get all neighbours of current_node
			ArrayList<Node> nearBys = getNeighbours(current_node);
			
			//loop through all neighbours and see where we can walk
			for(Node goTo: nearBys){
				
				if(goTo.walkable == false){
					continue; // ignore everything
				}
				
				//If the find the target go to it and break the loop
				if(goTo == targetNode){
					goTo.parent = current_node;
					return;
				}
				
				int new_cost = current_node.gCost + getDistance(goTo, current_node);
				
				//check if current_node is in the closeNodes, if so update it if possible
				if(closeNodes.contains(goTo)){
					if(new_cost < goTo.gCost){
						
						//throw away the current node, and update it
						closeNodes.remove(goTo);
						
						//new node
						goTo.gCost = new_cost;
						goTo.hCost = getDistance(goTo, targetNode);
						goTo.parent = current_node;
						
						//add newly created node to the set
						openSet.add(goTo);
					}
					else{
						//keep going but do nothing
					continue;
					}
				
				}
				
				// check if in openset, if so update it
				if (openSet.contains(goTo)){
					if (new_cost < goTo.gCost){
						goTo.gCost = new_cost;
						goTo.parent = current_node;
						openSet.updateItem(goTo);
						}
					}
				
				
				// check if goTo not in the close and openset, if not add it
				if (openSet.contains(goTo) == false){
					goTo.parent = current_node;
					goTo.gCost = getDistance(current_node,goTo) + current_node.gCost;
					goTo.hCost = getDistance(goTo,targetNode);
					openSet.add(goTo);
				}
			}
			//add node to closeNodes set
			closeNodes.add(current_node);
		}
	}

	public ArrayList<Node> retracePath(Node startNode, Node endNode) {
		Node currentNode = endNode;
	    ArrayList<Node> path = new ArrayList<Node>();
		while (currentNode != startNode && currentNode != null) {
			currentNode.inPath = true;
			path.add(currentNode);
			currentNode = currentNode.parent;
		}
		return path;
	}


	public void move(String direction) {
		// Direction may be: N,S,W,E,NE,NW,SE,SW
		// move the boat 1 cell in the required direction
		
		boolean North,West,South,East;
		
		int boatHeight,boatWidth;
		
		//Assign the appropriate axis for height and width
		boatHeight = boat.gridY;
		boatWidth = boat.gridX;
		
		//Assign appropriate boolean values to each direction
		North = (boatHeight - 1) >= 0;
		South = (boatHeight + 1) < height;
		East = (boatWidth  + 1) < width;
		West = (boatWidth - 1) >= 0;
		
		// Move north
		if (direction.equals("N") && North) {
			Node changePosition = map[boatHeight - 1][boatWidth];
			if(changePosition.walkable == false){
				return;
			}
			map[boatHeight - 1][boatWidth] = boat;
			boat.gridY  = boatHeight - 1;
			map[boatHeight][boatWidth] = changePosition;
			changePosition.gridY = boatHeight;
		} 
		
		// Move south
		if (direction.equals("S") && South) {
			Node changePosition = map[boatHeight + 1][boatWidth];
			if(changePosition.walkable == false){
				return;
			}
			map[boatHeight + 1][boatWidth] = boat;
			boat.gridY  = boatHeight + 1;
			map[boatHeight][boatWidth] = changePosition;
			changePosition.gridY = boatHeight;
		} 
		
		// Move west
		if (direction.equals("W") && West) {
			Node changePosition = map[boatHeight][boatWidth - 1];
			if(changePosition.walkable == false){
				return;
			}
			map[boatHeight][boatWidth - 1] = boat;
			boat.gridX  = boatWidth - 1;
			map[boatHeight][boatWidth] = changePosition;
			changePosition.gridX = boatWidth;
		} 
		
		// Move east
		if (direction.equals("E") && East) {
			Node changePosition = map[boatHeight][boatWidth + 1];
			if(changePosition.walkable == false){
				return;
			}
			map[boatHeight][boatWidth  + 1] = boat;
			boat.gridX  = boatWidth + 1;
			map[boatHeight][boatWidth] = changePosition;
			changePosition.gridX = boatWidth;
		}
		
		// Move northeast
		if (direction.equals("NE") && North && East) {
			Node changePosition = map[boatHeight - 1][boatWidth + 1];
			if(changePosition.walkable == false){
				return;
			}
			map[boatHeight - 1][boatWidth + 1] = boat;
			boat.gridY  = boatHeight - 1;
			boat.gridX = boatWidth + 1;
			map[boatHeight][boatWidth] = changePosition;
			changePosition.gridY = boatHeight;
			changePosition.gridX = boatWidth;
		}
		
		// Move northwest
		if (direction.equals("NW") && North && West) {
			
			Node changePosition = map[boatHeight - 1][boatWidth - 1];
			if(changePosition.walkable == false){
				return;
			}
			map[boatHeight - 1][boatWidth - 1] = boat;
			boat.gridY  = boatHeight - 1;
			boat.gridX = boatWidth - 1;
			map[boatHeight][boatWidth] = changePosition;
			changePosition.gridY = boatHeight;
			changePosition.gridX = boatWidth;
		}
		
		// Move southeast
		if (direction.equals("SE") && South && East) {
			Node changePosition = map[boatHeight + 1][boatWidth + 1];
			if(changePosition.walkable == false){
				return;
			}
			map[boatHeight - 1][boatWidth] = boat;
			boat.gridY  = boatHeight - 1;
			map[boatHeight][boatWidth] = changePosition;
			changePosition.gridY = boatHeight;
		}
		
		// Move southwest
		if (direction.equals("SW") && South && West) {
			Node changePosition = map[boatHeight + 1][boatWidth - 1];
			if(changePosition.walkable == false){
				return;
			}
			map[boatHeight + 1][boatWidth - 1] = boat;
			boat.gridY  = boatHeight + 1;
			boat.gridX = boatWidth - 1;
			map[boatHeight][boatWidth] = changePosition;	
			changePosition.gridY = boatHeight;
			changePosition.gridX = boatWidth;
		} 
	}
	
	public Node getTreasure(int range) {
		// range is the range of the sonar
		// if the distance of the treasure from the boat is less or equal that the sonar range,
		// return the treasure node. Otherwise return null.
		
		//Compute the distance from the boat and treasure
		int distance = getDistance(boat, treasure);
		
		//check if the distance is smaller than or equal the range
		if(distance <= range){
			found = true; // Allow for the treasure to be rendered
			return treasure;
		}
		return null;
	
	}

}
