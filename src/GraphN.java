import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
/**
 * Graph class.
 * @author Raphael Norman-Tenazas rtenaza1
 *
 */
public class GraphN {
    /**
     * List of nodes.
     */
    private ArrayList<Node> nodes;
    /**
     * Edges, ordered by weight.
     */
    private TreeSet<Edge> edges;
    /**
     * Unvisited nodes (used in Djikstra's).
     */
    private TreeSet<Node> unvisited;
    /**
     * Distance from current node (used in Djikstra's).
     */
    private TreeMap<Node, Double> distance;
    /**
     * Constructor.
     */
    public GraphN() {
        this.nodes = new ArrayList<Node>();
        this.edges = new TreeSet<Edge>();
        this.unvisited = new TreeSet<Node>();
        this.distance = new TreeMap<Node, Double>();
    }
    /**
     * Adds a node.
     * @param n
     * Node to add.
     */
    public void addNode(Node n) {
        if (!this.nodes.contains(n)) {
            this.nodes.add(n);
        }

    }
    /**
     * Removes a node (and edges associated with it).
     * @param n
     * Node to remove.
     */
    public void removeNode(Node n) {
        this.nodes.remove(n);
        for (Edge e : this.edges) {
            if (e.getBegin().equals(n) || e.getEnd().equals(n)) {
                this.edges.remove(e);
            }
        }
    }
    /**
     * Adds an edge.
     * @param begin
     * Beginning node
     * @param end
     * End node
     * @param weight
     * Weight of edge
     */
    public void addEdge(Node begin, Node end, int weight) {
        this.edges.add(new Edge(begin, end, weight));
    }
    /**
     * Add an edge.
     * @param e
     * Edge to add.
     */
    public void addEdge(Edge e) {
        this.edges.add(e);
    }
    /**
     * Gets unvisited neighbors (used in Djikstra's).
     * @param n
     * Node to find neighbors for.
     * @return
     * The set of neighbors.
     */
    public TreeSet<Node> getUnvNeighbors(Node n) {
        TreeSet<Node> neighbors = new TreeSet<Node>();
        for (Edge e : this.edges) {

            if (e.getBegin().equals(n) && this.unvisited.contains(e.getEnd())) {
                neighbors.add(e.getEnd());

            }

        }
        return neighbors;
    }
    /**
     * Gets neighbors (not necessarily visited).
     * @param n
     * Node to get neighbors from.
     * @return
     * Set of neighbors.
     */
    public TreeSet<Node> getNeighbors(Node n) {
        TreeSet<Node> neighbors = new TreeSet<Node>();
        for (Edge e : this.edges) {
            // System.out.println(e + " " + n);
            if (e.getBegin().equals(n)) {
                neighbors.add(e.getEnd());

            }

        }
        return neighbors;
    }
    /**
     * Djikstra's algorithim.
     * @param begin
     * Node to get shortest paths from.
     * @return
     * A map containing the nodes previous to that node to get to it.
     */
    public Map<Node, Node> djikstra(Node begin) {
        HashMap<Node, Node> prevTree = new HashMap<Node, Node>();
        this.unvisited = new TreeSet<Node>();
        this.distance = new TreeMap<Node, Double>();
        for (Node n : this.nodes) {
            this.distance.put(n, Double.POSITIVE_INFINITY);
            prevTree.put(n, null);
            this.unvisited.add(n);
        }
        this.distance.put(begin, 0.0);
        Node curr = begin;
        // System.out.println(edges);
        while (!this.unvisited.isEmpty() && curr != null) {
            // System.err.println("Curr = " + curr);
            // System.err.println(unvisited);
            curr = this.minDist(begin);
            this.unvisited.remove(curr);
            TreeSet<Node> neighbors = this.getUnvNeighbors(curr);
            for (Node n : neighbors) {
                double tempDist = this.distance.get(curr)
                        + this.length(curr, n);
                // System.out.println(n + " " + curr + " " + tempDist + " " +
                // this.distance.get(n));
                if (tempDist <= this.distance.get(n)) {
                    this.distance.put(n, tempDist);
                    // System.out.println("!" + this.distance.get(n) + " " +
                    // this.unvisited + n);

                    prevTree.put(n, curr);
                }
            }
        }
        return prevTree;
    }
    /**
     * Gets the node with the minimum distance from the current.
     * @param begin
     * Current node
     * @return
     * Node with minimum distance.
     */
    private Node minDist(Node begin) {
        double min = Double.POSITIVE_INFINITY;
        Node ret = null;
        for (Node n : this.unvisited) {
            // System.out.println(n + " " + this.distance.get(n));
            if (this.distance.get(n) < min) {
                ret = n;
            }
        }
        return ret;
    }

    /**
     * @return the nodes
     */
    public ArrayList<Node> getNodes() {
        return this.nodes;
    }

    /**
     * @return the edges
     */
    public TreeSet<Edge> getEdges() {
        return this.edges;
    }
    /**
     * Gets the weight of the edge between nodes.
     * @param begin
     * Beginning node
     * @param end
     * End node
     * @return
     * Weight
     */
    private int length(Node begin, Node end) {
        for (Edge e : this.edges) {
            if (e.getBegin().equals(begin) && e.getEnd().equals(end)) {
                return e.getWeight();
            }
        }
        return 0;
    }
    /**
     * Breadth first search for the shortest path. 
     * Breaks ties with distance because the edges are organized by weight.
     * @param begin
     * Beginning node
     * @return
     * Returns a previous map
     */
    public Map<Node, Node> breadthFirst(Node begin) {
        HashMap<Node, Node> prevTree = new HashMap<Node, Node>();
        this.unvisited = new TreeSet<Node>();
        this.distance = new TreeMap<Node, Double>();
        for (Node n : this.nodes) {
            this.distance.put(n, Double.POSITIVE_INFINITY);
            prevTree.put(n, null);
        }
        this.distance.put(begin, 0.0);
        this.unvisited.add(begin);
        while (!this.unvisited.isEmpty()) {
            Node curr = this.unvisited.first();
            this.unvisited.remove(curr);
            TreeSet<Node> neighbors = this.getNeighbors(curr);
            for (Node n : neighbors) {
                if (this.distance.get(n) == Double.POSITIVE_INFINITY) {
                    this.distance.put(n, this.distance.get(curr) + 1);
                    prevTree.put(n, curr);
                    this.unvisited.add(n);
                }
            }
        }
        return prevTree;
    }
    /**
     * Gets an edge given the nodes.
     * @param b
     * Beginning node
     * @param e
     * End node
     * @return
     * The edge
     */
    public Edge getEdge(Node b, Node e) {
        for (Edge ed : this.edges) {
            if ((ed.getBegin() == b || ed.getBegin() == e) 
                    && (ed.getEnd() == b || ed.getEnd() == e)) {
                return ed;
            }
        }
        return null;
    }

}
