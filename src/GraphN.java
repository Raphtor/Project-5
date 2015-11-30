import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class GraphN {
	
	private ArrayList<Node> nodes;
	private TreeSet<Edge> edges;
	private TreeSet<Node> unvisited;
	private TreeMap<Node, Double> distance;
	
	public GraphN() {
		this.nodes = new ArrayList<Node>();
		this.edges = new TreeSet<Edge>();
		this.unvisited = new TreeSet<Node>();
		this.distance = new TreeMap<Node, Double>();
	}
	public void addNode(Node n){
		if (!nodes.contains(n)){
			nodes.add(n);
		}
		

	}
	public void removeNode(Node n){
		nodes.remove(n);
		for(Edge e : edges){
			if (e.getBegin().equals(n)|| e.getEnd().equals(n)){
				edges.remove(e);
			}
		}
	}
	public void addEdge(Node begin, Node end, int weight){
		edges.add(new Edge(begin, end, weight));
	}
	public void addEdge(Edge e){
		edges.add(e);
	}
	public TreeSet<Node> getUnvNeighbors(Node n){
		TreeSet<Node> neighbors = new TreeSet<Node>();
		for (Edge e : this.edges){
			//System.out.println(e + " " + n);
			if(e.getBegin().equals(n) && this.unvisited.contains(e.getEnd())){
				neighbors.add(e.getEnd());
				
			}
			
		}
		return neighbors;
	}
	public TreeSet<Node> getNeighbors(Node n){
		TreeSet<Node> neighbors = new TreeSet<Node>();
		for (Edge e : this.edges){
			//System.out.println(e + " " + n);
			if(e.getBegin().equals(n)){
				neighbors.add(e.getEnd());
				
			}
			
		}
		return neighbors;
	}
	public Map<Node, Node> D(Node begin){
		HashMap<Node, Node> prevTree = new HashMap<Node,Node>();
		this.unvisited = new TreeSet<Node>();
		this.distance = new TreeMap<Node, Double>();
		for(Node n : this.nodes){
			this.distance.put(n, Double.POSITIVE_INFINITY);
			prevTree.put(n, null);
			this.unvisited.add(n);
		}
		this.distance.put(begin, 0.0);
		Node curr = begin;
		//System.out.println(edges);
		while(!this.unvisited.isEmpty() && curr!=null){
			//System.err.println("Curr = " + curr);
			//System.err.println(unvisited);
			curr = minDist(begin);
			this.unvisited.remove(curr);
			TreeSet<Node> neighbors = this.getUnvNeighbors(curr);
			for (Node n : neighbors){
				double tempDist = this.distance.get(curr) + this.length(curr, n);
				//System.out.println(n + " " + curr + " " + tempDist + " " + this.distance.get(n));		
				if (tempDist <= this.distance.get(n)){
					this.distance.put(n,tempDist);
					//System.out.println("!" + this.distance.get(n) + " " + this.unvisited + n);
					
					prevTree.put(n,curr);
				}
			}
		}
		return prevTree;
	}
	private Node minDist(Node begin){
		double min = Double.POSITIVE_INFINITY;
		Node ret = null;
		for (Node n : this.unvisited){
			//System.out.println(n + " " + this.distance.get(n));
			if (this.distance.get(n) < min){
				ret = n;
			}
		}
		return ret;
	}
	
	/**
	 * @return the nodes
	 */
	public ArrayList<Node> getNodes() {
		return nodes;
	}
	/**
	 * @return the edges
	 */
	public TreeSet<Edge> getEdges() {
		return edges;
	}
	private int length(Node begin, Node end){
		for (Edge e : this.edges){
			if(e.getBegin().equals(begin) && e.getEnd().equals(end)){
				return e.getWeight();
			}
		}
		return 0;
	}
	public Map<Node, Node> BFS(Node begin){
		HashMap<Node, Node> prevTree = new HashMap<Node,Node>();
		this.unvisited = new TreeSet<Node>();
		this.distance = new TreeMap<Node, Double>();
		for (Node n : this.nodes){
			this.distance.put(n, Double.POSITIVE_INFINITY);
			prevTree.put(n, null);
		}
		this.distance.put(begin, 0.0);
		this.unvisited.add(begin);
		while (!this.unvisited.isEmpty()){
			Node curr = this.unvisited.first();
			this.unvisited.remove(curr);
			TreeSet<Node> neighbors = this.getNeighbors(curr);
			for (Node n : neighbors){
				if(this.distance.get(n) == Double.POSITIVE_INFINITY){
					this.distance.put(n, this.distance.get(curr) + 1);
					prevTree.put(n, curr);
					this.unvisited.add(n);
				}
			}
		}
		return prevTree;
	}
	
	
}
