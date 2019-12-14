/**

 */
package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import utilities.Action;
import utilities.Edge;
import utilities.Plan;
import utilities.State;
import utilities.Task;

/**
 * @author
 *
 */
public class PopPlanner {

	private Task task;
	private Plan plan;

	public PopPlanner(Task task) {
		this.setTask(task);
		this.plan = new Plan(task.getStart(),task.getFinish());
		initiatePlan();
	}

	private void printState(State state) {
		if (state.isLogicOperation()) {
			System.out.print(state.getStateName());
			for (int i = 0; i < state.getParameters().size(); i++) {
				System.out.print(state.getParameters().get(i));
				if (i != state.getParameters().size() - 1) {
					System.out.print(",");
				}
			}
		}

	}

	private void printAction(Action action, List<String> parameters) {
		System.out.print(action.getActionName());
		for (int i = 0; i < action.getParameters().size(); i++) {
			System.out.print(action.getParameters().get(i));
			if (i != action.getParameters().size() - 1) {
				System.out.print(",");
			}
		}
	}

	public void checkFinishPrecodsWithStartEffects() {
		List<Integer> precondstoRemove = new ArrayList<>();

		for (int i = 0; i < this.plan.getPreConditions().size(); i++) {
			State firstPrecond = this.plan.getPreConditions().get(i);
			// check state logic operator and name
			System.out.println("check open precondition:");
			printState(firstPrecond);
			for (State startEffect : this.plan.getStart().getEffects()) {
				if (startEffect.isLogicOperation() == firstPrecond.isLogicOperation()
						&& startEffect.getStateName() == firstPrecond.getStateName()
						&& startEffect.getParameters().size() == firstPrecond.getParameters().size()) {
					// check parameters
					for (int j = 0; j < startEffect.getParameters().size(); j++) {
						if (startEffect.getParameters().get(j) == firstPrecond.getParameters().get(i)) {
							// same state: remove the open_precondition
							precondstoRemove.add(i);
							System.out.println("found precondition to remove: ");
							printState(firstPrecond);
						}
					}
				}
			}
		}
		System.out.println("remove precondition resolved by start effects");
		for (int i : precondstoRemove) {
			this.plan.getPreConditions().remove((i));
		}
	}

