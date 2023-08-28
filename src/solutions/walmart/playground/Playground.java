package solutions.walmart.playground;

import solutions.walmart.playground.bytecodefield.BytecodeFieldMod;
import solutions.walmart.playground.theme.ThemeBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class Playground {
    private JTextArea sourceArea;
    private JTextPane bytecodePane;
    private JCompilerService compilerService;
    private JTextField classNameField;
    private String lastClassName;

    public Playground() {
        compilerService = new JCompilerService();
        ThemeBuilder.buildDark();
        setupGUI();
    }

    private void setupGUI() {
        JFrame frame = new JFrame("github.com/WalmartSolutions");
        frame.setSize(800, 725);
        frame.setDefaultCloseOperation(3); // EXIT_ON_CLOSE
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cleanup();
                System.exit(0);
            }
        });

        sourceArea = new JTextArea();
        bytecodePane = new JTextPane();

        sourceArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        bytecodePane.setFont(new Font("Consolas", Font.PLAIN, 12));
        bytecodePane.setEditable(false);

        classNameField = new JTextField("Class");
        classNameField.setPreferredSize(new Dimension(150, 25));

        JButton translateButton = new JButton("Translate");
        translateButton.addActionListener(e -> {
            lastClassName = classNameField.getText();
            String bytecode = compilerService.getBytecode(sourceArea.getText(), lastClassName);
            bytecodePane.setText(bytecode);

            // highlighting
            BytecodeFieldMod.setHighlighting(bytecodePane);
        });

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controlPanel.add(classNameField);
        controlPanel.add(translateButton);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(controlPanel);
        mainPanel.add(new JScrollPane(sourceArea));
        mainPanel.add(new JScrollPane(bytecodePane));

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Playground::new);
    }

    private void cleanup() {
        if (lastClassName != null && !lastClassName.isEmpty()) {
            new File(lastClassName + ".java").delete();
            new File(lastClassName + ".class").delete();
        }
    }
}
