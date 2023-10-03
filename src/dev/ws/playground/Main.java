package dev.ws.playground;

import dev.ws.playground.routine.RoutineLoader;

/**
 * @author native
 */

public class Main {
	public static final String VERSION = "0.6";
	
	public static void main(String[] args) {
		/*
		 * The code is split into what we call 'routines'.
		 * The currently running routine will be logged in the output stream.
		 */
		RoutineLoader routineLoader = new RoutineLoader(args);
	}
}
