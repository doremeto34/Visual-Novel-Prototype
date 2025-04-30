package hust.soict.hedspi.aims.screen.manager;

import javax.swing.*;

import hust.soict.hedspi.aims.media.Media;

import java.awt.*;

public class PlayWindow extends JDialog {
    public PlayWindow(Media media) {
       
    	setAlwaysOnTop(true);
    	setSize(300, 150);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Playing: " + media.getTitle(), SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);

        // Call the media's play() method (prints to console or adds behavior)
        media.play();

        setVisible(true); // This must be called to show the dialog
    }
}
