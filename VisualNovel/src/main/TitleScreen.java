package main;

import javax.swing.*;

import story.SceneManager;
import ui.SaveLoadScreen;
import util.SaveManager;

import java.awt.*;
import java.awt.event.*;

public class TitleScreen extends JFrame {
    private JLabel backgroundLabel;
    private JLabel logoLabel;
    private JPanel btnPanel;

    public TitleScreen() {
        setTitle("Visual Novel");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // Absolute positioning

        
        // 1. Background
        backgroundLabel = new JLabel(new ImageIcon("assets/backgrounds/title_bg.jpg"));
        backgroundLabel.setBounds(0, 0, 800, 600);
        add(backgroundLabel);

        // 2. Game Logo
        logoLabel = new JLabel(new ImageIcon("assets/backgrounds/logo.png"));
        logoLabel.setBounds(200, 100, 400, 100); // (x, y, width, height)
        backgroundLabel.add(logoLabel);
        // 3. Button Panel
        btnPanel = new JPanel();
        btnPanel.setOpaque(false); // transparent so background shows through
        btnPanel.setLayout(new GridLayout(2, 1, 20, 20));
        btnPanel.setSize(300, 150);

        int panelX = (800 - 300) / 2;
        int panelY = 400;
        btnPanel.setBounds(panelX,panelY,300,150);

     // Create ImageIcons
        ImageIcon newGameIcon = new ImageIcon("assets/buttons/new_game.png");
        ImageIcon newGameHoverIcon = new ImageIcon("assets/buttons/new_game_hover.png");

        ImageIcon loadGameIcon = new ImageIcon("assets/buttons/load_game.png");
        ImageIcon loadGameHoverIcon = new ImageIcon("assets/buttons/load_game_hover.png");

        // Create Buttons
        JButton newGameBtn = new JButton(newGameIcon);
        JButton loadGameBtn = new JButton(loadGameIcon);

        // Make buttons transparent
        newGameBtn.setBorderPainted(false);
        newGameBtn.setContentAreaFilled(false);
        newGameBtn.setFocusPainted(false);

        loadGameBtn.setBorderPainted(false);
        loadGameBtn.setContentAreaFilled(false);
        loadGameBtn.setFocusPainted(false);

        // Add hover effects
        newGameBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                newGameBtn.setIcon(newGameHoverIcon);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                newGameBtn.setIcon(newGameIcon);
            }
        });

        loadGameBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loadGameBtn.setIcon(loadGameHoverIcon);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loadGameBtn.setIcon(loadGameIcon);
            }
        });
        // CLICK
        newGameBtn.addActionListener(e -> {
        	// Disable both buttons immediately
            newGameBtn.setEnabled(false);
            loadGameBtn.setEnabled(false);
            FadePanel fadePanel = new FadePanel();
            backgroundLabel.add(fadePanel);
            backgroundLabel.setComponentZOrder(fadePanel, 0);
            backgroundLabel.revalidate();
            backgroundLabel.repaint();

            Timer timer = new Timer(0, null);
            timer.addActionListener(new ActionListener() {
                float alpha = 0f;
                @Override
                public void actionPerformed(ActionEvent e) {
                    alpha += 0.02f;
                    if (alpha >= 1f) { 
                        alpha = 1f;
                        timer.stop();
                        PlayerStats stats = new PlayerStats();
                        new GameScreen(stats);
                        dispose();
                    }
                    fadePanel.setAlpha(alpha);
                }
            });
            timer.start();
        });

        // LOAD GAME
        loadGameBtn.addActionListener(e -> {
        	// Disable both buttons immediately
        	new SaveLoadScreen(null, TitleScreen.this, false); // false = load mode
            dispose();
        });


        btnPanel.add(newGameBtn);
        btnPanel.add(loadGameBtn);

        backgroundLabel.add(btnPanel); // add button panel to background


        //
        setVisible(true);
    }
}

