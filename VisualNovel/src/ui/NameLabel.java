package ui;

import javax.swing.*;
import java.awt.*;

public class NameLabel extends JLabel {

    public NameLabel() {
        super("");
        setFont(new Font("Verdana", Font.BOLD, 18));
        setForeground(Color.WHITE);
        setOpaque(true);
        setBackground(new Color(0, 0, 0, 150)); // semi-transparent black
        setHorizontalAlignment(SwingConstants.CENTER);
        setBounds(60, 400, 200, 30); // default size, you can adjust
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
}
