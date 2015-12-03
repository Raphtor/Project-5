/**
 * Edge class.
 * 
 * @author r42xe_000
 *
 */
public class Edge implements Comparable<Edge> {
    /**
     * Begin node.
     */
    private Node begin;
    /**
     * End node.
     */
    private Node end;
    /**
     * Weight.
     */
    private int weight;

    /**
     * Constructor.
     * 
     * @param b
     *            Beginning node.
     * @param e
     *            End node.
     * @param w
     *            Weight
     */
    public Edge(Node b, Node e, int w) {
        this.begin = b;
        this.end = e;
        this.weight = w;
    }

    /**
     * @return the begin
     */
    public Node getBegin() {
        return this.begin;
    }

    /**
     * @param b
     *            the begin to set
     */
    public void setBegin(Node b) {
        this.begin = b;
    }

    /**
     * @return the end
     */
    public Node getEnd() {
        return this.end;
    }

    /**
     * @param e
     *            the end to set
     */
    public void setEnd(Node e) {
        this.end = e;
    }

    /**
     * @return the weight
     */
    public int getWeight() {
        return this.weight;
    }

    /**
     * @param w
     *            the weight to set
     */
    public void setWeight(int w) {
        this.weight = w;
    }

    @Override
    public int compareTo(Edge other) {
        return (this.weight - other.weight) 
                + this.begin.getName().compareTo(other.getBegin().getName());
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return this.end.hashCode() + this.begin.hashCode() + this.weight;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Edge temp = (Edge) obj;
        return temp.begin.equals(temp.begin) 
                && temp.end.equals(temp.end) && this.weight == temp.weight;
    }

}
