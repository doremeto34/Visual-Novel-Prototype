package main;

import javax.swing.*;
import ui.MovingBackgroundPanel;
import ui.SaveLoadScreen;
import java.awt.*;
import java.awt.event.*;

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
        backgroundPanel = new MovingBackgroundPanel("assets/backgrounds/anime_bg.jpg");
        backgroundPanel.setBounds(0, 0, 800, 600);
        setContentPane(backgroundPanel); // <-- important: set background as content pane
        backgroundPanel.setLayout(null); // free positioning

     // 2. Menu Panel (Translucent Panel on the left)
        menuPanel = new JPanel();
        menuPanel.setOpaque(true); // Transparent
        menuPanel.setBackground(new Color(0, 0, 0, 150)); // Semi-transparent background
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS)); // Vertical layout for options
        menuPanel.setBounds(30, 0, 200, 600); // Set position and size

        // Create text options
        JLabel newGameLabel = createMenuOption("New Game");
        JLabel loadGameLabel = createMenuOption("Load Game");
        JLabel settingsLabel = createMenuOption("Settings");
        JLabel creditsLabel = createMenuOption("Credits");
        JLabel exitLabel = createMenuOption("Exit");

        // Add vertical glue before and after the labels to center them
        menuPanel.add(Box.createVerticalGlue()); // Adds space before the first label
        menuPanel.add(newGameLabel);
        menuPanel.add(loadGameLabel);
        menuPanel.add(settingsLabel);
        menuPanel.add(creditsLabel);
        menuPanel.add(exitLabel);
        menuPanel.add(Box.createVerticalGlue()); // Adds space after the last label

        // Add the menu panel to the background panel
        backgroundPanel.add(menuPanel); // menu floats over background


        // Option actions
        newGameLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PlayerStats stats = new PlayerStats();
                new GameScreen(stats);
                dispose();
            }
        });

        loadGameLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new SaveLoadScreen(null, TitleScreen.this, false);
                dispose();
            }
        });

        settingsLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO: Settings screen
            }
        });

        creditsLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO: Credits screen
            }
        });

        exitLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    // Helper method to create a menu option with hover effect (text only)
    private JLabel createMenuOption(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Verdana", Font.BOLD, 18));
        label.setForeground(Color.WHITE);
        label.setOpaque(false); // Make label background transparent
        label.setAlignmentX(Component.CENTER_ALIGNMENT); // Center-align text
        label.setPreferredSize(new Dimension(180, 40)); // Adjust size

        // Hover effect: change text color on hover
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setForeground(new Color(255, 200, 0)); // Change text color on hover (gold)
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setForeground(Color.WHITE); // Reset text color when mouse leaves
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // Optional: Play a sound or add a click effect
            }
        });

        return label;
    }
}