	private void algorithm() {
		System.out.println("Algorithm starts here");
		do {
			
		    if(this.plan.getPreConditions().size() > 0){
		        // get the first open precondition in the vector
		        System.out.println("[POP Algorithm] - get an open precondition");
		        State oprec = this.plan.getPreConditions().peekFirst();
		        printState(oprec);
		        System.out.println("[POP Algorithm] - the action of open precondition is :" );
		        Action  oprec_action = oprec.getOwner();
		        //printAction(oprec_action, oprec_action.getParameters());
		        
		        // find new action for open precondition
		        System.out.println("[POP Algorithm] - Look for an action that solves the precondition");
		        Action new_action = findActionForOpenPrecondition(oprec);           
		        System.out.println("[POP Algorithm] - Found an action");
		        //printAction(new_action, new_action.getParameters());
		        

		        this.plan.getActions().add(new_action);
		        
		        // find a link between oprec and its prev and update it      
		        System.out.println("[POP Algorithm] - Update edges");
		        for(int i = 0; i < this.plan.getLinks().size(); i++){
		            System.out.println("get the link: ");
		            System.out.println(this.plan.getLinks().get(i).getSource().getActionName()+ " --> "+
		            this.plan.getLinks().get(i).getDestination().getActionName());
		            Edge e = this.plan.getLinks().get(i);
		           
		            //printAction(oprec_action, oprec_action.getParameters());
		            if(e.getDestination() == oprec_action){
		                System.out.println("[POP Algorithm] - found an edge with the action of open precondition");
		                e.printEdge();
		                System.out.println("!! change destination for this edge: "); 
		                new_action = e.getDestination();
		                e.printEdge();
		                // create new edge
		                System.out.println("[POP Algorithm] - Create new edge, locating the new action between the old ones");
		                Edge new_edge = new Edge(new_action, oprec_action);                
		                new_edge.printEdge();
		                        
		                this.plan.getLinks().add(new_edge);
		                break;                            
		            }
		        }
		        
		        
				
				  // TODO: check threats
				  System.out.println("[POP Algorithm] - Resolve Threats"); do{ Edge threat =
				  findAThreat(this.plan.getStart()); if(threat == null) break;
				  if(!resolveAThreat(threat)){
				  System.out.println("PLANNING FAILED: cannot resolve a threat"); return; }
				  
				  if(threat == null){ break; } }while(true);
				 
		        List<State> effects = new ArrayList<>();
		        List<State> open_prec = new ArrayList<>();
		        updateOpenPreconditions(  );
		          
		        
		        
		        System.out.println("---STEP PLAN----");
		        this.plan.printPlan();
		        System.out.println("-----------------");
		        
		        System.out.println("---STEP LINKS----");
		        for(Edge edge :this.plan.getLinks()){
		            //printAction(edge.getSource(),edge.getSource().getParameters());
		            System.out.println(" --> " );
		             printAction(edge.getDestination(),edge.getDestination().getParameters());
		           
		        }
		        System.out.println("-----------------");
		        
		        System.out.println("--- STEP OPEN PRECONDITIONS----");
		        for(State precond: this.plan.getPreConditions()){
		            printState(precond);
		            System.out.println("("); 
		            printAction(precond.getOwner(),precond.getOwner().getParameters()); 
		            System.out.println(")");
		            System.out.println(" ");
		        }
		        
		        System.out.println("-----------------");

		    }
		            
		} while (this.plan.getPreConditions().size() != 0);
	}

	private Action findActionForOpenPrecondition(State oprec) {
		return null;
	}

	private void removeActionFromListByValue(List<Action> actionList, Action action) {
		actionList.remove(action);
	}

	private boolean checkSafetyBetweenTwoSteps(Action A, Action B) {
		for(State a_effect :A.getEffects()){
	        for(State b_precondition : B.getPreconditions()){
	            // check state name
	            if(a_effect.getStateName().equals(b_precondition.getStateName())){
	                // check state parameters
	                if(checkMatchingParameters(a_effect, b_precondition)){
	                    if(a_effect.isLogicOperation() != b_precondition.isLogicOperation())
	                        return false;
	                }
	            }
	        }
	    }
	    return true;
	}

	private Edge findAThreat(Action node) {
		if (node.getActionName().equals("FINISH"))
			return null;
		// find edges with node as source
		List<Edge> nodeEdges = new ArrayList<>();
		for (Edge edge : this.plan.getLinks()) {
			if (node == edge.getSource()) {
				if (checkSafetyBetweenTwoSteps(edge.getSource(), edge.getDestination())) {
					return edge;
				}
			}
		}

		return null;
	}

