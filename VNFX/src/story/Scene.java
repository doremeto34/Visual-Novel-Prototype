package story;

import java.io.Serializable;

public class Scene implements Serializable {
    private static final long serialVersionUID = 1L;

    public String backgroundPath;
    public DialogueEntry[] dialogues; // Use DialogueEntry now!

    public Scene(String backgroundPath, DialogueEntry[] dialogues) {
        this.backgroundPath = backgroundPath;
        this.dialogues = dialogues;
    }
}
