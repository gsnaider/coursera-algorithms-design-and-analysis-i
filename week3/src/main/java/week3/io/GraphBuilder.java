package week3.io;

import java.util.List;

import week3.graph.Edge;
import week3.graph.Graph;
import week3.graph.Node;

public class GraphBuilder {

	private Graph graph;

	public GraphBuilder(Graph graph) {
		this.graph = graph;
	}

	public void addNode(int nodeNumber, List<Integer> adjacentNodeNumbers) {
		Node node = graph.getNodes().get(nodeNumber - 1);
		
		for (Integer adjacentNodeNumber : adjacentNodeNumbers){
			
			Node adjacentNode = graph.getNodes().get(adjacentNodeNumber - 1);
			
			Edge edge = new Edge(node, adjacentNode);

			if(!node.getEdges().contains(edge)){
				node.getEdges().add(edge);
				adjacentNode.getEdges().add(edge);
				graph.getEdges().add(edge);
			}
		}
		
	}

}
