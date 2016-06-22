
import java.util.*;

public class Graph {
	// TODO : complete the graph data structure
	private static final Comparator<Integer> ASC = Comparator.naturalOrder(); // ascending
																				// comparator
	private static final Comparator<Integer> DESC = ASC.reversed(); // descending
																	// comparator
	private int numOfVertices;
	private int numOfEdges;
	private int[] vertices;
	private int[][] adjacencyMatrix;
	private Stack<Integer> cycle;
	
	

	// create an empty graph with V vertices
	public Graph(int v) {
		// TODO : complete this method
		this.numOfVertices = v;
		vertices = new int[v];
		for (int i = 0; i < v; i++) {
			vertices[i] = i;
		}
		adjacencyMatrix = new int[v][v];
	}

	public int degree(int v) {
		int deg = ((LinkedList<Integer>) getNeighbors(v)).size();
		return deg;
	}
	public int inDegree(int v) {
		int deg = ((LinkedList<Integer>) getIncomingVertices(v)).size();
		return deg;
	}
	
	public int outDegree(int v) {
		int deg = ((LinkedList<Integer>) getOutgoingVertices(v)).size();
		return deg;
	}
	

	public int findIndex(int v) {
		int index = -1;
		for (int i = 0; i < vertices.length; i++) {
			if (vertices[i] == v)
				index = i;
		}
		return index;

	}

	// return the number of vertices
	public int getNumVertices() {
		// TODO : complete this method
		return numOfVertices;
	}

	// return the number of edges
	public int getNumEdges() {
		// TODO : complete this method
		return numOfEdges;
	}

	// add a new vertex to the graph
	public void addVertex() {
		// TODO : complete this method
		int v = vertices[numOfVertices - 1] + 1;
		int[][] newMatrix = new int[numOfVertices + 1][numOfVertices + 1];
		int[] newVertices = new int[numOfVertices + 1];

		for (int i = 0; i < numOfVertices; i++) {
			for (int j = 0; j < numOfVertices; j++) {
				newMatrix[i][j] = adjacencyMatrix[i][j];
			}
		}

		for (int i = 0; i < numOfVertices; i++) {
			newVertices[i] = vertices[i];

		}
		newVertices[numOfVertices] = v;

		numOfVertices++;
		vertices = newVertices;
		adjacencyMatrix = newMatrix;
	}

	// add a new edge between vertices v and w
	public void addEdge(int v, int w) {
		// TODO : complete this method
		int indexOfV = findIndex(v);
		int indexOfW = findIndex(w);
		// TODO Auto-generated method stub
		if ((indexOfV < 0) || (indexOfW < 0))
			return;
		if (adjacencyMatrix[indexOfV][indexOfW] == 0) {
			adjacencyMatrix[indexOfV][indexOfW] = 1;
			numOfEdges++;
		} else {
			System.out.println("The edge is already in the graph!");
		}
	}

	// remove the edge between vertices v and w
	public void removeEdge(int v, int w) {
		// TODO : complete this method
		int indexOfV = findIndex(v);
		int indexOfW = findIndex(w);
		if ((indexOfV < 0) || (indexOfW < 0))
			return;
		if (adjacencyMatrix[indexOfV][indexOfW] == 1) {
			adjacencyMatrix[indexOfV][indexOfW] = 0;
			numOfEdges--;
			System.out.println("The Edge (" + v +"," + w + ") is removed from the graph" );
		} else {
			System.out.println("The edge (" + v + "," + w + ") does not exist in the list!");
		}

	}
	
	public void removeVertex(int v) {
		// TODO Auto-generated method stub
		boolean isHere = false;
		for (int i = 0; i < numOfVertices; i++) {
			if(vertices[i]==v){
				isHere = true;
			}
		}
		if(!isHere){
			System.out.println("The vertex " + v + " does not exist in the graph");
			return;
		}
		int index = findIndex(v);
		int[][] newMatrix = new int[numOfVertices-1][numOfVertices-1];
		int[] newVertices = new int[numOfVertices-1];
		numOfVertices--;
		for (int i = 0; i < index; i++) {
			newVertices[i]=vertices[i];
		}
		
		for (int i = index; i < numOfVertices; i++) {
			newVertices[i]=vertices[i+1];
		}
		
		for (int i = 0; i < index; i++){ 
			for (int j = 0; j < index; j++){ 
				newMatrix[i][j] = adjacencyMatrix[i][j];
			}		
		}
		
		for (int i = index; i < numOfVertices; i++){ 
			for (int j = 0; j < index; j++){ 
				newMatrix[i][j] = adjacencyMatrix[i+1][j];
			}		
		}
		
		for (int i = index; i < numOfVertices; i++){ 
			for (int j = index; j < numOfVertices; j++){ 
				newMatrix[i][j] = adjacencyMatrix[i+1][j+1];
			}		
		}
		
		for (int i = 0; i < index; i++){ 
			for (int j = index; j < numOfVertices; j++){ 
				newMatrix[i][j] = adjacencyMatrix[i][j+1];
			}		
		}
		vertices = newVertices;
		adjacencyMatrix = newMatrix;
		

	}