	private boolean resolveAThreat(Edge edge) {
		System.out.println("Resolve a Threat - Demotion attempt");
	    List<Edge> parent_new_actions = this.plan.getEdgeByDestination(edge.getSource());
	    List<Edge> grandparent_new_actions = this.plan.getEdgeByDestination(edge.getSource());
	    
	    Edge parent_e = parent_new_actions.get(0);
	    Edge grand_e  = grandparent_new_actions.get(0);
	    
	    // keep original
	    
	    Action grand_d_dest = grand_e.getDestination() ;    
	    Action parent_e_source = parent_e.getSource();
	    Action parent_e_dest = parent_e.getDestination();
	    
	    
	    grand_e.setDestination(edge.getSource());
	    Action tmp = parent_e.getSource();
	    parent_e.setSource(edge.getSource());
	    parent_e.setDestination(tmp);
	    edge.setSource(tmp);
	    
	    if(!checkSafetyBetweenTwoSteps(edge.getSource(), edge.getDestination()) ||
	       !checkSafetyBetweenTwoSteps(parent_e.getSource(), parent_e.getDestination())){
	        // try promotion
	        // recovery old status
	        grand_e.setDestination(grand_d_dest);  
	        parent_e.setSource(parent_e_source);
	        parent_e.setDestination(parent_e_dest);
	        
	        List<Edge> child_es = this.plan.getEdgeBySource(edge.getDestination());
	        Edge child_e = child_es.get(0);
	        parent_e.setDestination(edge.getSource());
	        tmp = edge.getSource();
	        
	        edge.setDestination(tmp);
	        edge.setSource(child_e.getSource());
	        child_e.setSource(tmp);
	        
	        
	        if(!checkSafetyBetweenTwoSteps(edge.getSource(), edge.getDestination()) ||
	            !checkSafetyBetweenTwoSteps(parent_e.getSource(), parent_e.getDestination())){
	            return false;
	        }
	        
	        
	    }
	    
	    
	    // promotion
	    return true;
	}

	private boolean checkMatchingParameters(State A, State B) {
		for (int i = 0; i < A.getParameters().size(); i++) {

			if (A.getParameters().get(i) != B.getParameters().get(i)) {
				return false;
			}
		}
		return true;
	}

	private void updateOpenPreconditions() {
	}

	private boolean checkResolutionBetweenStates(State A, State B) {
		 if(A.getStateName() == B.getStateName()){
		        // check state parameters
		        if(checkMatchingParameters(A, B)){
		            if(A.isLogicOperation()== B.isLogicOperation())
		                return true;
		        }
		    }
		    return false;
	}

	private boolean statePresentInList(List<State> states, State state) {
		for(State x : states){
	        if(checkResolutionBetweenStates(x, state)){
	            return true;
	        }
	    }
		return false;
	}

	private boolean findParameter(List<String> parameters, String parameter) {
		for (String param : parameters)
			if (param == parameter)
				return true;
		return false;
	}

	private boolean findStateInSet(Set<State> states, State state) {
		if(states.contains(state))
			return true;
		return false;
	}

	private boolean findStateInList(List<State> states, State state) {
		if(states.contains(state))
			return true;
		return false;
	}

	private boolean checkEffectsWithParameters(List<State> effects, State b) {
		for(State effect : effects){
            if(checkResolutionBetweenStates(effect, b)){
               return true;
            }
        }
    
    return false;
	}

	public void initiatePlan() {
		this.plan.setActions(new ArrayList<Action>());

		this.plan.setFinish(this.task.getFinish());
		this.plan.setStart(this.task.getStart());
		this.plan.getActions().add(this.plan.getStart());
		this.plan.getActions().add(this.plan.getFinish());
		this.plan.getLinks().add(new Edge(this.plan.getStart(), this.plan.getFinish()));
		Edge first_edge = new Edge(this.plan.getStart(), this.plan.getFinish());
		this.plan.getLinks().add(first_edge);
		for (State precond : this.plan.getFinish().getPreconditions()) {
			this.plan.getPreConditions().add(precond);
		}
	}

	public void compute() {
		System.out.println(" - start computation");
	    this.updateOpenPreconditions();

	    
	    // extract an open precondition
	    this.algorithm();
	    
	    System.out.println("[POP Algorithm] - Computation Finished");
	    // print the plan
	    this.plan.printPlan();
	    System.out.println(" [DONE]  ");
	}

	public void printPlan() {
		System.out.print(this.plan.getActions());
	}

	/**
	 * @return the task
	 */
	public Task getTask() {
		return task;
	}

	/**
	 * @param task the task to set
	 */
	public void setTask(Task task) {
		this.task = task;
	}

	/**
	 * @return the plan
	 */
	public Plan getPlan() {
		return plan;
	}

	/**
	 * @param plan the plan to set
	 */
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
}
