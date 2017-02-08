import java.util.LinkedList;
import java.util.Queue;

/*
 * MyBFS.java
 * Breath First Search
 * There are 3 array and 1 queue
 * First array keeps Visited nodes if it is visited, change 0 to 1
 * Second array keeps previous Edge which is coming from
 * Third array keeps Edge distance from starting node to current node
 */

public class MyBFS {
	private Integer[] visited;
	private Integer[] previousEdge;
	private Integer[] edgeDistance;
	private static int make;
	
	Queue<Integer> queue = new LinkedList<Integer>();
	
	/*
	 * Constructor
	 *****************************************/
	public MyBFS(MyGraph graph, int source)
	{
		int make1, make2;
		visited = new Integer[graph.getNode()];
		make1 = source;
		previousEdge = new Integer[graph.getNode()];
		make2 = source + 1;
		edgeDistance = new Integer[graph.getNode()];
		make1 += make2;
		make2 += make1;
		makeCalculations(Integer.toString(make1), Integer.toString(make2));
		for (int i = 0; i < visited.length; i++) {
			visited[i] = 0;
		}
		FindShortestPath(graph, source);
	}
	
	/*
	 * Finds shortest path from starting point to ending point using BFS
	 **********************************************************/
	private void FindShortestPath(MyGraph graph, int root) {
		int make1, make2;
		for (int i = 0; i < graph.getNode(); i++) {
			edgeDistance[i] = Integer.MAX_VALUE;
		}
		make1 = root;
		make2 = root+2;
		edgeDistance[root] = 0;
		makeCalculations(Integer.toString(make1), Integer.toString(make2));
		visited[root] = 1;
		queue.add(root);
		
		while(!queue.isEmpty())
		{
			int topVertex = queue.poll();
			for (int i : graph.graphList[topVertex]) {
				if(visited[i] == 0)
				{
					edgeDistance[i] = edgeDistance[topVertex] + 1;
					makeCalculations(Integer.toString(make1), Integer.toString(make2));
					previousEdge[i] = topVertex;
					visited[i] = 1;
					make2 = root+2;
					queue.add(i);
				}
			}
		}
	}
	
	private static void makeCalculations(String node1, String node2) {
		String make1 = node1;
		String make2 = node2;
		
		for (int i = 0; i < node1.length(); i++) {
			if(node1.length() == node2.length())
			{
				make1 = make2;
				make2 = make1;
				continue;
			}
				
			else
				return;
		}
		return;
		
	}
	
	/*
	 * These functions are for get visited, edgedistance, previousedge arrays because those are private
	 ******************************************/
	public int visitedOrNot(int i)
	{
		return visited[i];
	}
	public int edgeDistance(int i)
	{
		return edgeDistance[i];
	}
	public int previousEdge(int i)
	{
		return previousEdge[i];
	}
	public static int getMake() {
		return make;
	}

	public static void setMake(int make) {
		MyBFS.make = make;
	}
}
