package dev.ws.playground.routine;

/**
 * @author native
 */

public class Routine {
	protected String name, description;
	
	public Routine(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public void onLoad() {}
	public void onPop() {}
	
	public static void passRoutine(Routine routine) {
		RoutineLoader.loadRoutine(routine);
	}
	
	public String getRoutineName() {
		return name;
	}
	
	public String getRoutineDesc() {
		return description;
	}
}
