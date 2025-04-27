package ui;

import javax.swing.*;

import java.awt.*;
import java.util.List;

public class ChoicePanel extends JPanel {

    public ChoicePanel() {
        setLayout(new GridLayout(0, 1, 10, 10)); // vertical layout
        setOpaque(false); // allow transparency
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Semi-transparent black background
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(new Color(0, 0, 0, 150)); // Black with 150 alpha
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // Rounded corners
        g2.dispose();
    }

    public void showChoices(List<Choice> choices, Runnable onChoiceMade) {
        removeAll();
        setVisible(true);

        for (Choice choice : choices) {
        	if (choice.condition.getAsBoolean()) {
        		JButton button = new JButton(choice.text);
        		styleChoiceButton(button); //  clean styling
        		button.addActionListener(e -> {
        			setVisible(false);
        			choice.action.run();
        			if (onChoiceMade != null) {
        				onChoiceMade.run();
        			}
        		});
        		add(button);
        	}
        }

        revalidate();
        repaint();
    }

    private void styleChoiceButton(JButton button) {
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false); // Important: no default opaque color
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Verdana", Font.PLAIN, 20));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Custom paint for rounded background
        button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Background color (depending on button state)
                Color bg = button.getModel().isRollover() ? new Color(80, 80, 80, 220) : new Color(50, 50, 50, 200);
                g2.setColor(bg);

                // Draw rounded rectangle background
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 20, 20);

                g2.dispose();

                super.paint(g, c);
            }
        });
    }

}
