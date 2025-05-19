package story;

import java.util.List;

public class DialogueEntry {
    public String text;
    public String characterPath;
    public String speaker;
    public List<Choice> choices; // Optional

    public DialogueEntry(String speaker, String text, String characterPath) {
        this.speaker = speaker;
    	this.text = text;
        this.characterPath = characterPath;
        this.choices = null;
    }

    public DialogueEntry(List<Choice> choices) {
        this.text = "__CHOICE__"; // special marker
        this.characterPath = null; // no character change needed (optional)
        this.choices = choices;
    }
}
