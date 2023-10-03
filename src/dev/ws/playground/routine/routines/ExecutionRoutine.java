package dev.ws.playground.routine.routines;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import dev.ws.playground.routine.Routine;
import dev.ws.playground.routine.routines.assistant.RoutineAssistant;

/**
 * @author native
 */

public class ExecutionRoutine extends Routine {
	public static String input;
	
	public ExecutionRoutine(String input) {
		super("ExecutionRoutine", "Executes the 'javap' command to get output.");
		this.input = input;
	}
	
	@Override
	public void onLoad() {
		try {
			StringBuilder sb = new StringBuilder();
			
			Process process = Runtime.getRuntime().exec("javap -c " + input);
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
			RoutineAssistant.begin();
			
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line).append("\n");
			}
			
			passRoutine(new FilterRoutine(sb.toString()));
		} catch (IOException e) {
			passRoutine(new CrashRoutine("Failed to run javap, please check your java installation"));
		}
	}
}
