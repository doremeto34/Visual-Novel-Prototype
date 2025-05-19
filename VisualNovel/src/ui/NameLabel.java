package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class NameLabel extends JLabel {

    public NameLabel() {
        super("");
        setFont(new Font("Verdana", Font.BOLD, 18));
        setForeground(Color.WHITE);
        //setOpaque(true);
        //setBackground(new Color(0, 0, 0, 150)); // semi-transparent black
        setHorizontalAlignment(SwingConstants.CENTER);
        setBounds(65, 400, 200, 30); // default size, you can adjust
        setVisible(false); // hidden initially
    }

    public void setSpeaker(String speaker) {
        if (speaker != null && !speaker.isEmpty()) {
            setText(speaker);
            setVisible(true);
        } else {
            setVisible(false);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int arc = 20; // corner arc radius

        // Set transparency
        g2.setComposite(AlphaComposite.SrcOver.derive(0.6f));
        g2.setColor(Color.BLACK);

        // Create path with rounded top corners only
        Path2D.Float path = new Path2D.Float();
        path.moveTo(0, arc);
        path.quadTo(0, 0, arc, 0); // top-left corner
        path.lineTo(width - arc, 0);
        path.quadTo(width, 0, width, arc); // top-right corner
        path.lineTo(width, height); // straight down
        path.lineTo(0, height);     // straight left
        path.closePath();

        g2.fill(path);
        g2.dispose();

        super.paintComponent(g);
    }


}
