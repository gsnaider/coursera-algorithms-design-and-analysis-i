package week3.graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {

	private List<Node> nodes;
	
	private List<Edge> edges;

	
	public Graph(int numberOfNodes){
		nodes = new ArrayList<Node>(numberOfNodes);
		
		edges = new ArrayList<Edge>();
		
		for(int i = 0; i < numberOfNodes; i++){
			nodes.add(new Node(i));
		}
		
	}
	
	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
	
}
