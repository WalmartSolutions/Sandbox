package dev.ws.playground.routine.routines;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dev.ws.playground.routine.Routine;
import dev.ws.playground.routine.routines.assistant.RoutineAssistant;

/**
 * @author native
 * This is likely where the worst code in the whole project resides.
 * Made it at 2 AM, I don't know what I cooked here.
 */

public class FilterRoutine extends Routine {
	public static String output;
	
	public FilterRoutine(String output) {
		super("FilterRoutine", "Removes unnecessary output from javap.");
		this.output = output;
	}
	
	@Override
	public void onLoad() {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(output);
			
			if (sb.toString().contains("Compiled from")) {
				sb.delete(0, sb.indexOf("\n"));
				sb.delete(sb.indexOf("\n"), 1);
			}
			
			if (sb.toString().contains("// Field"))
				remove(sb, "// Field");
			if (sb.toString().contains("// Method"))
				remove(sb, "// Method");
			if (sb.toString().contains("// String"))
				remove(sb, "// String");
			
			int hashIndex = sb.indexOf("#");
			while (hashIndex != -1) {
				int endIndex = sb.indexOf(" ", hashIndex);
				sb.delete(hashIndex, endIndex);
				hashIndex = sb.indexOf("#");
			}
			
			while (sb.toString().contains("{")) {
				int index = sb.indexOf("{");
				sb.replace(index, index + "{".length(), "-> [START]");
			}
			
			while (sb.toString().contains("}")) {
				int index = sb.indexOf("}");
				sb.replace(index, index + "[END]".length(), "[END]");
			}
			
			while (sb.toString().contains("void")) {
				int index = sb.indexOf("void");
				sb.replace(index, index + "void".length(), "[METHOD]");
			}
		
			while (sb.toString().contains("Code:")) {
				int index = sb.indexOf("Code:");
				//int start = sb.lastIndexOf("\n", index) + 1; Use this to remove it instead of replacing.
				int end = sb.indexOf("\n", index);

				sb.replace(index, end, "[BODY]: ");
			}

			RoutineAssistant.write(RoutineAssistant.writeFile, sb.toString());
		} catch (IOException e) {
			passRoutine(new CrashRoutine("Failed to call RoutineAssistant's 'write().'"));
		}
	}
	
	public void remove(StringBuilder sb, String in) {
		while (sb.toString().contains(in)) {
			int index = sb.indexOf(in);
			sb.delete(index, index + in.length());
		}
	}
}
