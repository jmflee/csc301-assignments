package a1;

import java.util.Random;


public class BlobApp
{
  public static boolean[][] genGrid(int rows, int cols, int percentage) {
	  boolean[][] grid = new boolean [rows][cols];

	  // to generate random numbers
	  int randInt;
	  Random rand = new Random();

	  for (int i = 0; i < rows; i++)
	    for (int j = 0; j < cols; j++)
	    {
	      randInt = rand.nextInt(100);  // random number 0 .. 99
	      if (randInt < percentage)
	        grid[i][j] = true;
	      else
	        grid[i][j] = false;
	    }
	  return grid;
  }
  
  public static void main(String[] args)
  {

    final int GRIDR = 5;   // number of grid rows
    final int GRIDC = 10;   // number of grid columns
    int percentage = 30;       

    boolean[][] g = genGrid(GRIDR, GRIDC, percentage);
    
    
    System.out.println();

    // create grid
    Grid grid = new Grid(GRIDR, GRIDC, g);
    
    // display grid and blob count
    System.out.println(grid);
    System.out.println("\nThere are " + grid.blobCount() + " blobs.\n");
  }
}