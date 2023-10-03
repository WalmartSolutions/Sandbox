package dev.ws.playground.routine.routines;

import java.io.File;
import java.io.IOException;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import dev.ws.playground.routine.Routine;

public class CompileRoutine extends Routine {
	public static String input;
	
	public CompileRoutine(String input) {
		super("CompileRoutine", "Compiles .java files using javac.");
		this.input = input;
	}
	
	@Override
	public void onLoad() {
		StringBuilder sb = new StringBuilder();
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		compiler.run(null, null, null, input);
		
		sb.append(input);
		if (sb.toString().endsWith(".java")) {
			int lastDotIndex = sb.lastIndexOf(".");
	        sb.replace(lastDotIndex, sb.length(), ".class");
		}
		
		passRoutine(new ExecutionRoutine(sb.toString()));
		
		File deleteFile = new File(sb.toString());
		deleteFile.delete();
	}
}
