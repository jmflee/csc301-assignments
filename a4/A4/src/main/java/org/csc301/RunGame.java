package org.csc301;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class RunGame extends Canvas implements Runnable, KeyListener {

	// Starts the game with default settings
	TreasureHunt th = new TreasureHunt();
	private int tileWidth; 
	private int tileHeight; 
	Tile tileArray[][];
	// Start the game with custom settings
	// TreasureHunt th = new TreasureHunt(HEIGHT, WIDTH, LANDPERCENT, SONARS, RANGE);
	
	
	private static final String TITLE = "Redbeard's Pirate Booty";
	private static final int HEIGHT = 1080;
	private static final int WIDTH = 1920;
	private static final int SIZE = 32;
	private static boolean running = false;
	private boolean pop = false, win = false;
	
	private static final Dimension gameDim = new Dimension(WIDTH, HEIGHT);

	
	BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	JFrame frame;
	JLabel lblSonarsLeft;
	
	public int xOffset = 0;
	public int yOffset = 0;
	public static Point mouseP = new Point();
	
	Thread thread;
	
	//Controls
	public static boolean left, right, up, down, click, stop;
	public static boolean boatLeft, boatRight, boatUp, boatDown, boatSonar;
	
	/**
	 * Create the applet.
	 * @wbp.parser.entryPoint
	 */
	public RunGame(int height, int width, int landPercent, int sonars,
			int range) throws FileNotFoundException, HeapFullException, HeapEmptyException {
		this.th = new TreasureHunt(height, width, landPercent, sonars, range);
		this.tileWidth = th.getWidth(); 
		this.tileHeight = th.getHeight(); 
		this.tileArray = new Tile[tileWidth][tileHeight];
		
		setMinimumSize(gameDim);
		setMaximumSize(gameDim);
		setPreferredSize(gameDim);
		frame = new JFrame(TITLE);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		
		frame.getContentPane().add(this, BorderLayout.CENTER);
		
		lblSonarsLeft = new JLabel("Sonars Left: " + th.getSonars());
		lblSonarsLeft.setFont(new Font("Dialog", Font.BOLD, 20));
		frame.getContentPane().add(lblSonarsLeft, BorderLayout.NORTH);
		frame.pack();
		
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		this.addKeyListener(this);
		
		init();

		requestFocus();
	}

	// Initializes the grid
	private void init() {
		// Convert the map into a gui
		String map[] = th.getMap().replaceAll("\\d", "").replaceAll(" ", "").split("\n");
		for (int x = 0; x < tileWidth; x++) {
			for (int y = 0; y < tileHeight; y++) {
				// Render the path
				if (map[y+2].charAt(x) == '*') {
					tileArray[x][y] = new Tile (x * SIZE, y * SIZE, this);
				}
				// Render the islands
				if (map[y+2].charAt(x) == '+') {
					tileArray[x][y] = new Tile (x * SIZE, y * SIZE, this);
				}
				// Render water
				if (map[y+2].charAt(x) == '.') {
					tileArray[x][y] = new Tile (x * SIZE, y * SIZE, this);
				}
				// Hide the treasure
				if (map[y+2].charAt(x) == 'T') {
					tileArray[x][y] = new Tile (x * SIZE, y * SIZE, this);
				}
				// Render the boats
				if (map[y+2].charAt(x) == 'B') {
					tileArray[x][y] = new Tile (x * SIZE, y * SIZE, this);
				}
			}
		}
	}
	
	public void tick() {
		for (int x = 0; x < tileWidth; x++) {
			for (int y = 0; y < tileHeight; y++) {
				tileArray[x][y].tick(this);
			}
		}
		moveMap();
	}
	
	// Moves the map with wasd controls
	private void moveMap() {
		if (left)
			xOffset++;
		if (right)
			xOffset--;
		if (up)
			yOffset++;
		if (down)
			yOffset--;
		if (click)
			System.out.println(this.getMousePosition());
		if (stop)
			stop();
	}
	
	public void keyPressed(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		
		if (keyCode == KeyEvent.VK_LEFT)
			RunGame.left = true;
		if (keyCode == KeyEvent.VK_RIGHT)
			RunGame.right = true;
		if (keyCode == KeyEvent.VK_UP)
			RunGame.up = true;
		if (keyCode == KeyEvent.VK_DOWN)
			RunGame.down = true;
		if (keyCode == KeyEvent.VK_ESCAPE)
			RunGame.stop = true;
		
		if (keyCode == KeyEvent.VK_A)
			RunGame.boatLeft = true;
		if (keyCode == KeyEvent.VK_D)
			RunGame.boatRight = true;
		if (keyCode == KeyEvent.VK_W)
			RunGame.boatUp = true;
		if (keyCode == KeyEvent.VK_S)
			RunGame.boatDown = true;
		if (keyCode == KeyEvent.VK_SPACE)
			RunGame.boatSonar = true;
	}

	
	public void keyReleased(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		
		if (keyCode == KeyEvent.VK_LEFT)
			RunGame.left = false;
		if (keyCode == KeyEvent.VK_RIGHT)
			RunGame.right = false;
		if (keyCode == KeyEvent.VK_UP)
			RunGame.up = false;
		if (keyCode == KeyEvent.VK_DOWN)
			RunGame.down = false;
		if (keyCode == KeyEvent.VK_ESCAPE)
			RunGame.stop = false;
		
		try {
		if (keyCode == KeyEvent.VK_A) {
			RunGame.boatLeft = false;
			th.processCommand("GO W");
		}
		if (keyCode == KeyEvent.VK_D) {
			RunGame.boatRight = false;
			th.processCommand("GO E");
		}
		if (keyCode == KeyEvent.VK_W) {
			RunGame.boatUp = false;
			th.processCommand("GO N");
		}
		if (keyCode == KeyEvent.VK_S) {
			RunGame.boatDown = false;
			th.processCommand("GO S");
		}
		if (keyCode == KeyEvent.VK_SPACE && th.getState().equals("STARTED")) {
			RunGame.boatSonar = false;
			th.processCommand("SONAR");
			lblSonarsLeft.setText("Sonars Left: " + th.getSonars());
			if (th.getState() == "OVER") {
				render();
			}
			// Game exit dialog
			int input = 0;
			String [] buttons = { "Play Again", "Exit Game" };
			if (th.getState().equals("OVER") && (pop == false)) {
				try{
					// Set a delay for the winner dialog to allow everything else to render
					Thread.sleep(1000); 
				}catch (InterruptedException ie){
					System.out.println(ie.getMessage());
				}

				// We got a winner
				if (win == true) {
					input = JOptionPane.showOptionDialog(this, "Congratulations, your crew has found Redbeard's pirate booty in a distance of: " + th.pathLength(), "Winner", JOptionPane.INFORMATION_MESSAGE, 0, null, buttons, buttons[1]);
				}
				// We got a loser
				if (th.getSonars() == 0) { 
					input = JOptionPane.showOptionDialog(this, "Bad news, your crew has ran out of sonars and cannot progress any further", "Game Over", JOptionPane.INFORMATION_MESSAGE, 0, null, buttons, buttons[1]);
				}
				pop = true;
				if (input == 0) {
					Menu menu = new Menu();
					menu.setVisible(true);
					frame.dispose();
				} else {
					System.exit(0);
				}
			}
		}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void keyTyped(KeyEvent e) {
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
		// Convert the map into a gui
		String map[] = th.getMap().replaceAll("\\d", "").replaceAll(" ", "").split("\n");
		
		for (int x = 0; x < tileWidth; x++) {
			for (int y = 0; y < tileHeight; y++) {
				// Render the islands
				if (map[y+2].charAt(x) == '+') {
					tileArray[x][y].renderIslands(g);
				}
				// Render water
				if (map[y+2].charAt(x) == '.') {
					tileArray[x][y].renderWater(g);
				}
				// Render the islands
				if (map[y+2].charAt(x) == '*') {
					tileArray[x][y].renderPath(g);;
				}
				// Hide the treasure
				if (map[y+2].charAt(x) == 'T') {
					tileArray[x][y].renderTreasure(g);
					win = true;
				}
				// Render the boats
				if (map[y+2].charAt(x) == 'B') {
					tileArray[x][y].renderBoat(g);
				}
			}
		}
		
		g.dispose();
		bs.show();
	}
	
	// Start a new game thread
	public synchronized void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	// Stops game
	public synchronized void stop() {
		running = false;
		System.exit(0);
	}
	
	public void run() {
		
		while (running) {
			tick();
			render();
			
			// Sets the sensitivity of the wasd controls
			try {
				Thread.sleep(2);
			} catch(Exception e) {
				
			}
		}
	}
	
	public static void main(String args[]) {
		try {
			RunGame g = new RunGame(15, 60, 20, 3, 200);
			g.start();
		} catch (FileNotFoundException | HeapFullException | HeapEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
