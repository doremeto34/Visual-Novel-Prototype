package main;

import javax.swing.*;

import story.SceneManager;
import ui.SaveLoadScreen;
import util.SaveManager;

import java.awt.*;
import java.awt.event.*;
import ui.MovingBackgroundPanel;

public class TitleScreen extends JFrame {
    private MovingBackgroundPanel backgroundPanel;
    private JPanel menuPanel;

    public TitleScreen() {
        setTitle("Visual Novel");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); 
        setResizable(false);

        // 1. Moving Fullscreen Background
        backgroundPanel = new MovingBackgroundPanel("assets/backgrounds/title_bg.jpg");
        backgroundPanel.setBounds(0,0,1024,683);
        setContentPane(backgroundPanel); // <-- important: set background as content pane
        backgroundPanel.setLayout(null); // free positioning

        // 2. Menu Panel (Floating on the left)
        menuPanel = new JPanel();
        menuPanel.setOpaque(false); // transparent
        menuPanel.setLayout(new GridLayout(4, 1, 10, 10));
        menuPanel.setBounds(30, 150, 200, 250); // adjust as needed

        // Buttons
        JButton newGameBtn = createMenuButton("New Game");
        JButton loadGameBtn = createMenuButton("Load Game");
        JButton settingsBtn = createMenuButton("Settings");
        JButton exitBtn = createMenuButton("Exit");

        menuPanel.add(newGameBtn);
        menuPanel.add(loadGameBtn);
        menuPanel.add(settingsBtn);
        menuPanel.add(exitBtn);

        backgroundPanel.add(menuPanel); // menu floats over background

        // Button actions
        newGameBtn.addActionListener(e -> {
            PlayerStats stats = new PlayerStats();
            new GameScreen(stats);
            dispose();
        });

        loadGameBtn.addActionListener(e -> {
            new SaveLoadScreen(null, this, false);
            dispose();
        });

        settingsBtn.addActionListener(e -> {
            // TODO: Settings screen
        });

        exitBtn.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setFont(new Font("Verdana", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 0, 0, 150)); // semi-transparent dark background
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        return button;
    }
}


