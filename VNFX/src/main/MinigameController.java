package main;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import util.FXUtils;

public class MinigameController {
	
	final GameScreenController gsController;
	final PlayerStats playerStats;
	String minigameId;
	int returnSceneId;
	public MinigameController(GameScreenController gsController,PlayerStats playerStats,String minigameId,int returnSceneId) {
		this.gsController = gsController;
		this.playerStats = playerStats;
		this.minigameId = minigameId;
		this.returnSceneId = returnSceneId;
	}
    @FXML
    private Button returnButton;

    @FXML
    void returnButtonPressed(ActionEvent event) {
        gsController.dialogueController.transitionToScene(returnSceneId);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/GameScreen.fxml"));
            loader.setController(gsController);
            gsController.getPlayerStats().dialogueIndex=0;
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("VNFX");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
