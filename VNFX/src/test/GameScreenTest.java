package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.GameScreenController;
import main.PlayerStats;

public class GameScreenTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		final String GAMESCREEN_FXML_FILE_PATH = "/ui/GameScreen.fxml";
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(GAMESCREEN_FXML_FILE_PATH));
		
		PlayerStats playerStats = new PlayerStats();
		playerStats.sceneId = 1;
		playerStats.dialogueIndex = 0;
		GameScreenController gsController = new GameScreenController(playerStats);
		fxmlLoader.setController(gsController);
		Parent root	= fxmlLoader.load();
		
		primaryStage.setTitle("VNFX");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
