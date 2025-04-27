package ui;

import javax.swing.*;
import java.awt.*;

public class DialoguePanel extends JPanel {
    private int cornerRadius = 30; // default 30px rounding
    private int padding = 20;      // default 20px inner padding

    public DialoguePanel() {
        super();
        setOpaque(false);
        setLayout(new BorderLayout()); // still allow adding text inside
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(cornerRadius, cornerRadius);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Semi-transparent black
        graphics.setColor(new Color(0, 0, 0, 150));
        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
    }

    @Override 
    public Insets getInsets() {
        return new Insets(padding, padding, padding, padding);
    }

    // (Optional) If you ever want to customize radius/padding
    public void setCornerRadius(int radius) {
        this.cornerRadius = radius;
        repaint();
    }

    public void setPadding(int padding) {
        this.padding = padding;
        revalidate();
        repaint();
    }
}
