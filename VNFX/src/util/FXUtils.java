package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class FXUtils {
	
	public static void setBackgroundImage(Pane pane, String imagePath) {
	    // Load the image (make sure the path is correct: file: for local file, or getResource if bundled)
	    Image image = new Image("file:" + imagePath, 1280, 720, false, true);

	    // Create a BackgroundImage
	    BackgroundImage backgroundImage = new BackgroundImage(
	        image,
	        BackgroundRepeat.NO_REPEAT,
	        BackgroundRepeat.NO_REPEAT,
	        BackgroundPosition.CENTER,
	        new BackgroundSize(1280, 720, false, false, false, false)
	    );

	    // Set the background to the pane
	    pane.setBackground(new Background(backgroundImage));
	}
	
	public static String getBackgroundPathFromSceneFile(String sceneId) {
        File file = new File("assets/scenes/" + sceneId + ".txt"); // Adjust if your path differs
        if (!file.exists()) return null;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.readLine(); // first line = background path
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
