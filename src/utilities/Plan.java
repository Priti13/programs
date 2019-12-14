/**
 * 
 */
package utilities;

import java.util.LinkedList;
import java.util.List;

/**
 * @author
 *
 */
public class Plan implements PlanInterface {

	private List<Action> actions;
	private Action start = null;
	private Action finish = null;
	private LinkedList<Edge> links = new LinkedList<Edge>();
	private LinkedList<State> preConditions=new LinkedList<State>();

	public Plan(Action start, Action finish){
		this.start = start;
		this.finish = finish;
	}
	/**
	 * @return the actions
	 */
	public List<Action> getActions() {
		return actions;
	}

	/**
	 * @param actions the actions to set
	 */
	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	/**
	 * @return the start
	 */
	public Action getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(Action start) {
		this.start = start;
	}

	/**
	 * @return the finish
	 */
	public Action getFinish() {
		return finish;
	}

	/**
	 * @param finish the finish to set
	 */
	public void setFinish(Action finish) {
		this.finish = finish;
	}

	/**
	 * @return the links
	 */
	public LinkedList<Edge> getLinks() {
		return links;
	}

	/**
	 * @param links the links to set
	 */
	public void setLinks(LinkedList<Edge> links) {
		this.links = links;
	}

	/**
	 * @return the preConditions
	 */
	public LinkedList<State> getPreConditions() {
		return preConditions;
	}

	/**
	 * @param preConditions the preConditions to set
	 */
	public void setPreConditions(LinkedList<State> preConditions) {
		this.preConditions = preConditions;
	}

	/**
	 *
	 */
	@Override
	public void printPlan() {

	}

	/**
	 *
	 */
	@Override
	public void printNodes(Action node) {
		if(node.getActionName().equals("FINISH"))
			System.out.println(node);
	}

	/**
	 *
	 */
	@Override
	public LinkedList<Edge> getEdgeByDestination(Action destination) {
		LinkedList<Edge> list = new LinkedList<Edge>();
	    for(Edge edge : this.links){
	        if(destination == edge.getDestination()){
	            list.add(edge);
	        }
	    }
	    
	    return list;
	}

	/**
	 *
	 */
	@Override
	public List<Edge> getEdgeBySource(Action destination) {

		return null;
	}
}
