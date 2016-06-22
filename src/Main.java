import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Graph graph1 = new Graph(5);
			
	/*
	 * Test cases for adjacency matrix implementation of graph.
	 * 		
	 */
			
			System.out.println("***************************************");
			System.out.println("* Part 1: Adjacency Matrix Test Cases *");
			System.out.println("***************************************\n");

			System.out.println("Construct a graph with 5 vertices.");			
			System.out.println(graph1.showAsMatrix());		
			graph1.addEdge(2, 1);
			graph1.addEdge(1, 0);
			graph1.addEdge(0, 2);
			graph1.addEdge(2, 4);
			graph1.addEdge(4, 3);
			graph1.addEdge(3, 2);
			graph1.addEdge(4, 0);



			
			System.out.println("The graph is filled with the edges: "
					+ "\n(2,1), (2,4), (3,1), (4,3), (4,1), (0,1), and (0,3)\n");
			System.out.println(graph1.showAsMatrix());		

			
			
			
			System.out.println("\nRemove edge in cycle:");
			System.out.println(graph1.removeEdgeInCycle());
			System.out.println(graph1.showAsMatrix());		

			
			System.out.println("\nRemove all cycles:");
			System.out.println(graph1.removeAllCycles());
			System.out.println(graph1.showAsMatrix());		

			System.out.println("\nTopological Sort:");
			System.out.println(graph1.topologicalSort2());
			
			System.out.println("\nVertices within the distance 3 to the vertex 2:");
			System.out.println(graph1.verticesWithinDistance(2,3));

		
			
			System.out.println("\nRemoval of the edges (4,5) and (2,1)");
			graph1.removeEdge(4, 5);
			graph1.removeEdge(2, 1);
			System.out.println(graph1.showAsMatrix());		

			graph1.removeVertex(2);
			System.out.println("\nAfter removal of 2");
			System.out.println(graph1.showAsMatrix());		
			
			System.out.println("\nNeighbours of the vertex 1:");
			System.out.println(graph1.getNeighbors(1)); //takes index
						
			System.out.println("\nDegree Sequence in descending order:");
			System.out.println(graph1.degreeSequence());
			
			System.out.println("\nVertices within the distance 2 to the vertex 1:");
			System.out.println(graph1.verticesWithinDistance2(1));
			
			
			System.out.println("\nAdd Vertex:");
			graph1.addVertex();
			System.out.println(graph1.showAsMatrix());		
			
			System.out.println("\nAdddition of the edges (1,5), (3,5), and (0,5):");
			graph1.addEdge(1, 5);
			graph1.addEdge(3, 5);
			graph1.addEdge(0, 5);		
			System.out.println(graph1.showAsMatrix());		
			
			
			System.out.println("\nShow the adjacency matrix graph as adjacency list graph");
			System.out.println(graph1.showAsAdjacencyList());		


			System.out.println("\n************************************");
			System.out.println("*             The End.             *");
			System.out.println("************************************\n");
			
	}

}
