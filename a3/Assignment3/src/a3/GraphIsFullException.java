// Developed by Joseph Lee

package a3;

public class GraphIsFullException extends Exception{
	
	private String message = "";
	public GraphIsFullException () {
		
	}
	public GraphIsFullException (String log) {
		this.message = log;
	}
}