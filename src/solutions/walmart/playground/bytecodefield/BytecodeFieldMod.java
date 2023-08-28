package solutions.walmart.playground.bytecodefield;

import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class BytecodeFieldMod {

    /* This is kinda dumb but i cant be bothered */
    static String[] keywords = {
            "ldc", "invokevirtual", "invokestatic", "invokeinterface", "aload_0", "astore", "areturn",
            "invokespecial", "return", "iconst_0", "iconst_1", "iconst_2", "iconst_3", "putfield", "getstatic",
            "ireturn", "ifne", "aload_1", "ifeq", "bipush", "sipush", "iconst_4", "iconst_5", "iload_1"
    };

    public static void setHighlighting(JTextPane bytecodePane) {
        StyledDocument doc = bytecodePane.getStyledDocument();

        Style defaultStyle = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
        Style keywordStyle = doc.addStyle("KeywordStyle", defaultStyle);
        StyleConstants.setForeground(keywordStyle, new Color(0, 150, 255));

        String text = bytecodePane.getText();
        for (String keyword : keywords) {
            int index = text.indexOf(keyword);
            while (index >= 0) {
                doc.setCharacterAttributes(index, keyword.length(), bytecodePane.getStyle("KeywordStyle"), true);
                index = text.indexOf(keyword, index + keyword.length());
            }
        }
    }
}
