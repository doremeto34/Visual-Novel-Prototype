package ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ChoicePanel extends JPanel {

    public ChoicePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // vertical, respects preferred sizes
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        g2.dispose();
    }

    public void showChoices(List<Choice> choices, Runnable onChoiceMade) {
        removeAll();
        setVisible(true);

        for (Choice choice : choices) {
            if (choice.condition.getAsBoolean()) {
                // wrap text at ~380px so it will wrap inside a 400px button
            	String html = "<html><div style='width:300px; word-break:break-word;'>" + choice.text + "</div></html>";
                JButton btn = new JButton(html);

                styleChoiceButton(btn);
                btn.setAlignmentX(Component.CENTER_ALIGNMENT);
                btn.setMaximumSize(new Dimension(400, Short.MAX_VALUE)); // fix width, flex height

                btn.addActionListener(e -> {
                    setVisible(false);
                    choice.action.run();
                    if (onChoiceMade != null) onChoiceMade.run();
                });

                add(btn);
                add(Box.createVerticalStrut(10)); // space between buttons
            }
        }

        revalidate();
        repaint();
    }

    private void styleChoiceButton(JButton button) {
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Verdana", Font.PLAIN, 18));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(
                  RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
                );
                Color bg = button.getModel().isRollover()
                  ? new Color(80, 80, 80, 220)
                  : new Color(50, 50, 50, 200);
                g2.setColor(bg);
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 20, 20);
                g2.dispose();
                super.paint(g, c);
            }
        });
    }
}
