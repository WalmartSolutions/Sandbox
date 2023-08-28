package solutions.walmart.playground;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JCompilerService {
    public String getBytecode(String source, String customClass) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        String className = customClass;  // You may want to extract this from the source code or make it more dynamic.
        File sourceFile = new File(className + ".java");
        try (FileWriter writer = new FileWriter(sourceFile)) {
            writer.write(source);
        } catch (IOException e) {
            e.printStackTrace();
        }
        compiler.run(null, null, null, sourceFile.getPath());

        /**
         * public class Class {
         *     boolean test;
         *
         *     public void setTest(boolean test) {
         * 	this.test = test;
         *     }
         * }
         */

        StringBuilder bytecode = new StringBuilder();
        try {
            Process process = new ProcessBuilder("javap", "-c", className).start();
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {

                /**
                 * kill me.
                 */
                if (line.contains("// Method")) {
                    String methodRef = line.substring(line.indexOf("// Method") + 9).trim();
                    methodRef = methodRef.replace("\"", "");

                    Matcher matcher = Pattern.compile("^\\s*").matcher(line);
                    String indentation = (matcher.find()) ? matcher.group() : "";

                    String instructionPart = line.substring(0, line.indexOf("//")).trim();
                    line = indentation + instructionPart + " " + methodRef;
                }

                // this should be fixed to trim spaces between the refs and the instruction properly but whatever :p
                if (line.contains("// Field")) {
                    String fieldRef = line.substring(line.indexOf("// Field") + 9).trim();
                    fieldRef = fieldRef.replace("\"", "");

                    Matcher matcher = Pattern.compile("^\\s*").matcher(line);
                    String indentation = (matcher.find()) ? matcher.group() : "";

                    String instructionPart = line.substring(0, line.indexOf("//")).trim();
                    line = indentation + instructionPart + " " + fieldRef;
                }

                if (!line.contains("Code:")) {
                    bytecode.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytecode.toString();
    }
}
