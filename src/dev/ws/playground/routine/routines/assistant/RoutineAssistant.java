package dev.ws.playground.routine.routines.assistant;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import dev.ws.playground.routine.routines.CrashRoutine;
import dev.ws.playground.routine.routines.InitRoutine;

/**
 * @author native
 */

public class RoutineAssistant extends InitRoutine {
	public static File writeFile = new File("output.txt");
	
	public RoutineAssistant(String[] args) {
		super(args);
	}
	

	public static void begin() {
		try {
			if (writeFile.createNewFile()) {
				System.out.println("[WS] Output written to " + writeFile);
			}
			else
				passRoutine(new CrashRoutine("Couldn't create file output.txt, it already exists."));
		} catch (IOException ioEx) {
			// Dumb as well.
			passRoutine(new CrashRoutine("An error occured at dev.ws.playground.routine.routines.initroutine.RoutineAssistant"));
		}
	}
	
	public static void write(File file, String content) throws IOException {
		FileWriter fileWriter = new FileWriter(file);
		fileWriter.write(content);
		fileWriter.close();
	}
}
