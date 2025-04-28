package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MovingBackgroundPanel extends JPanel {
    private Image backgroundImage;
    private int imageWidth;
    private int imageHeight;

    private float currentOffsetX = 0;
    private float currentOffsetY = 0;
    private float targetOffsetX = 0;
    private float targetOffsetY = 0;

    private Timer animationTimer;

    public MovingBackgroundPanel(String imagePath) {
        backgroundImage = new ImageIcon(imagePath).getImage();
        imageWidth = backgroundImage.getWidth(null);
        imageHeight = backgroundImage.getHeight(null);

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

        // Timer for smooth animation
        animationTimer = new Timer(16, e -> {
            // Smoothly interpolate current towards target
            currentOffsetX += (targetOffsetX - currentOffsetX) * 0.05f;
            currentOffsetY += (targetOffsetY - currentOffsetY) * 0.05f;
            repaint();
        });
        animationTimer.start();
    }

    private float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(value, max));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, (int)-currentOffsetX, (int)-currentOffsetY, 900,600, this);
    }
}


