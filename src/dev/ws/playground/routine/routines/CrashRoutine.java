package dev.ws.playground.routine.routines;

import dev.ws.playground.routine.Routine;

/**
 * @author native
 */

public class CrashRoutine extends Routine {
	public static String crashMsg;
	
	public CrashRoutine(String crashMsg) {
		super("CrashRoutine", "Many routines pass the execution in here to crash the program.");
		this.crashMsg = crashMsg;
	}
	
	@Override
	public void onLoad() {
		System.out.println("CrashRoutine: " + crashMsg);
		throw new RuntimeException(crashMsg);
	}
}
