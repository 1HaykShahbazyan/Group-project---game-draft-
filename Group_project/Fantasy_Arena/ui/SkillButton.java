package Fantasy_Arena.ui;

import javax.swing.*;
import java.awt.*;

public class SkillButton extends JButton {
    public SkillButton(String skillName) {
        setText(skillName);
        setPreferredSize(new Dimension(100, 30));
        setFont(new Font("Arial", Font.PLAIN, 12));
        setBackground(Color.BLUE);
        setForeground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}

