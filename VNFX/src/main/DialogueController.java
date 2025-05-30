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
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class DialogueController {
    public final GameScreenController gsController;
    public final PlayerStats playerStats;
    private story.Scene currentScene;
    
    private Timer typewriterTimer;
    private int charIndex = 0;
    private String fullText = "";

    public DialogueController(GameScreenController gsController, PlayerStats playerStats) {
        this.gsController = gsController;
        this.playerStats = playerStats;
    }
    
    public void setScene(story.Scene scene) {
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
                    CharacterImage characterImage = new CharacterImage(characterPath, 400, 533);
                    characterImage.setPosition(440, 100); // example position
                    characterImage.setOpacity(1);
                    characterImage.fadeIn(500);
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
        String characterAnimation = textEntry.getCharacterAnimation();

        if (speaker != null && !speaker.isEmpty()) {
            gsController.getNameLabel().setText(speaker);
            gsController.getNameLabel().setVisible(true);
        } else {
        	gsController.getNameLabel().setVisible(false);
        }

        if (characterPath != null) {
        	gsController.removeCharacterImage();
            CharacterImage characterImage = new CharacterImage(characterPath, 400, 533);
            characterImage.setPosition(440, 100); // example position
            if(characterAnimation!=null && characterAnimation.equals("fade"))
            	characterImage.fadeIn(500);
            else
            	characterImage.setOpacity(1);
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

        FXUtils.setBackgroundImage(gsController.getBackgroundPane(), currentScene.backgroundPath);
        showNextDialogue();
    }
    
    public void transitionToMinigame(Stage primaryStage, String minigameId,int returnSceneId) {
    	try {
    		String MINIGAME_FXML_FILE_PATH = "/ui/Minigame.fxml";
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(MINIGAME_FXML_FILE_PATH));

    		MinigameController minigameController = new MinigameController(gsController,playerStats,minigameId,returnSceneId);
    		fxmlLoader.setController(minigameController);
        
    		Parent root = fxmlLoader.load();
        
    		primaryStage.setScene(new Scene(root));
    		primaryStage.setTitle("VNFX");
    		primaryStage.show();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
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
