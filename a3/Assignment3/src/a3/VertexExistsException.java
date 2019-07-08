// Developed by Joseph Lee

package a3;

public class VertexExistsException extends Exception{
	
	private String message = "";
	public VertexExistsException () {
		
	}
	public VertexExistsException (String log) {
		this.message = log;
	}
}
