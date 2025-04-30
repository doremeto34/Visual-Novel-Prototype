package ui;

import javax.swing.*;
import java.awt.*;
import main.PlayerStats;
import main.GameScreen;
import util.SaveManager;

public class SaveLoadPanel extends JPanel {
    private static final String SAVE_FILE = "save1.dat";

    public SaveLoadPanel(GameScreen gameScreen, PlayerStats playerStats) {
        setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        setOpaque(false);
        setBounds(550, -5, 230, 60);

        JButton saveButton = createStyledButton("💾 Save");
        saveButton.addActionListener(e -> {
            new SaveLoadScreen(gameScreen, null, true); // Open Save screen
        });

        JButton loadButton = createStyledButton("📂 Load");
        loadButton.addActionListener(e -> {
            new SaveLoadScreen(gameScreen, null, false); // Open Load screen
        });

        add(saveButton);
        add(loadButton);
    }


    private JButton createStyledButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Semi-transparent background
                g2.setColor(new Color(70, 130, 180, 180)); // Blue with alpha (180/255)
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // 20 = curve radius

                super.paintComponent(g2);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                // No border painting
            }
        };

        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(100, 30));
        return button;
    }
}
