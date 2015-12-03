/**
 * Node class.
 * 
 * @author r42xe_000
 *
 */
public class Node implements Comparable<Node> {
    /**
     * Name.
     */
    private String name;
    


    /**
     * Constructor.
     * @param n
     * Name of node
     */
    public Node(String n) {
        this.name = n;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Node other = (Node) obj;
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.equals(other.name)) {
            return false;
        }
        return true;
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param n
     *            the name to set
     */
    public void setName(String n) {
        this.name = n;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();

    }

    @Override
    public int compareTo(Node o) {
        return this.name.compareTo(o.name);
    }

}
