package main;

import javax.swing.*;

import story.DialogueEntry;
import story.Scene;
import story.SceneManager;
import ui.Choice;
import ui.ChoicePanel;
import ui.DialoguePanel;
import ui.NameLabel;
import ui.SaveLoadPanel;
import util.ImageUtils;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import ui.ClickIndicator;
import ui.DialogueTextArea;

public class GameScreen extends JFrame {
    private JLabel backgroundLabel;
    private JLabel characterLabel;
    private JPanel dialoguePanel;
    private ChoicePanel choicePanel;
    private DialogueTextArea dialogueTextArea;
    private Timer blinkTimer;
    private NameLabel nameLabel;
    private ClickIndicator clickIndicator;
    

    public Scene currentScene;
    //private int dialogueIndex = 0;
    private SceneManager sceneManager;
    
    private PlayerStats playerStats; // Save reference to current player stats

    public GameScreen(PlayerStats stats) {
        this.playerStats = stats;
        
        setTitle("Visual Novel - Game");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        // Load stats 
        SaveLoadPanel saveLoadPanel = new SaveLoadPanel(this, playerStats);
        SceneManager sceneManager = new SceneManager(playerStats, this);
        currentScene = sceneManager.getScene(playerStats.sceneId);

        // Background
        ImageIcon bgIcon = ImageUtils.loadScaledImage(currentScene.backgroundPath, 800, 600);
        backgroundLabel = new JLabel(bgIcon);
        backgroundLabel.setBounds(0, 0, 800, 600);

        // Character (start with first character in scene)
        ImageIcon charIcon = ImageUtils.loadScaledImage(currentScene.dialogues[playerStats.dialogueIndex].characterPath, 300, 400);
        characterLabel = new JLabel(charIcon);
        characterLabel.setBounds(350, 100, 300, 400);

        // Dialogue Panel
        dialoguePanel = new DialoguePanel();
        dialoguePanel.setBounds(50, 430, 700, 150);

        // Click Indicator
        clickIndicator = new ClickIndicator();
        dialoguePanel.add(clickIndicator);    

        // Dialogue TextArea
        dialogueTextArea = new DialogueTextArea();
        dialoguePanel.add(dialogueTextArea, BorderLayout.CENTER);

        // Choice Panel settings
        choicePanel = new ChoicePanel();
        choicePanel.setBounds(200, 300, 400, 200);
        choicePanel.setVisible(false);
        
        //
        nameLabel = new NameLabel();
        
        // ADD LAYERS
        add(choicePanel);
        add(saveLoadPanel);
        add(dialoguePanel);
        add(nameLabel);
        add(characterLabel);
        add(backgroundLabel);

        // Click to advance
        dialogueTextArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!choicePanel.isVisible()) { // <--- Only allow click if no choice showing
                    showNextDialogue();
                }
            }
        });

        showNextDialogue();

        setVisible(true);
    }
    // NEXT DIALOGUE
    public void showNextDialogue() {
        if (playerStats.dialogueIndex < currentScene.dialogues.length) {
            DialogueEntry entry = currentScene.dialogues[playerStats.dialogueIndex];

            if ("__CHOICE__".equals(entry.text)) {
                // This is a choice point!
                clickIndicator.stopBlinking();
                showChoicesPanel(entry.choices);
            } else {
                // Normal dialogue
            	
            	if (entry.speaker != null && !entry.speaker.isEmpty()) {
                    nameLabel.setText(entry.speaker);
                    nameLabel.setVisible(true);
                    nameLabel.repaint();
                    nameLabel.revalidate();
                } else {
                    nameLabel.setVisible(false); // Hide if no speaker (narration)
                }
                
                dialogueTextArea.setText(entry.text);
                if (entry.characterPath != null) {
                    ImageIcon newCharIcon = ImageUtils.loadScaledImage(entry.characterPath, 300, 400);
                    characterLabel.setIcon(newCharIcon);
                }
                clickIndicator.startBlinking();
            }

            playerStats.dialogueIndex++;
        } else {
            dialogueTextArea.setText("The End.");
            clickIndicator.stopBlinking();
        }
    }


    // SHOW CHOICE PANEL
    public void showChoicesPanel(List<Choice> choices) {
        choicePanel.showChoices(choices, () -> {
            revalidate();
            repaint();
        });
    }
    // LOAD SCENE
    // Load a new scene
    public void loadScene(int sceneId,int dialogueIndex) {
        // Save new sceneId to PlayerStats if needed
        playerStats.sceneId = sceneId;
        // Load scene from manager
        currentScene = SceneManager.getScene(sceneId);
        if (currentScene == null) {
            System.out.println("Scene not found: " + sceneId);
            return;
        }
        // Update background
        ImageIcon bgIcon = ImageUtils.loadScaledImage(currentScene.backgroundPath, 800, 600);
        backgroundLabel.setIcon(bgIcon);

        // Update first character image
        /*
        if (currentScene.dialogues.length > 0) {
            ImageIcon charIcon = ImageUtils.loadScaledImage(currentScene.dialogues[0].characterPath, 300, 400);
            characterLabel.setIcon(charIcon);
        } else {
            characterLabel.setIcon(null); // No character
        }
        */
        // Start first dialogue
        showNextDialogue();
    }
    
    // new scene
    public void transitionToScene(int sceneId) {
        playerStats.sceneId = sceneId;
        playerStats.dialogueIndex = 0;
        loadScene(sceneId, 0);
    }
    // get player stats
    public PlayerStats getPlayerStats() {
        return playerStats;
    }

}
