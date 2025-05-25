package main;

import java.util.List;

import story.Choice;

public interface SceneActionHandler {
    void transitionToScene(int sceneId);
    void showChoicesPanel(List<Choice> choices);
    void showNextDialogue();
}
