/**
 * Edge class
 * @author r42xe_000
 *
 */
public class Edge implements Comparable<Edge>{
	private Node begin;
	private Node end;
	private int weight;
	/**
	 * @return the begin
	 */
	public Node getBegin() {
		return begin;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Edge [begin=" + begin + ", end=" + end + ", weight=" + weight + "]";
	}
	/**
	 * @param begin the begin to set
	 */
	public void setBegin(Node begin) {
		this.begin = begin;
	}
	/**
	 * @return the end
	 */
	public Node getEnd() {
		return end;
	}
	/**
	 * @param end the end to set
	 */
	public void setEnd(Node end) {
		this.end = end;
	}
	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public Edge(Node begin, Node end, int weight) {
		this.begin = begin;
		this.end = end;
		this.weight = weight;
	}
	@Override
	public int compareTo(Edge other) {
		return (this.weight - other.weight) + this.begin.getName().compareTo(other.getBegin().getName());
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((begin == null) ? 0 : begin.hashCode());
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + weight;
		return result;
	}
	/* (non-Javadoc)
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
		Edge other = (Edge) obj;
		if (begin == null) {
			if (other.begin != null) {
				return false;
			}
		} else if (!begin.equals(other.begin)) {
			return false;
		}
		if (end == null) {
			if (other.end != null) {
				return false;
			}
		} else if (!end.equals(other.end)) {
			return false;
		}
		if (weight != other.weight) {
			return false;
		}
		return true;
	}
	
}