	// return the list of vertices which are adjacent to vertex v
	public Iterable<Integer> getNeighbors(int v) {
		// TODO : complete this method
		int index = findIndex(v);
		LinkedList<Integer> listOfNeighbors = new LinkedList<Integer>();
		for (int i = 0; i < numOfVertices; i++) {
			if (adjacencyMatrix[index][i] == 1) {
				listOfNeighbors.add(vertices[i]);
			}
			if (adjacencyMatrix[i][index] == 1) {
				if(!listOfNeighbors.contains(vertices[i]))
				listOfNeighbors.add(vertices[i]);
			}

		}
		return listOfNeighbors;
	}

	public Iterable<Integer> getOutgoingVertices(int v) {
		// TODO : complete this method
		int index = findIndex(v);
		LinkedList<Integer> listOfNeighbors = new LinkedList<Integer>();
		for (int i = 0; i < numOfVertices; i++) {
			if (adjacencyMatrix[index][i] == 1) {
				listOfNeighbors.add(vertices[i]);
			}
		}
		return listOfNeighbors;
	}
	
	public Iterable<Integer> getIncomingVertices(int v) {
		// TODO : complete this method
		int index = findIndex(v);
		LinkedList<Integer> listOfNeighbors = new LinkedList<Integer>();
		for (int i = 0; i < numOfVertices; i++) {
			if (adjacencyMatrix[i][index] == 1) {
				listOfNeighbors.add(vertices[i]);
			}

		}
		return listOfNeighbors;
	}
	
	// return the list of the degrees of the vertices in this graph in sorted
	// order (from largest to smallest)
	public List<Integer> degreeSequence() {
		// TODO : complete this method
		LinkedList<Integer> listOfDegrees = new LinkedList<Integer>();
		for (int i = 0; i < numOfVertices; i++) {
			listOfDegrees.add(degree(vertices[i]));
		}
		Collections.sort(listOfDegrees, Collections.reverseOrder());
		return listOfDegrees;
	}

	// return the list of the vertices which are at distance 2 away from the
	// vertex v
	public List<Integer> verticesWithinDistance2(int v) {
		// TODO : complete this method
		LinkedList<Integer> listOfNeighbours = (LinkedList<Integer>) getNeighbors(v);
		LinkedList<Integer> listOfVertices = new LinkedList<Integer>();
		listOfVertices.add(v);
		LinkedList<Integer> distanceVertices;

		for (int i = 0; i < listOfNeighbours.size(); i++) {
			int neighbour = listOfNeighbours.get(i);
			distanceVertices = (LinkedList<Integer>) getNeighbors(neighbour);
			for (int j = 0; j < distanceVertices.size(); j++) {
				int distance = distanceVertices.get(j);
				if ((!listOfVertices.contains(distance)) 
						&&(!listOfNeighbours.contains(distance)) 
						&&(distance != v))
					listOfVertices.add(distance);
			}

		}	
		listOfVertices.addAll(listOfNeighbours);
		return listOfVertices;
	}

	// return the string representation of the graph in adjacency matrix format
	public String showAsMatrix() {
		// TODO : complete this method
		String s = "";

		for (int i = 0; i < adjacencyMatrix.length; i++) {
			for (int j = 0; j < adjacencyMatrix[i].length; j++) {
				s += adjacencyMatrix[i][j] + "-";
			}
			s += "\n";
		}
		
		return s;
	}

	// return the string representation of the graph in adjacency list format
	public String showAsAdjacencyList() {
		// TODO : complete this method
		LinkedList<Integer> neighbors;
		String s = "";
		for (int i = 0; i < vertices.length; i++) {
			neighbors = (LinkedList<Integer>) getNeighbors(vertices[i]);
			for (int j = 0; j < neighbors.size(); j++) {
				s += neighbors.get(j) + "-";
			}
			s += "-\n";
		}
		return s;
	}
	
	
	// remove a single edge that is part of a directed cycle, and return the edge that is removed
	public Edge removeEdgeInCycle(){
		boolean[] visited = new boolean[numOfVertices];
		boolean[] forest = new boolean[numOfVertices];
		for (int i = 0; i < numOfVertices; i++) {
			Edge e = searchForCycleDFS(vertices[i],visited,forest);
			if(e!=null)
				return e;
		}
		return null;
		
	}
	
