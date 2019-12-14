/**
 * 
 */
package main;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import utilities.Action;
import utilities.State;
import utilities.Task;;

/**
 * The main entry point for the POP planner program
 * 
 * @author
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter initial state");
		String stateName = scanner.nextLine();
		State initialState = new State(stateName,null,true);
		
		System.out.println("Please enter final state");
		String finalStateName = scanner.nextLine();
		State finalState = new State(finalStateName,null,true);
		
		System.out.println("Please enter number of operators");
		int numOfOperators = scanner.nextInt();
		
		System.out.println("Enter all operators seperated by commas and then press enter:");
		String readLine = scanner.next();
		
		
		//scanner.nextLine();
		String[] operatorNames = readLine.split(",");
		
		LinkedList<String> items = new LinkedList<String>();
		for (int i = 0; i < numOfOperators; i++) {
			items.add(operatorNames[i]);
		}
		
		LinkedList<Action> actions = new LinkedList<Action>();
		//start
		Action start = new Action();
	    start.setActionName("START");
	    initialState.setOwner(start);
	    start.setEffects(generateStartEffects(start));
	    
	    // finish
	    Action finish = new Action();
	    finish.setActionName("FINISH");
	    finalState.setOwner(finish);
	    finish.setPreconditions(generateFinishPreconditions(finish));
	    
	    Task task = new Task(items, actions, start, finish);
	    PopPlanner popPlanner = new PopPlanner(task);
	    popPlanner.compute();
	    popPlanner.printPlan();
		scanner.close();
	}

	/**
	 * @param finish
	 * @return
	 */
	private static List<State> generateFinishPreconditions(Action finish) {
		LinkedList<String> on_a_b_params = new LinkedList<String>();
	    on_a_b_params.add("A");
	    on_a_b_params.add("B");
	    State on_a_b = new State("ON", finish, true);
	    on_a_b.setParameters(on_a_b_params);
	    
	    // on(B,C)
	    LinkedList<String> on_b_c_params = new LinkedList<String>();
	    on_b_c_params.add("B");
	    on_b_c_params.add("C");
	    State on_b_c = new State("ON", finish, true);
	    on_b_c.setParameters(on_b_c_params);
	    
	    LinkedList<State> preconditions = new LinkedList<State>();
	    preconditions.add(on_a_b);
	    preconditions.add(on_b_c);
	    
	    return preconditions;
	}

	/**
	 * @param start
	 * @return
	 */
	private static LinkedList<State> generateStartEffects(Action start) {
		LinkedList<String> on_c_a_params = new LinkedList<String>();
	    on_c_a_params.add("C");
	    on_c_a_params.add("A");
	    State on_c_a = new State("ON", start, true);
	    on_c_a.setParameters(on_c_a_params);
	    
	    
	    // clear(C)
	    LinkedList<String> clear_c_params = new LinkedList<String>();
	    clear_c_params.add("C");
	    State clear_c = new State("CLEAR", start, true);
	    clear_c.setParameters(clear_c_params);
	    
	    // clear(B)
	    LinkedList<String> clear_b_params = new LinkedList<String>();
	    clear_b_params.add("B");
	    State clear_b = new State("CLEAR", start, true);
	    clear_b.setParameters(clear_b_params);
	    
	    // table(A)
	    LinkedList<String> table_a_params = new LinkedList<String>();
	    table_a_params.add("A");
	    State table_a = new State("TABLE", start, true);
	    table_a.setParameters(table_a_params);
	    
	    
	    // table(B)
	    LinkedList<String> table_b_params = new LinkedList<String>();
	    table_b_params.add("B");
	    State table_b = new State("TABLE", start, true);
	    table_b.setParameters(table_b_params);
	    
	    LinkedList<State> effects = new LinkedList<State>();
	    effects.add(on_c_a);
	    effects.add(clear_c);
	    effects.add(clear_b);
	    effects.add(table_a);
	    effects.add(table_b);
	    
	    return effects;
	}

}
