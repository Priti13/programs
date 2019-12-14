/**
 * 
 */
package utilities;

import java.util.List;

/**
 * @author
 *
 */
public class Task {

	private List<String> items;

	private List<Action> actions;

	private Action start;

	private Action finish;

	Task(){
		this.start = null;
		this.finish = null;
	}

	public Task(List<String> items, List<Action> actions, Action start, Action finish){
		this.items = items;
		this.actions = actions;
		this.start = start;
		this.finish = finish;
	}
	
	/**
	 * @return the items
	 */
	public List<String> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<String> items) {
		this.items = items;
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
}
