package story;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BooleanSupplier;

import main.GameScreen;
import main.PlayerStats;
import java.util.List;
import java.io.*;
import java.util.*;

public class SceneManager {
    private static Map<Integer, Scene> scenes = new HashMap<>();
    private int currentSceneIndex = 1;
    private GameScreen gameScreen;
    private PlayerStats playerStats;

    // Map action keys (from code:) to actual Runnable logic
    private Map<String, Runnable> codeMap = new HashMap<>();

    public SceneManager(PlayerStats playerStats, GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.playerStats = playerStats;
        registerCodeActions();
        loadAllScenes();
    }

    private void registerCodeActions() {
        codeMap.put("exploreForest", () -> {
            if (playerStats.stat1 > -1) {
                gameScreen.transitionToScene(2);
            } else {
                System.out.println("You are too weak to explore!");
            }
        });

        codeMap.put("restHere", () -> {
            playerStats.stat1 += 20;
            System.out.println("You regained health by resting.");
        });

        codeMap.put("thirdChoice", () -> {
            playerStats.stat1 += 20;
            System.out.println("You regained health by resting.");
        });

        codeMap.put("fourthChoice", () -> {
            playerStats.stat1 += 20;
            System.out.println("You regained health by resting.");
        });
        
        codeMap.put("gotoScene1", () -> {
        	gameScreen.transitionToScene(1);
        });
        codeMap.put("gotoScene2", () -> {
        	gameScreen.transitionToScene(2);
        });
        codeMap.put("gotoScene3", () -> {
        	gameScreen.transitionToScene(3);
        });
    }

    private BooleanSupplier createConditionFromExpression(String expr) {
        switch (expr) {
            case "stat1>-1":
                return () -> playerStats.stat1 > -1;
            case "stat2>=10":
                return () -> playerStats.stat2 >= 10;
            case "stat1<=50":
                return () -> playerStats.stat1 <= 50;
            // Add more as needed
            default:
                System.err.println("Unknown condition: " + expr);
                return () -> true; // fallback: always show
        }
    }
    
    private void loadAllScenes() {
        // Example: loads scene1.txt, scene2.txt,... You can customize how many scenes you have
        int maxScenes = 3; // change to your total scene count
        for (int i = 1; i <= maxScenes; i++) {
            Scene scene = parseSceneFromFile("assets/scenes/scene" + i + ".txt");
            if (scene != null) {
                scenes.put(i, scene);
            }
        }
    }

    private Scene parseSceneFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String backgroundPath = br.readLine();
            if (backgroundPath == null) {
                System.err.println("Empty scene file: " + filename);
                return null;
            }

            List<DialogueEntry> entries = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;

                if (line.startsWith("D|")) {
                    String[] parts = line.split("\\|", 4);
                    entries.add(new DialogueEntry(parts[1], parts[2], parts.length > 3 ? parts[3] : null));
                } else if (line.startsWith("C|")) {
                    List<Choice> choices = new ArrayList<>();

                    while ((line = br.readLine()) != null) {
                        line = line.trim();

                        if (line.isEmpty() || line.startsWith("#")) continue; // Skip empty/comment lines
                        if (!line.startsWith("O|")) break; // End of choice block

                        String[] parts = line.split("\\|", 4); // Allow up to: O|text|code|cond:xxx
                        String text = parts[1];
                        String cmd = parts[2].trim();
                        Runnable action = createRunnableFromCommand(cmd);

                        BooleanSupplier condition = () -> true;
                        if (parts.length >= 4 && parts[3].startsWith("cond:")) {
                            String expr = parts[3].substring(5).trim();
                            condition = createConditionFromExpression(expr);
                        }

                        choices.add(new Choice(text, action, condition));
                    }

                    entries.add(new DialogueEntry(choices));

                    // Re-evaluate current line if it's not part of this choice block
                    if (line == null) break;
                    else continue;
                }
            }

            DialogueEntry[] dialogueArray = entries.toArray(new DialogueEntry[0]);
            return new Scene(backgroundPath, dialogueArray);

        } catch (IOException e) {
            System.err.println("Failed to load scene from file: " + filename);
            e.printStackTrace();
            return null;
        }
    }

    public Scene getCurrentScene() {
        return scenes.get(currentSceneIndex);
    }

    public void loadScene(int sceneIndex) {
        if (scenes.containsKey(sceneIndex)) {
            currentSceneIndex = sceneIndex;
            // TODO: update your UI with scenes.get(sceneIndex)
        } else {
            System.err.println("Scene " + sceneIndex + " not found.");
        }
    }

    private Runnable createRunnableFromCommand(String cmd) {
        if (cmd.startsWith("code:")) {
            String key = cmd.substring(5);
            Runnable action = codeMap.get(key);
            if (action != null) return action;
            else return () -> System.err.println("Unknown code: " + key);
        } else if (cmd.startsWith("goto:")) {
            int sceneIndex = Integer.parseInt(cmd.substring(5));
            return () -> loadScene(sceneIndex);
        } else {
            return () -> System.out.println("No action for command: " + cmd);
        }
    }

    public static Scene getScene(int sceneIndex) {
        return scenes.get(sceneIndex);
    }

}

