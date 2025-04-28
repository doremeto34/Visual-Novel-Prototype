package ui;

import javax.swing.*;
import java.awt.*;

public class DialogueTextArea extends JTextArea {

    public DialogueTextArea() {
        setWrapStyleWord(true);
        setLineWrap(true);
        setEditable(false);
        setFocusable(false);
        setOpaque(false);
        setForeground(Color.WHITE);
        setFont(new Font("Verdana", Font.PLAIN, 18));
    }
}
