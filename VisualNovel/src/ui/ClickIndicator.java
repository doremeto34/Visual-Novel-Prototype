package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickIndicator extends JLabel {
    private Timer blinkTimer;
    private boolean visible = true;

    public ClickIndicator() {
        super("▶"); // The little arrow
        setFont(new Font("SansSerif", Font.PLAIN, 15));
        setForeground(new Color(225,200,0));
        setVisible(false);
        setBounds(660, 110, 15, 15); // Default position

        blinkTimer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(visible);
                visible = !visible;
            }
        });
    }

    public void startBlinking() {
        blinkTimer.start();
    }

    public void stopBlinking() {
        blinkTimer.stop();
        setVisible(false);
    }
}
