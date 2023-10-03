package dev.ws.playground.routine.routines;

import dev.ws.playground.routine.Routine;

/**
 * @author native
 */

public class InitRoutine extends Routine {
	public static String[] args;
	
	public InitRoutine(String[] args) {
		super("InitRoutine", "Routine that gets loaded at initialization.");
		this.args = args;
	}
	
	@Override
	public void onLoad() {
		if (args.length != 1)
			passRoutine(new CrashRoutine("Not enough or more than needed arguments passed."));
		
		String input = args[0];
		if (!input.endsWith(".java") && !input.endsWith(".class"))
			passRoutine(new CrashRoutine("Unknown extension passed - " + input));
		
		passRoutine(new IntroRoutine());
		
		if (input.endsWith(".java"))
			passRoutine(new CompileRoutine(input));
		else if (input.endsWith(".class"))
			passRoutine(new ExecutionRoutine(input));
	}
	
	@Override
	public void onPop() {
		/*
		 * After all of onLoad() finishes we can close the program because InitRoutine is the parent.
		 * Meaning if InitRoutine's onLoad() is done we know that everything finished.
		 */
		System.exit(0);
	}
}
