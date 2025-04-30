package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MovingBackgroundPanel extends JPanel {
    private Image backgroundImage;
    private int imageWidth;
    private int imageHeight;

    private float currentOffsetX = 0;
    private float currentOffsetY = 0;
    private float targetOffsetX = 0;
    private float targetOffsetY = 0;

    private Timer animationTimer;

    private final int PANEL_WIDTH = 945;
    private final int PANEL_HEIGHT = 620;

    public MovingBackgroundPanel(String imagePath) {
        backgroundImage = new ImageIcon(imagePath).getImage();
        imageWidth = backgroundImage.getWidth(null);
        imageHeight = backgroundImage.getHeight(null);

        // Scale the image to fit 900x700
        Image scaledImage = backgroundImage.getScaledInstance(PANEL_WIDTH, PANEL_HEIGHT, Image.SCALE_SMOOTH);
        backgroundImage = scaledImage;

        // Mouse movement listener
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;

                int maxOffsetX = Math.max(0, imageWidth - getWidth());
                int maxOffsetY = Math.max(0, imageHeight - getHeight());

                targetOffsetX = (float)(e.getX() - centerX) * maxOffsetX / centerX / 20;
                targetOffsetY = (float)(e.getY() - centerY) * maxOffsetY / centerY / 20;

                targetOffsetX = clamp(targetOffsetX, 0, maxOffsetX);
                targetOffsetY = clamp(targetOffsetY, 0, maxOffsetY);
            }
        });

        // Use javax.swing.Timer (not java.util.Timer)
        animationTimer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Smoothly interpolate current towards target
                currentOffsetX += (targetOffsetX - currentOffsetX) * 0.05f;
                currentOffsetY += (targetOffsetY - currentOffsetY) * 0.05f;
                repaint();
            }
        });
        animationTimer.start();
    }

    private float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(value, max));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Scale the image to 900x700 and apply the offset for the parallax effect
        g.drawImage(backgroundImage, (int)-currentOffsetX, (int)-currentOffsetY, PANEL_WIDTH, PANEL_HEIGHT, this);
    }
}


