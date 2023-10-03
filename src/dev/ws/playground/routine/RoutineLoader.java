package dev.ws.playground.routine;

import java.util.ArrayList;
import java.util.List;

import dev.ws.playground.routine.routines.InitRoutine;

/**
 * @author native
 */

public class RoutineLoader {
	private static List<Routine> routines = new ArrayList<>();
	
	public RoutineLoader(String[] args) {
		/*
		 * We start by loading the initialization routine and from then on the execution gets passed around.
		 */
		loadRoutine(new InitRoutine(args));
	}
	
	public static Routine getRoutine(String routine) {
		for (Routine r : routines) {
			if (r.getRoutineName().equals(routine))
				return r;
		}
		return null;
	}
	
	public static Routine getRoutine(Routine routine) {
		for (Routine r : routines) {
			if (r.equals(routine))
				return r;
		}
		return null;
	}
	
	public static void loadRoutine(Routine routine) {
		// This is definitely not the cleanest way of doing it but it works.
		routines.add(routine);
		System.out.println("Loaded routine -> " + routine.getRoutineName());
		routine.onLoad();
		popRoutine(routine);
	}
	
	public static void popRoutine(Routine routine) {
		routines.remove(routine);
		System.out.println("Popped routine -> " + routine.getRoutineName());
		routine.onPop();
	}
	
	public static List<Routine> getRoutines() {
		return routines;
	}
}
