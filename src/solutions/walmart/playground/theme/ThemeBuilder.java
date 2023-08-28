package solutions.walmart.playground.theme;

import javax.swing.*;
import java.awt.*;

public class ThemeBuilder {
    public static void buildDark() {
        Color bgColor = new Color(45, 47, 47); // Dark gray
        Color fgColor = new Color(187, 187, 187); // Light gray

        UIManager.put("control", bgColor);
        UIManager.put("info", new Color(255, 128, 128)); // Tooltips, etc.
        UIManager.put("nimbusBase", new Color(18, 30, 49)); // Checkbox, RadioButton, ToggleButton
        UIManager.put("nimbusAlertYellow", new Color(248, 187, 0));
        UIManager.put("nimbusDisabledText", new Color(128, 128, 128, 255));
        UIManager.put("nimbusFocus", new Color(115, 164, 209));
        UIManager.put("nimbusGreen", new Color(176, 179, 50));
        UIManager.put("nimbusInfoBlue", new Color(66, 139, 221));
        UIManager.put("nimbusLightBackground", bgColor);
        UIManager.put("nimbusOrange", new Color(191, 98, 4));
        UIManager.put("nimbusRed", new Color(169, 46, 34));
        UIManager.put("nimbusSelectedText", fgColor);
        UIManager.put("nimbusSelectionBackground", new Color(104, 93, 156));
        UIManager.put("text", fgColor);

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
