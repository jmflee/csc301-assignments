package a1;

public class Grid
{
protected int rows;         // number of grid rows
protected int cols;         // number of grid columns
 
protected boolean [][] grid;     // the grid containing blobs
// You may define helper variables as needed
protected boolean [][] marked;   // the grid containing marked blobs

public Grid(int rows, int cols, boolean[][] grid)

{
 this.rows = rows;
 this.cols = cols;
 this.grid = grid;
 this.marked = new boolean [rows][cols];
 
}

public String toString()
{
 String gridString = "";
 for (int i = 0; i < rows; i++)
 {
   for (int j = 0; j < cols; j++)
   {
     if (grid[i][j])
       gridString = gridString + "X";
     else
       gridString = gridString + ".";
    }
   gridString = gridString + "\n";   // end of row
 }  
 return gridString;
}

public int blobCount()
// returns the number of blobs in this grid
{
 int count = 0;
 // Your code goes here
 for (int x = 0; x < rows; x++)
 {
   for (int y = 0; y < cols; y++)
   {
	 // An x exists in the position and was not previously marked
     if (grid[x][y] && !marked[x][y]) {
    	count++; // Found a blob
    	markGrid(x, y); // Mark the grid for x's around the location
     }
   }
 }  
 return count;
}

// You may define helper methods as needed
// Marks blobs in a grid
void markGrid(int x, int y) 
{
  // Return if x or y is past the grid bounds or value is marked or a block isn't present
  if (x < 0 || y < 0 || x >= rows || y >= cols || !grid[x][y] || marked[x][y]) 
  {
	return;  
  }

  marked[x][y] = true;

  markGrid(x-1, y); // Check for x's in the left
  markGrid(x+1, y); // Check for x's in the right
  markGrid(x+1, y+1); // Check for x's in the bottom right
  markGrid(x+1, y-1); // Check for x's in the top right
  markGrid(x-1, y+1); // Check for x's in the bottom left
  markGrid(x-1, y-1); // Check for x's in the top left
  markGrid(x, y-1); // Check for x's above
  markGrid(x, y+1); // Check for x's below
}
}

