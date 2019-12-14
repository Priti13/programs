/**
 * 
 */
package utilities;

import java.util.List;

/**
 * @author
 *
 */
public class Action {

	private List<String> parameters;
	private List<State> preconditions;
	private List<State> effects;
	private String actionName;
	/**
	 * @return the parameters
	 */
	public List<String> getParameters() {
		return parameters;
	}
	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(List<String> parameters) {
		this.parameters = parameters;
	}
	/**
	 * @return the preconditions
	 */
	public List<State> getPreconditions() {
		return preconditions;
	}
	/**
	 * @param preconditions the preconditions to set
	 */
	public void setPreconditions(List<State> preconditions) {
		this.preconditions = preconditions;
	}
	/**
	 * @return the effects
	 */
	public List<State> getEffects() {
		return effects;
	}
	/**
	 * @param effects the effects to set
	 */
	public void setEffects(List<State> effects) {
		this.effects = effects;
	}
	/**
	 * @return the actionName
	 */
	public String getActionName() {
		return actionName;
	}
	/**
	 * @param actionName the actionName to set
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
}
