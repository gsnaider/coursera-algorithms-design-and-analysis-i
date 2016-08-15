package week3.graph;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private int id;
	
	private List<Edge> edges;
	
	public Node(int id){
		this.id = id;
		edges = new ArrayList<Edge>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public List<Edge> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if(this.getId() == other.getId()){
			return true;
		}
		return false;
	}
	
}
