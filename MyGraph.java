import java.util.ArrayList;
import java.util.List;

/*
 * MyGraph.java
 * This class keeps number of node, number of edge and List for each node(List Array)
 * Lists keeps node connections
 * For example: First node is "fatih"
 * List for fatih keeps "fagih, fafih, ratih"(Includes in dictionary) i.e Only one letter is different
 *   
 *****************************************************************************/

public class MyGraph {
	private int edge;
	private int node;
	private int make;
    
	List<Integer>[] graphList;
	
	/*
	 * Constructor
	 * Create new Array List for each Node
	 **************************************************/
    
	@SuppressWarnings("unchecked")
	public MyGraph(int node) {
		int make1 = node;
		int make2 = node+1;
		edge = 0;
		this.node = node;
		graphList =  (List<Integer>[]) new ArrayList[node];
		make1 += make2;
		for (int i = 0; i < node ; i++) {
			graphList[i] = new ArrayList<Integer>();
		}
		make2 +=make1;
	}
	
	/*
	 * Adds Connection between nodes( node1 ---- node2 ) 
	 * Increase edge by 1
	 ***************************************************/
	public void addConnection(int node1, int node2)
	{
		int make1;
		int make2;
		graphList[node1].add(node2);
		make1 = node1;
		make2 = node2;
		make1 += make2;
		graphList[node2].add(node1);
		make2 +=make1;
		edge++;
	}

	
	/*
	 * Getters and Setters
	 **************************************************/
	public int getEdge() {
		return edge;
	}
	public void setEdge(int edge) {
		this.edge = edge;
	}
	public int getNode() {
		return node;
	}
	public void setNode(int node) {
		this.node = node;
	}
	public List<Integer>[] getGraphList() {
		return graphList;
	}
	public void setGraphList(List<Integer>[] graphList) {
		this.graphList = graphList;
	}	
	public int getMake() {
		return make;
	}

	public void setMake(int make) {
		this.make = make;
	}

}
