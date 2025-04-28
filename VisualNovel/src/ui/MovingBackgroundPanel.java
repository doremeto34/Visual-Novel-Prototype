package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MovingBackgroundPanel extends JPanel {
    private Image backgroundImage;
    private int imageWidth;
    private int imageHeight;
    private int offsetX = 0;
    private int offsetY = 0;

    public MovingBackgroundPanel(String imagePath) {
        backgroundImage = new ImageIcon(imagePath).getImage();
        imageWidth = backgroundImage.getWidth(null);
        imageHeight = backgroundImage.getHeight(null);

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;

                int maxOffsetX = Math.max(0, imageWidth - getWidth());
                int maxOffsetY = Math.max(0, imageHeight - getHeight());

                offsetX = (e.getX() - centerX) * maxOffsetX / centerX / 20;
                offsetY = (e.getY() - centerY) * maxOffsetY / centerY / 20;

                offsetX = Math.max(0, Math.min(offsetX, maxOffsetX));
                offsetY = Math.max(0, Math.min(offsetY, maxOffsetY));

                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, -offsetX, -offsetY, imageWidth, imageHeight, this);
    }
}

