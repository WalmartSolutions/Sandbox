package dev.ws.playground.routine.routines;

import dev.ws.playground.Main;
import dev.ws.playground.routine.Routine;

/**
 * @author native
 */

public class IntroRoutine extends Routine {
	public IntroRoutine() {
		super("IntroRoutine", "Prints an intro to the output stream.");
	}
	
	@Override
	public void onLoad() {
		String username = System.getProperty("user.name");
		System.out.println("[WS] Welcome to Playground " + username + "!");
		System.out.println("[WS] Running version -> " + Main.VERSION);
	}
}
