package utilities;

import java.util.List;

public class State {

	private String stateName;
	private Action owner;
	private boolean logicOperation;
	private List<String> parameters;

	/**
	 * Constructor to initialize a State object
	 * @param stateName
	 * @param owner
	 * @param logicOperation
	 */
	public State(String stateName, Action owner, boolean logicOperation) {
		this.stateName = stateName;
		this.owner = owner;
		this.logicOperation = logicOperation;
	}

	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * @param stateName the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	/**
	 * @return the owner
	 */
	public Action getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(Action owner) {
		this.owner = owner;
	}

	/**
	 * @return the logicOperation
	 */
	public boolean isLogicOperation() {
		return logicOperation;
	}

	/**
	 * @param logicOperation the logicOperation to set
	 */
	public void setLogicOperation(boolean logicOperation) {
		this.logicOperation = logicOperation;
	}

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
}
