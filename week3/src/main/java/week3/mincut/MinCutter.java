package week3.mincut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
			if(minCut == null || minCutCandidate < minCut){
				System.out.println("Min Cut Candidate: " + minCutCandidate);
				minCut = minCutCandidate;
			}
		}
		return minCut;
	}
	
	private int kargerMinCut() {
		List<Set<Node>> nodeSets = createNodeSets();
		List<Edge> edges = new ArrayList<Edge>();
		edges.addAll(graph.getEdges());
		int nodesRemaining = graph.getNodes().size();
		Collections.shuffle(edges);
		Iterator<Edge> edgeIterator = edges.iterator();
		while (nodesRemaining > 2 && edgeIterator.hasNext()){
			Edge edgeToRemove = edgeIterator.next();
			if (!inSameSet(nodeSets, edgeToRemove.getNode1(), edgeToRemove.getNode2())){
				contractNodes(nodeSets, edgeToRemove.getNode1(), edgeToRemove.getNode2());
				nodesRemaining--;
			}
		}
		
		return countEdgesBetweenSets(nodeSets);
		
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

	private void contractNodes(List<Set<Node>> nodeSets, Node node1, Node node2) {
		Set<Node> node1Set = findNodeSet(nodeSets, node1);
		Set<Node> node2Set = findNodeSet(nodeSets, node2);
		node1Set.addAll(node2Set);
		nodeSets.remove(node2Set);
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