	// remove one edge per cycle until there are no more cycles, and return the list of edges in the order in which they are removed
	public List<Edge> removeAllCycles(){
		LinkedList<Edge> removed = new LinkedList<Edge>();
		boolean[] visited = new boolean[numOfVertices];
		boolean[] forest = new boolean[numOfVertices];
		for (int i = 0; i < numOfVertices; i++) {
			removed.addAll(searchForAllCycleDFS(vertices[i],visited,forest,removed));			
		}
		HashSet<Edge> set = new HashSet<>();
		set.addAll(removed);
		removed.clear();
		removed.addAll(set);
		return removed;
		
	}
	
	public Edge searchForCycleDFS(int v, boolean[] visited, boolean[] recent){
		visited[v] = true;
		recent[v] = true;
		Edge e = null;
		for (Integer w : getOutgoingVertices(v)) {
			System.out.println(v + "->" + w);

			if(!visited[w]){	
				return searchForCycleDFS(w,visited,recent);
					
			}else if(recent[w]){
				e = new Edge(v,w);
				removeEdge(v, w);
				return e;
			}

		}
		recent[v]=false;	
		return e;		
	}
	
	public LinkedList<Edge> searchForAllCycleDFS(int v, boolean[] visited, boolean[] recent, LinkedList<Edge> removed){
		visited[v] = true;
		recent[v] = true;
		Edge e = null;
		for (Integer w : getOutgoingVertices(v)) {
			System.out.println(v + "->" + w);

			if(!visited[w]){	
				return searchForAllCycleDFS(w,visited,recent,removed);
					
			}else if(recent[w]){
				e = new Edge(v,w);
				removeEdge(v, w);
				removed.add(e);
			}

		}
		recent[v]=false;	
		return removed;		
	}
	
	
	// topological sort of vertices if there are no cycles, otherwise return null
	public List<Integer> topologicalSort(){
		LinkedList<Integer> vertices = new LinkedList<Integer>();		
		boolean visited[] = new boolean[numOfVertices];
		for (int i = 0; i < numOfVertices; i++) {
			visited[i] = false;
		}		
		for (int i = 0; i < numOfVertices; i++) {
			if(!visited[i]){
				topoSortHelper(i, visited, vertices);
			}	
		}			
		return vertices;	
	}
	
	private void topoSortHelper(int v, boolean[] visited, LinkedList<Integer> listOfVertices) {
		// TODO Auto-generated method stub
		visited[v] = true;
		int i = 0;
		Iterator<Integer> iter = getOutgoingVertices(v).iterator();
		while (iter.hasNext()) {
			i = iter.next();
			if(visited[i] == false)
				topoSortHelper(i, visited, listOfVertices);
		}
		listOfVertices.addFirst(v);
	}

	// check to see whether the given list of vertices are a topological sort
	public boolean isTopological(List<Integer> sort){
		return sort.equals(topologicalSort());		
	}
	
	// generalized form of the verticesWithinDistance2, return vertices within distance d from vertex v
	public List<Integer> verticesWithinDistance(int v, int d){
		if(d==0)
			return new LinkedList<Integer>();
		
		LinkedList<Integer> distantVertices = new LinkedList<Integer>();
		LinkedList<Integer> verticesOnTheNextLevel = new LinkedList<Integer>();

		distantVertices.add(v);
		for (Integer w : getOutgoingVertices(v)) {
			verticesOnTheNextLevel.addAll(verticesWithinDistance(w, d-1));
			for (int i = 0; i < verticesOnTheNextLevel.size(); i++) {
				int u = verticesOnTheNextLevel.get(i);
				if(!distantVertices.contains(u))
					distantVertices.add(u);	
			}
		}
		
		
		return distantVertices;

	}
	
	
	// topological sorted implemented as the textbook suggested
	
	public List<Integer> topologicalSort2(){
		LinkedList<Integer> sorted = new LinkedList<Integer>();
		Stack<Integer> stack = new Stack<Integer>();
		HashMap<Integer,Integer> inDegreeMap = new HashMap<Integer,Integer>();
		
		for (int i = 0; i < vertices.length; i++) {
			int v = vertices[i];
			inDegreeMap.put(v, inDegree(v));
			if(inDegreeMap.get(v)==0)
				stack.push(v);
		}
		
		while(!stack.isEmpty()){
			int v = stack.pop();
			sorted.addLast(v);
			
			for (Integer w : getOutgoingVertices(v)) {
				int nextVertex = w;
				inDegreeMap.put(nextVertex, inDegreeMap.get(nextVertex)-1);
				if(inDegreeMap.get(nextVertex)==0)
					stack.push(nextVertex);
			}
		}
		
		return sorted;
		
	}

}