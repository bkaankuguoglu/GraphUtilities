
import java.util.*;

public class Digraph {
	// TODO : complete the graph data structure
	private int V;
	private int E;
	private LinkedList<Integer> adj[];

	// create an empty graph with V vertices
	public Digraph(int V) {
		this.V = V;
		this.E = 0;
		adj = (LinkedList<Integer>[]) (new LinkedList[V]);
		for (int i = 0; i < V; i++) {
			adj[i] = new LinkedList<Integer>();
		}
	}

	// return the number of vertices
	public int getNumVertices() {
		return V;
	}

	// return the number of edges
	public int getNumEdges() {
		return E;
	}

	// add a new edge between vertices v and w
	public void addEdge(int v, int w) {
		if (adj[v].contains(w))
			return;
		adj[v].add(w);
		E++;
	}

	private Edge removeEdge(Edge e) {
		// TODO Auto-generated method stub
		int v = e.v;
		int w = e.w;
		if ((v < 0) || (w < 0))
			return null;
		if (adj[v].contains(w)) {
			for (int i = 0; i < adj[v].size(); i++) {
				if (w == adj[v].get(i))
					adj[v].remove(i);
			}
			E--;
			// System.out.println("The edge (" + v + "," + w + ") is removed!");
			return e;
		} else {
			System.out.println("The edge does not exist in the list!");
			return e;
		}
	}

	// return the list of vertices which are adjacent to vertex v
	public Iterable<Integer> getNeighbors(int v) {
		return adj[v];
	}
	


	// remove a single edge that is part of a directed cycle, and return the
	// edge that is removed
	public Edge removeEdgeInCycle() {
		// TODO : complete this method
		Edge result = null;
		LinkedList<Edge> path = new LinkedList<Edge>();
		boolean[] recent = new boolean[V];
		boolean[] visited = new boolean[V];
		// it compiles when it s 0
		for (int i = 0; i < V; i++) {
			if (getNeighbors(i).iterator().hasNext()) {
				result = searchForCycleDFS(i, visited, recent, path);
				if (result != null) {
					return removeEdge(result);
				}
			}
		}
		return null;
	}

	public Edge searchForCycleDFS(int v, boolean[] visited, boolean[] recent, LinkedList<Edge> cycle) {
		LinkedList<Edge> thisTurn = new LinkedList<Edge>();

		boolean found = false;
		visited[v] = true;
		recent[v] = true;
		Edge e = null;

		for (Integer w : getNeighbors(v)) {
			// System.out.println(v + "->" + w);
			e = new Edge(v, w);
			cycle.add(e);
			thisTurn.add(e);

			if (!visited[w]) {
				found = false;
				return searchForCycleDFS(w, visited, recent, cycle);
			} else if (recent[w]) {
				if (cycle.getFirst().v != cycle.getLast().w) {
					found = false;
				} else
					found = true;

			}

		}

		recent[v] = false;
		// System.out.println("this: " + thisTurn);
//		System.out.println("cycle " + cycle);
//		System.out.println("f " + cycle.getFirst());
//		System.out.println("l " + cycle.getLast());

		if (found) {
			Collections.sort(cycle);
			e = cycle.getFirst();
			return e;
			// System.out.println("e: " + e);
		} else {
			cycle.clear();
			return null;
		}
	}

	// remove one edge per cycle until there are no more cycles, and return the
	// list of edges in the order in which they are removed
	public List<Edge> removeAllCycles() {
		// TODO : complete this method
		LinkedList<Edge> result = new LinkedList<Edge>();
		Edge e = null;
		int c = 0;
		while(c==0){
			System.out.println(e);
			System.out.println(result);
			e = removeEdgeInCycle();
			if(e!=null)
				result.add(e);
			else
				c=1;
		}
		return result;
	}

	// topological sort of vertices if there are no cycles, otherwise return
	// null
	// pseudocode given in the textbook
	public List<Integer> topologicalSort() {
		// TODO : complete this method
		LinkedList<Integer> sorted = new LinkedList<Integer>();
		LinkedList<Integer> stack = new LinkedList<Integer>();
		HashMap<Integer, Integer> inDegreeMap = new HashMap<Integer, Integer>();

		for (int v = 0; v < adj.length; v++) {
			inDegreeMap.put(v, inDegree(v));
			if (inDegreeMap.get(v) == 0)
				stack.add(v);
		}

		while (!stack.isEmpty()) {
			int v = stack.removeLast();
			sorted.addLast(v);

			for (Integer w : getNeighbors(v)) {
				int nextVertex = w;
				inDegreeMap.put(nextVertex, inDegreeMap.get(nextVertex) - 1);
				if (inDegreeMap.get(nextVertex) == 0)
					stack.add(nextVertex);
			}
		}
		if (sorted.size() != V)
			return null;

		return sorted;
	}

	public Iterable<Integer> getIncomingVertices(int v) {
		// TODO : complete this method
		LinkedList<Integer> listOfNeighbors = new LinkedList<Integer>();
		for (int i = 0; i < adj.length; i++) {
			if (adj[i].contains(v)) {
				listOfNeighbors.add(i);
			}

		}
		return listOfNeighbors;
	}

	private Integer inDegree(int v) {
		// TODO Auto-generated method stub
		int deg = ((LinkedList<Integer>) getIncomingVertices(v)).size();
		return deg;
	}

	// check to see whether the given list of vertices are a topological sort
	public boolean isTopological(List<Integer> sort) {
		// TODO : complete this method
		LinkedList<Integer> list = new LinkedList<Integer>();
		for (Integer v : sort) {
			list.add(v);
			for (Integer w : getNeighbors(v)) {
				if (list.contains(w))
					return false;
			}
		}

		return true;

	}

	// generalized form of the verticesWithinDistance2
	// return vertices within distance d from vertex v

	public List<Integer> verticesWithinDistance(int v, int d) {
		// TODO : complete this method
		List<Integer> known = new LinkedList<Integer>();
		List<Integer> forest = new LinkedList<Integer>();
		return verticesWithinDistanceHelper(v, d, known, forest);
	}

	public List<Integer> verticesWithinDistanceHelper(int v, int d, List<Integer> known, List<Integer> forest) {
		LinkedList<Integer> level = new LinkedList<Integer>();
		known.add(v);
		level.addLast(v);
		int counter = 0;
		while (!level.isEmpty()) {
			if (counter == d)
				break;
			counter++;
			LinkedList<Integer> nextLevel = new LinkedList<Integer>();
			for (Integer w : level) {
				for (Integer neighbor : getNeighbors(w)) {
					if (!known.contains(neighbor)) {
						known.add(neighbor);
						forest.add(neighbor);
						nextLevel.addLast(neighbor);
					}

				}

			}
			level = nextLevel;
		}

		return known;

	}
}