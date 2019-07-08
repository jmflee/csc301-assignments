package org.csc301;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

import javax.swing.Action;
import javax.swing.JTextField;

public class Menu extends JFrame {

	private static final String TITLE = "Redbeard's Pirate Booty";
	private JPanel contentPane;
	private JLabel lblTitle, lblSettings, lblMapHeight, lblMapWidth, lblLandPercentage, lblStartingSonars, lblSonarRange;
	private JLabel lblCurrentHeight, lblCurrentWidth, lblCurrentDensity, lblCurrentSonars, lblCurrentRange;
	JButton btnInfo, btnPlay, btnRestore, btnApply;
	private final Action info = new Info();
	private final Action play = new Play();
	private JTextField heightText, widthText, densityText, sonarsText, rangeText;
	private final Action restore = new Restore();
	private final Action apply = new Apply();
	private final Action exit = new Exit();
	private final static int HDEF = 15;
	private final static int WDEF = 60;
	private final static int LPDEF = 20;
	private final static int SDEF = 3;
	private final static int RDEF = 200;
	private int height = HDEF;
	private int width = WDEF;
	private int landDensity = LPDEF;
	private int sonars = SDEF;
	private int range = RDEF;
	private JButton btnExit;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitle = new JLabel("Redbeard's Pirate Booty");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitle.setBounds(20, 11, 327, 25);
		contentPane.add(lblTitle);
		
		btnInfo = new JButton("Game Info and Instructions");
		btnInfo.setAction(info);
		btnInfo.setBounds(365, 15, 234, 23);
		contentPane.add(btnInfo);
		
		btnPlay = new JButton("Play Game!");
		btnPlay.setAction(play);
		btnPlay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnPlay.setBounds(20, 47, 579, 129);
		contentPane.add(btnPlay);
		
		btnApply = new JButton("Apply Changes");
		btnApply.setAction(apply);
		btnApply.setBounds(437, 366, 162, 23);
		contentPane.add(btnApply);
		
		btnRestore = new JButton("Restore to Defaults");
		btnRestore.setAction(restore);
		btnRestore.setBounds(451, 400, 148, 23);
		contentPane.add(btnRestore);
		
		lblSettings = new JLabel("Settings");
		lblSettings.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSettings.setBounds(30, 187, 73, 25);
		contentPane.add(lblSettings);
		
		lblMapHeight = new JLabel("Map Height (Default: 15)");
		lblMapHeight.setFont(new Font("Arial", Font.PLAIN, 14));
		lblMapHeight.setBounds(40, 223, 178, 14);
		contentPane.add(lblMapHeight);
		
		lblMapWidth = new JLabel("Map Width (Default: 60)");
		lblMapWidth.setFont(new Font("Arial", Font.PLAIN, 14));
		lblMapWidth.setBounds(40, 248, 178, 14);
		contentPane.add(lblMapWidth);
		
		lblLandPercentage = new JLabel("Land Density (Default: 20%)");
		lblLandPercentage.setFont(new Font("Arial", Font.PLAIN, 14));
		lblLandPercentage.setBounds(40, 273, 178, 14);
		contentPane.add(lblLandPercentage);
		
		lblStartingSonars = new JLabel("Starting Sonars (Default 3)");
		lblStartingSonars.setFont(new Font("Arial", Font.PLAIN, 14));
		lblStartingSonars.setBounds(40, 298, 178, 14);
		contentPane.add(lblStartingSonars);
		
		lblSonarRange = new JLabel("Sonar Range (Default: 200 )");
		lblSonarRange.setFont(new Font("Arial", Font.PLAIN, 14));
		lblSonarRange.setBounds(40, 323, 211, 14);
		contentPane.add(lblSonarRange);
		
		heightText = new JTextField();
		heightText.setFont(new Font("Arial", Font.PLAIN, 14));
		heightText.setBounds(275, 221, 86, 20);
		contentPane.add(heightText);
		heightText.setColumns(10);
		
		widthText = new JTextField();
		widthText.setFont(new Font("Arial", Font.PLAIN, 14));
		widthText.setColumns(10);
		widthText.setBounds(275, 246, 86, 20);
		contentPane.add(widthText);
		
		densityText = new JTextField();
		densityText.setFont(new Font("Arial", Font.PLAIN, 14));
		densityText.setColumns(10);
		densityText.setBounds(275, 271, 86, 20);
		contentPane.add(densityText);
		
		sonarsText = new JTextField();
		sonarsText.setFont(new Font("Arial", Font.PLAIN, 14));
		sonarsText.setColumns(10);
		sonarsText.setBounds(275, 296, 86, 20);
		contentPane.add(sonarsText);
		
