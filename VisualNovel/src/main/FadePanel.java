package main;

import javax.swing.*;
import java.awt.*;

public class FadePanel extends JPanel {
    private float alpha = 0f;

    public FadePanel() {
        setOpaque(false);
        setBounds(0, 0, 800, 600); // Or you can set size later dynamically
    } 

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
        repaint();
    }
}
