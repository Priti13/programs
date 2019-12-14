/**
 * 
 */
package utilities;

/**
 * @author
 *
 */
public class Edge {

	public Edge(Action source, Action destination) {
		this.source = source;
		this.destination = destination;
	}

	/**
	 * Action depicting the source of the edge
	 */
	private Action source;

	/**
	 * Action depicting the destination of the edge
	 */
	private Action destination;

	/**
	 * @return the source
	 */
	public Action getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(Action source) {
		this.source = source;
	}

	/**
	 * @return the destination
	 */
	public Action getDestination() {
		return destination;
	}

	/**
	 * @param destination the destination to set
	 */
	public void setDestination(Action destination) {
		this.destination = destination;
	}
	
	/**
	 * Prints the source and destination of the edge
	 */
	public void printEdge(){
		System.out.println("Source of the edge is "+getSource());
		System.out.println("Destination of the edge is "+getDestination());
	}
}
