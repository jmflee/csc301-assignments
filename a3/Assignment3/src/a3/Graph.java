// Developed by Joseph Lee

package a3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Graph<T> implements GraphInterface{
	private int size;
	private Map<Object, HashSet<Object>> vert; // Object and if it is marked
	private Map<Object, Boolean> marked;
	
	private ArrayList<Set<T>> connected;
	private ArrayList<Object> checked; // List of vertices already checked in the graph
	private Set<Object> set;
	
	public Graph (int vertexSize) {
		this.size = vertexSize;
		this.vert = new HashMap<Object, HashSet<Object>>(); // Stores vertices and edges
		this.marked = new HashMap<Object, Boolean>();
	}

	@SuppressWarnings("unchecked")
	public Set<T> DFSVisit(Object self, HashSet<Object> startVertex) {
		//System.out.println(self + " =" + startVertex);
		checked.add(self);
		set.add(self);
		for (Object s : startVertex) {
			if (!checked.contains(s)) {
				set.addAll(vert.get(s));
				DFSVisit(s, vert.get(s));
			}
		}
		
		return ((Set<T>) set);
	}

	// Gets the connected vertices and returns as an array of sets
	public ArrayList<Set<T>> connectedComponents() {
		connected = new ArrayList<Set<T>>();
		checked = new ArrayList<Object>();
		for (Object hs : vert.keySet()) {
			set = new HashSet<Object>(); // Reset the set
			// Skip keys if they have been checked for
			if (!checked.contains(hs)) { 
				connected.add((Set<T>) DFSVisit(hs, vert.get(hs)));
			}
		}
		return connected;
	}
	
	@Override
	// Returns true if this graph is empty; otherwise, returns false.
	public boolean isEmpty() {
		
		return vert.isEmpty();
	}

	@Override
	// Returns true if this graph is full; otherwise, returns false.
	public boolean isFull() {
		
		return (vert.size() == size);
	}

	@Override
	// Preconditions:  Vertex is not already in this graph.
	//                 Vertex is not null.
	// Throws GraphIsFullException if the graph is full
	// Otherwise adds vertex to this graph.
	public void addVertex(Object vertex) throws GraphIsFullException, VertexExistsException {

		if (vert.containsKey(vertex)) {
			throw new VertexExistsException();
		} else if (vert.size()+1 > size) {
			throw new GraphIsFullException();
		} else {
			vert.put(vertex, new HashSet<Object>());
			marked.put(vertex, false);
		}
	}

	@Override
	// Adds an edge with the specified weight from fromVertex to toVertex.
	public void addEdge(Object fromVertex, Object toVertex) {
		// Linkage only occurs if both objects exist in the hashmap
		if (vert.containsKey(fromVertex) && vert.containsKey(toVertex) && (fromVertex != toVertex)) {
			vert.get(fromVertex).add(toVertex);
			vert.get(toVertex).add(fromVertex);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	// Returns a queue of the vertices that are adjacent from vertex.
	public Queue<T> getToVertices(Object vertex) {
		
		Queue<Object> vertices = new LinkedList<Object>(vert.get(vertex));
		return ((Queue<T>) vertices);
	}

	@Override
	// Sets marks for all vertices to false.
	public void clearMarks() {
		
		for (Map.Entry<Object, Boolean> entry : marked.entrySet()) {
			marked.put(entry.getKey(), false);
		}
	}

	@Override
	// Sets mark for vertex to true.
	public void markVertex(Object vertex) {
		
		marked.put(vertex, true);
	}

	@Override
	// Returns true if vertex is marked; otherwise, returns false.
	public boolean isMarked(Object vertex) {
		
		return marked.get(vertex);
	}

}
