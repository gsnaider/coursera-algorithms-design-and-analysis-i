package week3.mincut;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import week3.graph.Edge;
import week3.graph.Graph;
import week3.graph.Node;
import week3.io.KargerFileReader;

public class MinCutter {

	private static final int NUMBER_OF_NODES = 200;
	
	private Graph graph;
	
	private Integer minCut;
	
	public MinCutter() {
		this.graph = new Graph(NUMBER_OF_NODES);
		minCut = null;
	}

	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	private int findMinCut() {
		Double itersDouble = Math.pow(NUMBER_OF_NODES, 2) * Math.log(NUMBER_OF_NODES);
		int iters = itersDouble.intValue();
		for (int i = 0; i < iters; i++) {
			int minCutCandidate = kargerMinCut();
			System.out.println(i);
			if(minCut == null || minCutCandidate < minCut){
				System.out.println("Min Cut Candidate: " + minCutCandidate);
				minCut = minCutCandidate;
			}
		}
		return minCut;
	}
	
	//Very inefficient!!!
	private int kargerMinCut() {

		List<Set<Node>> nodeSets = createNodeSets();
		List<Edge> edges = new ArrayList<Edge>();
		edges.addAll(graph.getEdges());
		int nodesRemaining = graph.getNodes().size();
		
		Random random = new Random();
		
		while (nodesRemaining > 2){
			
			int edgeIndex = random.nextInt(edges.size());
			Edge edgeToRemove = edges.get(edgeIndex);
			Set<Node> nodesSet = contractNodes(nodeSets, edgeToRemove.getNode1(), edgeToRemove.getNode2());
			removeSelfLoops(nodesSet, edges);
			nodesRemaining--;
		}
		
		return countEdgesBetweenSets(nodeSets);
		
	}
	
	private int efficientKargerMinCut() {
		List<Node> nodes = graph.getNodes();
		List<Edge> edges = graph.getEdges();
		
		Graph contractedGraph = new Graph(nodes.size());
		contractedGraph.setEdges(edges);
		contractedGraph.setNodes(nodes);
		
		int nodesRemaining = nodes.size();
		Random random = new Random();
		while (nodesRemaining > 2){
			int edgeIndex = random.nextInt(edges.size());
			Edge edgeToRemove = edges.get(edgeIndex);
			
		}
		
	}

	private void removeSelfLoops(Set<Node> nodesSet, List<Edge> edges) {
		for (Node node : nodesSet){
			for (Edge edge : node.getEdges()){
				if (nodesSet.contains(edge.getNode1()) &&  nodesSet.contains(edge.getNode2())){
					edges.remove(edge);
				}
			}
		}
	}

	private int countEdgesBetweenSets(List<Set<Node>> nodeSets) {
		int count = 0;
		for (Edge edge : graph.getEdges()){
			if (!inSameSet(nodeSets, edge.getNode1(), edge.getNode2())){
				count++;
			}
		}
		return count;
	}

	private boolean inSameSet(List<Set<Node>> nodeSets, Node node1, Node node2) {
		Set<Node> node1Set = findNodeSet(nodeSets, node1);
		return node1Set.contains(node2);
	}

	private Set<Node> contractNodes(List<Set<Node>> nodeSets, Node node1, Node node2) {
		Set<Node> node1Set = findNodeSet(nodeSets, node1);
		Set<Node> node2Set = findNodeSet(nodeSets, node2);
		if (node1Set.size() < node2Set.size()){
			node2Set.addAll(node1Set);
			node1Set.clear();
			return node2Set;
		} else {
			node1Set.addAll(node2Set);
			node2Set.clear();
			return node1Set;
		}
	}

	private Set<Node> findNodeSet(List<Set<Node>> nodeSets, Node node1) {
		for (Set<Node> nodeSet : nodeSets){
			if(nodeSet.contains(node1)){
				return nodeSet;
			}
		}
		return null;
	}

	private List<Set<Node>> createNodeSets() {
		List<Set<Node>> nodeSets = new ArrayList<Set<Node>>();
		
		for (Node node : graph.getNodes()){
			Set<Node> nodeSet = new HashSet<Node>();
			nodeSet.add(node);
			nodeSets.add(nodeSet);
		}
		return nodeSets;
	}

	public static void main(String[] args){
	
		KargerFileReader fileReader = new KargerFileReader();
		MinCutter minCutter = new MinCutter();
		fileReader.readFile(minCutter.getGraph());
		int minCut = minCutter.findMinCut();
		System.out.println("MinCut: " + minCut);
		
	}




}