		rangeText = new JTextField();
		rangeText.setFont(new Font("Arial", Font.PLAIN, 14));
		rangeText.setColumns(10);
		rangeText.setBounds(275, 321, 86, 20);
		contentPane.add(rangeText);
		
		lblCurrentHeight = new JLabel("15");
		lblCurrentHeight.setFont(new Font("Arial", Font.PLAIN, 14));
		lblCurrentHeight.setBounds(371, 223, 178, 14);
		contentPane.add(lblCurrentHeight);
		
		lblCurrentWidth = new JLabel("60");
		lblCurrentWidth.setFont(new Font("Arial", Font.PLAIN, 14));
		lblCurrentWidth.setBounds(371, 249, 178, 14);
		contentPane.add(lblCurrentWidth);
		
		lblCurrentDensity = new JLabel("20");
		lblCurrentDensity.setFont(new Font("Arial", Font.PLAIN, 14));
		lblCurrentDensity.setBounds(371, 274, 178, 14);
		contentPane.add(lblCurrentDensity);
		
		lblCurrentSonars = new JLabel("3");
		lblCurrentSonars.setFont(new Font("Arial", Font.PLAIN, 14));
		lblCurrentSonars.setBounds(371, 299, 178, 14);
		contentPane.add(lblCurrentSonars);
		
		lblCurrentRange = new JLabel("200");
		lblCurrentRange.setFont(new Font("Arial", Font.PLAIN, 14));
		lblCurrentRange.setBounds(371, 324, 178, 14);
		contentPane.add(lblCurrentRange);
		
		btnExit = new JButton("Exit");
		btnExit.setAction(exit);
		btnExit.setBounds(20, 400, 148, 23);
		contentPane.add(btnExit);
	}
	private class Info extends AbstractAction {
		public Info() {
			putValue(NAME, "Game Info and Instructions");
			putValue(SHORT_DESCRIPTION, "Shows game info and how to play");
		}
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(new Menu(), "Use the wasd keys to control your overview of the map" +
		"\nClick on a blue unit of land for the initial starting position of the ship" +
		"\nClick on the arrow keys to move your ship around" +
		"\nTo drop a sonar, click on the sonar button +"
		+ "If you run out of sonars before finding the treasure, you lose the game"
					, "Game Information", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	private class Play extends AbstractAction {
		public Play() {
			putValue(NAME, "Play Game");
			putValue(SHORT_DESCRIPTION, "Initializes the gui for the game");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				getText();
				RunGame play = new RunGame(height, width, landDensity, sonars, range);
				play.start();
			} catch (FileNotFoundException | HeapFullException | HeapEmptyException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        setVisible(false); // Hides the current window
		}
	}
	private class Restore extends AbstractAction {
		public Restore() {
			putValue(NAME, "Restore to Defaults");
			putValue(SHORT_DESCRIPTION, "Restores the starting settings for the game");
		}
		public void actionPerformed(ActionEvent e) {
			height = HDEF;
			width = WDEF;
			landDensity = LPDEF;
			sonars = SDEF;
			range = RDEF;
			lblCurrentHeight.setText(Integer.toString(HDEF));
			lblCurrentWidth.setText(Integer.toString(WDEF));
			lblCurrentDensity.setText(Integer.toString(LPDEF));
			lblCurrentSonars.setText(Integer.toString(SDEF));
			lblCurrentRange.setText(Integer.toString(RDEF));

		}
	}
	
	private static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
	private void getText() {
		if (isInteger(heightText.getText())){
			height = Integer.parseInt(heightText.getText());
			lblCurrentHeight.setText(Integer.toString(height));
		}
		if (isInteger(widthText.getText())){
			width = Integer.parseInt(widthText.getText());
			lblCurrentWidth.setText(Integer.toString(width));
		}
		if (isInteger(densityText.getText())){
			landDensity = Integer.parseInt(densityText.getText());
			lblCurrentDensity.setText(Integer.toString(landDensity));
		}
		if (isInteger(sonarsText.getText())){
			sonars = Integer.parseInt(sonarsText.getText());
			lblCurrentSonars.setText(Integer.toString(sonars));
		}
		if (isInteger(rangeText.getText())){
			range = Integer.parseInt(rangeText.getText());
			lblCurrentRange.setText(Integer.toString(range));
		}
	}
	
	private class Apply extends AbstractAction {
		public Apply() {
			putValue(NAME, "Apply Changes");
			putValue(SHORT_DESCRIPTION, "Changes game setting chosen by the user");
		}
		public void actionPerformed(ActionEvent e) {
			getText();
		}
	}
	private class Exit extends AbstractAction {
		public Exit() {
			putValue(NAME, "Exit Game");
			putValue(SHORT_DESCRIPTION, "Exits the game");
		}
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
}
