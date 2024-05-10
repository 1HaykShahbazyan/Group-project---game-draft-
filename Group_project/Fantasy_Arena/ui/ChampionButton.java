package Fantasy_Arena.ui;

import javax.swing.*;
import java.awt.*;
import Fantasy_Arena.core.*;

public class ChampionButton extends JButton {
    private Champion champion;
    private ImageIcon icon;

    public ChampionButton(Champion champion) {
        this.champion = champion;
        setText(champion.getName());
        updateStatus();
        setPreferredSize(new Dimension(150, 50));
        setFont(new Font("Arial", Font.PLAIN, 14));
        setHorizontalAlignment(SwingConstants.LEFT);
        setBackground(new Color(50, 100, 200));
        setImageByClass(champion);
    }

    public void updateStatus() {
        setText("<html><b>" + champion.getName() + "</b><br>HP: " + (int) champion.getHp() + " | MP: " + (int) champion.getMp() + "</html>");
    }

    public void highlight() {
        setBackground(Color.YELLOW);
    }

    public void highlight(Color color) {
        setBackground(color);
    }

    public void unhighlight() {
        setBackground(new Color(50, 100, 200));
    }

    public Champion getChampion() {
        return this.champion;
    }

    private void setImageByClass(Champion champion) {
        String[] toStringWords = champion.toString().split("\\s+");
        String imagePath = "img/" + toStringWords[0].toLowerCase() + ".png";
        icon = new ImageIcon(imagePath);
        Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(scaledImage));
    }
}
