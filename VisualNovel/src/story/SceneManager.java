package story;

import java.util.HashMap;
import java.util.Map;

import main.GameScreen;
import main.PlayerStats;

import java.util.List;

import ui.Choice;

public class SceneManager {
    public PlayerStats playerStats;
    public GameScreen gameScreen;
    public static Map<Integer, Scene> scenes = new HashMap<>();
    
    public SceneManager(PlayerStats playerStats, GameScreen gameScreen) {
        this.playerStats = playerStats;
        this.gameScreen = gameScreen;
        loadScenes();
    }

    private void loadScenes() {
        scenes.put(1, new Scene(
            "assets/backgrounds/scene1.png",
            new DialogueEntry[]{
                new DialogueEntry("Alice","Hello tml!", "assets/characters/alice.png"),
                new DialogueEntry("Bob","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam!", "assets/characters/bob.png"),
                new DialogueEntry("Bob","3rd text!", "assets/characters/alice.png"),
                new DialogueEntry(List.of(
                    new Choice("Explore the forest", () -> {
                        if (playerStats.stat1 > -1) {
                            //System.out.println("You are healthy enough to explore!");
                            gameScreen.transitionToScene(2);
                        } else {
                            //System.out.println("You are too weak to explore!");
                        }
                    }),
                    new Choice("Rest here", () -> {
                        playerStats.stat1 += 20;
                        System.out.println("You regained health by resting.");
                    }),
                    new Choice("Third choice", () -> {
                        playerStats.stat1 += 20;
                        System.out.println("You regained health by resting.");
                    }),
                    new Choice("Fourth choice", () -> {
                        playerStats.stat1 += 20;
                        System.out.println("You regained health by resting.");
                    })
                )),
                new DialogueEntry("Alice","Alice breeze passes...", "assets/characters/alice.png"),
                new DialogueEntry("Bob","A breeze passes...", "assets/characters/bob.png")
            }
        ));

        scenes.put(2, new Scene(
            "assets/backgrounds/scene2.png",
            new DialogueEntry[]{
                new DialogueEntry("Bob","You walk deeper into the dark forest...", "assets/characters/bob.png"),
                new DialogueEntry("Bob","You hear wolves howling in the distance.", "assets/characters/bob.png"),
                new DialogueEntry(List.of(
                    new Choice("Run toward the sound", () -> {
                        playerStats.stat2 -= 10;
                        //System.out.println("You were scratched by a branch! Health -10");
                        gameScreen.transitionToScene(3);
                    }),
                    new Choice("Hide and stay quiet", () -> {
                        //.out.println("You hide and wait safely.");
                        gameScreen.transitionToScene(3);
                    })
                ))
            }
        ));
        
        scenes.put(3, new Scene(
                "assets/backgrounds/scene3.png",
                new DialogueEntry[]{
                    new DialogueEntry("Alice","You walk deeper into the dark forest...", "assets/characters/alice.png"),
                    new DialogueEntry("Bob","You hear wolves howling in the distance.", "assets/characters/bob.png"),
                    new DialogueEntry(List.of(
                        new Choice("Run toward the sound", () -> {
                            playerStats.stat2 -= 10;
                            //System.out.println("You were scratched by a branch! Health -10");
                            gameScreen.transitionToScene(2);
                        }),
                        new Choice("Hide and stay quiet", () -> {
                            //System.out.println("You hide and wait safely.");
                            gameScreen.transitionToScene(2);
                        })
                    ))
                }
            ));

    }


    public static Scene getScene(int sceneId) {
        return scenes.get(sceneId);
    }
}
