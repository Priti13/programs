/**
 * 
 */
package utilities;

import java.util.List;

/**
 * @author 
 *
 */
public interface PlanInterface {

	public void printPlan();
	
	public void printNodes(Action node);
	
	public List<Edge> getEdgeByDestination(Action source);
	
	public List<Edge> getEdgeBySource(Action destination);
	
	
}
