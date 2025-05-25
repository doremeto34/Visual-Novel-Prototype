package main;

import story.*;
import ui.CharacterImage;
import ui.ChoicePanel;
import util.FXUtils;
import util.ImageUtils;

import java.awt.Dimension;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public class DialogueController implements SceneActionHandler {
    private final GameScreenController gsController;
    private final PlayerStats playerStats;
    private Scene currentScene;
    
    Timer typewriterTimer;
    private int charIndex = 0;
    private String fullText = "";

    public DialogueController(GameScreenController gsController, PlayerStats playerStats) {
        this.gsController = gsController;
        this.playerStats = playerStats;
    }
    
    public void setScene(Scene scene) {
        this.currentScene = scene;
    }


    public void showNextDialogue() {
        if (playerStats.dialogueIndex < currentScene.dialogues.length) {
            DialogueEntry entry = currentScene.dialogues[playerStats.dialogueIndex];

            if (entry instanceof ChoiceDialogueEntry choiceEntry) {
            	String characterPath = choiceEntry.getCharacterPath();
            	System.out.println(characterPath);
            	if (characterPath != null) {
            		gsController.removeCharacterImage();
                    // Create and display the new character image
                    CharacterImage characterImage = new CharacterImage(characterPath, 400, 533);
                    characterImage.setPosition(440, 100); // example position
                    characterImage.fadeIn(500); // fade in over 500ms

                    gsController.setCharacterImage(characterImage);
                    gsController.getBackgroundPane().getChildren().add(0,characterImage.getImageView());
                }
                gsController.getClickIndicator().stopBlinking();
                showChoicesPanel(choiceEntry.getChoices());
            } else if (entry instanceof TextDialogueEntry textEntry) {
                updateTextDialogue(textEntry);
            }
            playerStats.dialogueIndex++;

        } else {
            gsController.getDialogueTextArea().setText("The End.");
            gsController.getClickIndicator().stopBlinking();
        }
    }

    private void updateTextDialogue(TextDialogueEntry textEntry) {
        String speaker = textEntry.getSpeaker();
        String text = textEntry.getText();
        String characterPath = textEntry.getCharacterPath();
        System.out.println("Character Path: " + characterPath);

        if (speaker != null && !speaker.isEmpty()) {
            gsController.getNameLabel().setText(speaker);
            gsController.getNameLabel().setVisible(true);
        } else {
        	gsController.getNameLabel().setVisible(false);
        }

        if (characterPath != null) {
        	gsController.removeCharacterImage();
            // Create and display the new character image
            CharacterImage characterImage = new CharacterImage(characterPath, 400, 533);
            characterImage.setPosition(440, 100); // example position
            characterImage.fadeIn(500); // fade in over 500ms

            gsController.setCharacterImage(characterImage);
            gsController.getBackgroundPane().getChildren().add(0,characterImage.getImageView());
        }

        gsController.getClickIndicator().startBlinking();
        //startTypewriterEffect(text);
        gsController.getDialogueTextArea().setText(text);
    }
    public void showChoicesPanel(List<Choice> choices) {
        ChoicePanel panel = gsController.getChoicePanel();
        panel.showChoices(choices, () -> {
            // Optional callback when choice is made
        });

        double panelWidth = 440;
        panel.setPrefWidth(panelWidth);

        Pane parent = gsController.getBackgroundPane();

        // Add panel if not already added
        if (!parent.getChildren().contains(panel)) {
            parent.getChildren().add(panel);
        }

        // Defer height calculation and positioning until CSS/layout pass is done
        Platform.runLater(() -> {
            panel.applyCss();
            panel.layout();

            double panelHeight = panel.calculateTotalHeight();
            //DEBUG 
            System.out.println("Calculated panel height: " + panelHeight);
            panel.setPrefSize(panelWidth, panelHeight);

            double x = (parent.getWidth() - panelWidth) / 2;
            double y = (parent.getHeight() - panelHeight) / 2;

            panel.relocate(x, y);
            panel.setVisible(true);
        });
    }

    @Override
    public void transitionToScene(int sceneId) {
        playerStats.sceneId = sceneId;
        playerStats.dialogueIndex = 0;
        loadScene(sceneId, 0);
    }

    public void loadScene(int sceneId, int dialogueIndex) {
        playerStats.sceneId = sceneId;
        this.currentScene = SceneManager.getScene(sceneId);

        if (currentScene == null) {
            System.out.println("Scene not found: " + sceneId);
            return;
        }

        //ImageIcon bgIcon = ImageUtils.loadScaledImage(currentScene.backgroundPath, 1280, 720);
        FXUtils.setBackgroundImage(gsController.getBackgroundPane(), currentScene.backgroundPath);
        showNextDialogue();
    }
    /*
    private void startTypewriterEffect(String text) {
        if (typewriterTimer != null && typewriterTimer.isRunning()) {
            typewriterTimer.stop();
        }

        this.fullText = text;
        this.charIndex = 0;
        gsController.getDialogueTextArea().setText("");

        typewriterTimer = new Timer(30, e -> {
            if (charIndex < fullText.length()) {
            	gsController.getDialogueTextArea().append(String.valueOf(fullText.charAt(charIndex)));
                charIndex++;
            } else {
                typewriterTimer.stop();
                gsController.getClickIndicator().startBlinking();
            }
        });
        typewriterTimer.start();
    }
	*/
    
}
